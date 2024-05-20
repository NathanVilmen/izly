package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import fr.utt.if26.vilmen_izly.Model.Rechargement;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.RechargementViewModel;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class PaiementQrActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton flecheRetour;

    private String identifiant;

    private UtilisateurViewModel utilisateurViewModel;
    private RechargementViewModel rechargementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_qr);

        flecheRetour = (ImageButton) findViewById(R.id.retour_bouton_paiementqr);

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("identifiant")){
                identifiant = intent.getStringExtra("identifiant");
            }
        }
        flecheRetour.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        //conditions de connexions : identifiant OK et mdp OK
        onBackPressed();

        /*Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("identifiant", identifiant);
        this.startActivity(intent);*/
    }
}