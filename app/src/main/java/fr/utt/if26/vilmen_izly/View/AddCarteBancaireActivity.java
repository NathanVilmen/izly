package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.Model.CarteBancaire;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.CarteBancaireViewModel;
import fr.utt.if26.vilmen_izly.ViewModel.RechargementViewModel;

public class AddCarteBancaireActivity extends AppCompatActivity {
    String identifiant;
    CarteBancaireViewModel carteBancaireViewModel;
    EditText nomEt, numeroEt, ccvEt, dateFinValiditeEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carte_bancaire);

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("identifiant")){
                identifiant = intent.getStringExtra("identifiant");
            }
        }

        carteBancaireViewModel = new ViewModelProvider(this).get(CarteBancaireViewModel.class);


        ImageButton flecheRetour = findViewById(R.id.retour_bouton_add_carte_bancaire);
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nomEt = findViewById(R.id.nom_add_cb_et);
        numeroEt = findViewById(R.id.numero_add_cb_et);
        ccvEt = findViewById(R.id.ccv_add_cb_et);
        dateFinValiditeEt = findViewById(R.id.date_fin_add_cb_et);

        MaterialButton boutonConfirmer = findViewById(R.id.confirmer_bouton_add_cb);
        boutonConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomEt.getText().toString();
                String numero = numeroEt.getText().toString();
                String ccv = ccvEt.getText().toString();
                String dateFinValidite = dateFinValiditeEt.getText().toString();

                CarteBancaire newCb = new CarteBancaire(nom, numero, ccv, dateFinValidite, identifiant);
                carteBancaireViewModel.insertCarteBancaire(newCb);

                Intent intent = new Intent(AddCarteBancaireActivity.this, RechargementActivity.class);
                intent.putExtra("identifiant", identifiant);
                startActivity(intent);
            }
        });


    }
}