package fr.utt.if26.vilmen_izly.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.R;


public class SecondFragment extends Fragment {

    MaterialButton carteBancaire;
    private String identifiant;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        if (getArguments() != null && getArguments().containsKey("identifiant")) {
            // Récupérer l'identifiant de l'utilisateur depuis les arguments du fragment
            identifiant = getArguments().getString("identifiant");
            Log.i("Rechargement", "Rechargement pour : " + identifiant);
        } else{
            Log.e("Rechargement", "Aucun utilisateur trouvé.");
        }

        carteBancaire = (MaterialButton) view.findViewById(R.id.carte_bancaire_bouton_recharger);
        carteBancaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RechargementActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });


        return view;
    }
}