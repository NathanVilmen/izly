package fr.utt.if26.vilmen_izly.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.vilmen_izly.Model.Rechargement;
import fr.utt.if26.vilmen_izly.Model.RechargementListAdapter;
import fr.utt.if26.vilmen_izly.Model.StatutEtudiant;
import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.RechargementViewModel;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class FirstFragment extends Fragment {

    Button payerBouton;
    ImageButton boutonPlus, boutonCompte;
    OnButtonPlusClickListener buttonPlusClickListener;
    TextView textViewBienvenue, textViewSolde, textViewRegime;
    private UtilisateurViewModel utilisateurViewModel;
    private RechargementViewModel rechargementViewModel;
    private String identifiant;
    private StatutEtudiant statut;
    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Récupération de la vue
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Initialiser les ViewModel
        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);
        rechargementViewModel = new ViewModelProvider(this).get(RechargementViewModel.class);

        // Vérifier si l'extra "identifiant" existe dans les arguments du fragment
        if (getArguments() != null && getArguments().containsKey("identifiant")) {

            // Récupérer l'identifiant de l'utilisateur depuis les arguments du fragment
            identifiant = getArguments().getString("identifiant");
            Log.i("Connexion", "utilisateur connecté:"+identifiant);

            // Appeler la méthode getUtilisateurParIdentifiant du ViewModel pour obtenir l'utilisateur
            utilisateurViewModel.getUtilisateurParIdentifiant(identifiant).observe(getViewLifecycleOwner(), new Observer<Utilisateur>() {
                @Override
                public void onChanged(Utilisateur utilisateur) {
                    if (utilisateur != null) {
                        // Récupérer le nom et le prénom de l'utilisateur
                        String nom = utilisateur.getNom();
                        String prenom = utilisateur.getPrenom();
                        Float solde = utilisateur.getSolde();

                        // Afficher le message de bienvenue avec le nom de l'utilisateur
                        textViewBienvenue = view.findViewById(R.id.bienvenue_home_tv);
                        String text = "Bonjour, " + prenom + " " + nom;
                        textViewBienvenue.setText(text);

                        textViewSolde = view.findViewById(R.id.solde_home_tv);
                        text = String.format("+ %.2f €", solde);
                        textViewSolde.setText(text);

                        if (utilisateur.getStatut() == StatutEtudiant.BOURSIER){
                            statut = StatutEtudiant.BOURSIER;
                        } else if (utilisateur.getStatut() == StatutEtudiant.NORMAL) {
                            statut = StatutEtudiant.NORMAL;
                        } else {
                            statut = StatutEtudiant.AUTRE;
                        }
                    } else {
                        Log.w("UserRetrievalError", "Aucun utilisateur trouvé.");
                    }
                }
            });
        }


        // Initialisation du bouton "Payer" pour implémenter son événement
        payerBouton = (Button) view.findViewById(R.id.payer_bouton_home);
        payerBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double montant = -3.30;
                if (statut == StatutEtudiant.BOURSIER){
                    montant = -1.00;
                }

                Rechargement newPayment = rechargementViewModel.createRechargement(montant, identifiant);
                rechargementViewModel.insertRechargement(newPayment);
                utilisateurViewModel.addToSoldeUtilisateur(montant, identifiant);

                Intent intent = new Intent(getActivity(), PaiementQrActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });

        // Initialisation du bouton "+" pour implémenter son événement
        boutonPlus = (ImageButton) view.findViewById(R.id.plus_bouton_home);
        boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment secondFragment = new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putString("identifiant", identifiant);
                secondFragment.setArguments(bundle);

                if (buttonPlusClickListener != null) {
                    buttonPlusClickListener.onButtonPlusClick();
                }

                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, secondFragment)
                        .addToBackStack(null)  //Permet le retour en arrière
                        .commit();
            }
        });

        // Initialisation du bouton "Compte" pour implémenter son événement
        boutonCompte = (ImageButton) view.findViewById(R.id.bouton_account_home);
        boutonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PageUtilisateurActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });

        //initialisation du recyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_rechargements_rv);
        final RechargementListAdapter adapter = new RechargementListAdapter(new RechargementListAdapter.RechargementDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        rechargementViewModel.getRechargementsByIdentifiantUtilisateur(identifiant).observe(getViewLifecycleOwner(), rechargements -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(rechargements);
        });

        textViewRegime = view.findViewById(R.id.vous_beneficiez_home_tv);
        ImageButton infoBouton = view.findViewById(R.id.logo_info_home);
        infoBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewRegime.getText().toString();
                if (text.equals("Vous bénéficiez d'un tarif boursier.")){
                    Toast.makeText(getContext(), "Tarif boursier : 1€", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Tarif normal : 3.30€", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
    public void setOnButtonPlusClickListener(OnButtonPlusClickListener listener) {
        this.buttonPlusClickListener = listener;
    }



}