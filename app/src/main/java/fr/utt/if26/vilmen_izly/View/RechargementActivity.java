package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import fr.utt.if26.vilmen_izly.Model.CarteBancaire;
import fr.utt.if26.vilmen_izly.Model.CarteBancaireAdapter;
import fr.utt.if26.vilmen_izly.Model.Rechargement;
import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.CarteBancaireViewModel;
import fr.utt.if26.vilmen_izly.ViewModel.RechargementViewModel;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class RechargementActivity extends AppCompatActivity {

    private String identifiant;
    private UtilisateurViewModel utilisateurViewModel;
    private RechargementViewModel rechargementViewModel;
    private CarteBancaireViewModel carteBancaireViewModel;
    private double previousSolde;
    OnButtonPlusClickListener buttonPlusClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechargement);

        ImageButton flecheRetour = findViewById(R.id.retour_bouton_rechargement);

        ImageButton bouton10, bouton20, bouton30, bouton40;
        EditText soldeEt = findViewById(R.id.solde_rechargement_et);
        bouton10 = findViewById(R.id.bouton_10_rechargement);
        bouton20 = findViewById(R.id.bouton_20_rechargement);
        bouton30 = findViewById(R.id.bouton_30_rechargement);
        bouton40 = findViewById(R.id.bouton_40_rechargement);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("identifiant")) {
                identifiant = intent.getStringExtra("identifiant");
            }
        }

        bouton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format("%.2f", 10.00f);
                soldeEt.setText(text);
            }
        });
        bouton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format("%.2f", 20.00f);
                soldeEt.setText(text);
            }
        });
        bouton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format("%.2f", 30.00f);
                soldeEt.setText(text);
            }
        });
        bouton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format("%.2f", 40.00f);
                soldeEt.setText(text);
            }
        });
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        MaterialButton boutonConfirmer = findViewById(R.id.confirmer_bouton_recharger);
        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);
        rechargementViewModel = new ViewModelProvider(this).get(RechargementViewModel.class);
        carteBancaireViewModel = new ViewModelProvider(this).get(CarteBancaireViewModel.class);

        boutonConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valeurTextuelle = soldeEt.getText().toString();
                valeurTextuelle = valeurTextuelle.replace(",", ".");
                double montant = Double.parseDouble(valeurTextuelle);
                if (montant > 0) {
                    Rechargement newRechargement = rechargementViewModel.createRechargement(montant, identifiant);
                    Log.i("NewRechargement", newRechargement.toString());
                    rechargementViewModel.insertRechargement(newRechargement);
                    utilisateurViewModel.addToSoldeUtilisateur(montant, identifiant);

                    /*Bundle bundle = new Bundle();
                    bundle.putString("identifiant", identifiant);
                    SecondFragment secondFragment = new SecondFragment();
                    secondFragment.setArguments(bundle);

                    if (buttonPlusClickListener != null) {
                        buttonPlusClickListener.onButtonPlusClick();
                    }

                    RechargementActivity.this
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, secondFragment)
                            .addToBackStack(null)  //Permet le retour en arrière
                            .commit();*/
                    Intent intent = new Intent(RechargementActivity.this, HomeActivity.class);
                    intent.putExtra("identifiant", identifiant);
                    startActivity(intent);

                } else {
                    Toast.makeText(RechargementActivity.this, "Veuillez entrer un montant", Toast.LENGTH_SHORT).show();
                }
            }
        });

        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(RechargementActivity.this, HomeActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);*/
                onBackPressed();
            }
        });


        Spinner spinnerCartesBancaires = findViewById(R.id.spinnerCartesBancaires);
        // Récupération des cartes bancaires

        // Création d'un adapteur pour le spinner
        carteBancaireViewModel.getCartesBancairesParIdentifiant(identifiant).observe(this, new Observer<List<CarteBancaire>>() {
            @Override
            public void onChanged(List<CarteBancaire> listeCartesBancaires) {
                CarteBancaireAdapter adapter = new CarteBancaireAdapter(getApplicationContext(),listeCartesBancaires);
                spinnerCartesBancaires.setAdapter(adapter);
            }
        });


        spinnerCartesBancaires.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Récupérez l'objet CarteBancaire sélectionné
                CarteBancaire carteSelectionnee = (CarteBancaire) parentView.getItemAtPosition(position);

                // Faites quelque chose avec la carte sélectionnée, si nécessaire
                // Par exemple, affichez des détails ou effectuez une action
                Toast.makeText(getApplicationContext(), "Carte sélectionnée : " + carteSelectionnee.getCardName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Code à exécuter lorsqu'aucun élément n'est sélectionné
            }
        });

        MaterialButton ajouterCarteBancaire = findViewById(R.id.ajouter_une_cb_bouton_rechargement);
        ajouterCarteBancaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechargementActivity.this, AddCarteBancaireActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });

    }

    public void setOnButtonPlusClickListener(OnButtonPlusClickListener listener) {
        this.buttonPlusClickListener = listener;
    }
}