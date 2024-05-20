package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import fr.utt.if26.vilmen_izly.Model.StatutEtudiant;
import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class MesInformationsActivity extends AppCompatActivity {

    private String identifiant;
    private UtilisateurViewModel utilisateurViewModel;
    TextView identifiantTv, codeSecretTv, codePostalTv, nomTv, prenomTv, adresseTv, dateDeNaissanceTv, telephoneTv, villeTv, paysTv, emailTv, statutTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_informations);

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("identifiant")){
                identifiant = intent.getStringExtra("identifiant");
            }
        }

        identifiantTv = findViewById(R.id.identifiant_mes_informations_et);
        nomTv = findViewById(R.id.nom_mes_informations_et);
        prenomTv = findViewById(R.id.prenom_mes_informations_et);
        codeSecretTv = findViewById(R.id.code_secret_mes_informations_et);
        codePostalTv = findViewById(R.id.code_postal_mes_informations_et);
        adresseTv = findViewById(R.id.adresse_mes_informations_et);
        dateDeNaissanceTv = findViewById(R.id.date_de_naissance_mes_informations_et);
        telephoneTv = findViewById(R.id.telephone_mes_informations_et);
        villeTv = findViewById(R.id.ville_mes_informations_et);
        paysTv = findViewById(R.id.pays_mes_informations_et);
        emailTv = findViewById(R.id.email_mes_informations_et);
        statutTv = findViewById(R.id.statut_mes_informations_et);


        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);
        utilisateurViewModel.getUtilisateurParIdentifiant(identifiant).observe(this, new Observer<Utilisateur>() {
            @Override
            public void onChanged(Utilisateur utilisateur) {
                identifiantTv.setText(utilisateur.getIdentifiant());
                nomTv.setText(utilisateur.getNom());
                prenomTv.setText(utilisateur.getPrenom());
                codeSecretTv.setText(utilisateur.getCodeSecret());
                codePostalTv.setText(String.valueOf(utilisateur.getCodePostal()));
                adresseTv.setText(utilisateur.getAdresse());
                dateDeNaissanceTv.setText(utilisateur.getDateNaissance());
                telephoneTv.setText(utilisateur.getTelephone());
                villeTv.setText(utilisateur.getVille());
                paysTv.setText(utilisateur.getPays());
                emailTv.setText(utilisateur.getEmail());
                statutTv.setText(utilisateur.getStatutName());
            }
        });

        ImageButton flecheRetour = findViewById(R.id.retour_bouton_mes_informations);
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}