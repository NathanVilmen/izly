package fr.utt.if26.vilmen_izly.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class FourthFragment extends Fragment {

    private UtilisateurViewModel utilisateurViewModel;
    private String identifiant;

    public FourthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        if (getArguments() != null && getArguments().containsKey("identifiant")) {

            // Récupérer l'identifiant de l'utilisateur depuis les arguments du fragment
            identifiant = getArguments().getString("identifiant");
        }

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        MaterialButton boutonDeconnexion = view.findViewById(R.id.deconnexion_bouton_parametres);
        boutonDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilisateurViewModel.deconnecterUtilisateur(identifiant);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}