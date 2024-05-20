package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.Model.StatutEtudiant;
import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class InscriptionActivity extends AppCompatActivity {


    private EditText identifiantEt, nomEt, prenomEt, codeSecretEt, codePostalEt,
            adresseEt, dateDeNaissanceEt, telephoneEt, villeEt, paysEt, emailEt;
    private CheckBox boursierCb, conditionCb;
    private UtilisateurViewModel utilisateurViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ImageButton flecheRetour = findViewById(R.id.retour_bouton_inscription);
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button enSavoirPlus = findViewById(R.id.en_savoir_plus_inscription_bouton);
        enSavoirPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this, RGPDActivity.class);
                startActivity(intent);
            }
        });

        identifiantEt = findViewById(R.id.identifiant_inscription_et);
        nomEt = findViewById(R.id.nom_inscription_et);
        prenomEt = findViewById(R.id.prenom_inscription_et);
        codeSecretEt = findViewById(R.id.code_secret_inscription_et);
        codePostalEt = findViewById(R.id.code_postal_inscription_et);
        adresseEt = findViewById(R.id.adresse_inscription_et);
        dateDeNaissanceEt = findViewById(R.id.date_de_naissance_inscription_et);
        telephoneEt = findViewById(R.id.telephone_inscription_et);
        villeEt = findViewById(R.id.ville_inscription_et);
        paysEt = findViewById(R.id.pays_inscription_et);
        emailEt = findViewById(R.id.email_inscription_et);
        boursierCb = findViewById(R.id.boursier_inscription_cb);
        conditionCb = findViewById(R.id.conditions_generales_inscription_cb);

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);


        MaterialButton boutonInscription = findViewById(R.id.inscription_inscription_bouton);
        boutonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identifiant;

                identifiant = identifiantEt.getText().toString();

                utilisateurViewModel.getUtilisateurParIdentifiant(identifiant).observe(InscriptionActivity.this, utilisateur -> {
                    if (utilisateur != null){
                        Toast.makeText(InscriptionActivity.this, "Cet utilisateur existe déjà !", Toast.LENGTH_SHORT).show();
                    } else {
                        String nom, prenom, codeSecret, adresse, dateDeNaissance, telephone, ville, pays, email;
                        int codePostal;

                        nom = nomEt.getText().toString();
                        prenom = prenomEt.getText().toString();
                        codeSecret = codeSecretEt.getText().toString();
                        adresse = adresseEt.getText().toString();
                        dateDeNaissance = dateDeNaissanceEt.getText().toString();
                        telephone = telephoneEt.getText().toString();
                        ville = villeEt.getText().toString();
                        pays = paysEt.getText().toString();
                        email = emailEt.getText().toString();
                        codePostal = Integer.parseInt(codePostalEt.getText().toString());

                        if (!identifiant.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() &&
                                !codeSecret.isEmpty() && !adresse.isEmpty() && !dateDeNaissance.isEmpty() &&
                                !telephone.isEmpty() && !ville.isEmpty() && !pays.isEmpty() &&
                                !email.isEmpty() && codePostalEt.getText().toString().trim().length() > 0) {
                            // Tous les champs sont remplis
                            StatutEtudiant statut = StatutEtudiant.NORMAL;

                            if (boursierCb.isChecked()){
                                statut = StatutEtudiant.BOURSIER;
                            }
                            if (conditionCb.isChecked()){
                                Utilisateur newUser = new Utilisateur(identifiant, nom, prenom,
                                        dateDeNaissance, codeSecret, null, adresse, codePostal,
                                        ville, pays, email, telephone, statut, true);
                                Log.i("NewUser", "Ajout du nouvel utilisateur..." + newUser.toString());

                                utilisateurViewModel.insert(newUser);

                                Log.i("UserCreation", "Nouvel utilisateur ajouté.");

                                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(InscriptionActivity.this, "Inscription réussie, veuillez vous connecter.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(InscriptionActivity.this, "Vous devez accepter les conditions.", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            // Au moins un des champs est vide
                            Toast.makeText(InscriptionActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}