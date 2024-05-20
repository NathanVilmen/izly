package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class PageUtilisateurActivity extends AppCompatActivity {

    ImageButton flecheRetour;
    private UtilisateurViewModel utilisateurViewModel;
    private String identifiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_utilisateur);

        Intent intent = getIntent();
        if (intent!=null){
            if (intent.hasExtra("identifiant")){
                this.identifiant = intent.getStringExtra("identifiant");
            }
        }

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        flecheRetour = (ImageButton) findViewById(R.id.retour_bouton_utilisateur);
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        MaterialButton boutonDeconnexion = findViewById(R.id.deconnexion_bouton_utilisateur);
        boutonDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilisateurViewModel.deconnecterUtilisateur(identifiant);
                Intent intent = new Intent(PageUtilisateurActivity.this, MainActivity.class);
                startActivity(intent);

                // Bloquer le retour en arrière
                finish();
            }
        });

        MaterialButton mesInformationsBouton = findViewById(R.id.mes_infos_bouton_utilisateur);
        mesInformationsBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageUtilisateurActivity.this, MesInformationsActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });
    }
}