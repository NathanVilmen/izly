package fr.utt.if26.vilmen_izly.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;
import fr.utt.if26.vilmen_izly.ViewModel.UtilisateurViewModel;

public class MainActivity extends AppCompatActivity {
    private UtilisateurViewModel utilisateurViewModel;
    EditText identifiantEt, codeSecretEt;

    private String identifiantUtilisateurConnecte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*registerActivityLifecycleCallbacks(this);*/

        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                // Ne rien faire ici
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                // Ne rien faire ici
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                // Ne rien faire ici
                utilisateurViewModel = new ViewModelProvider(MainActivity.this).get(UtilisateurViewModel.class);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                // Ne rien faire ici
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                // Vérifiez si l'activité est la dernière activité visible (l'application est en cours de fermeture)
                /*if (activity.isFinishing()) {
                    deconnecterUtilisateur();
                }*/
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                // Ne rien faire ici
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // Ne rien faire ici
            }
        });


        MaterialButton connexionBouton = (MaterialButton) findViewById(R.id.connexion_main_bouton);

        identifiantEt = findViewById(R.id.identifiant_main_et);
        codeSecretEt = findViewById(R.id.code_secret_main_et);

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        Button inscriptionBouton = findViewById(R.id.inscris_toi_main_button);
        inscriptionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        Button motDePasseOublie = findViewById(R.id.mdp_oublie_main_button);
        motDePasseOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Instructions envoyées par mail.", Toast.LENGTH_SHORT).show();
            }
        });


        connexionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String identifiantStr = identifiantEt.getText().toString();
                String codeSecretStr = codeSecretEt.getText().toString();
                utilisateurViewModel = new ViewModelProvider(MainActivity.this).get(UtilisateurViewModel.class);

                if (!identifiantStr.isEmpty()) {

                    // Vérifier que le code secret est un entier valide
                    if (!codeSecretStr.isEmpty()) {
                        if(TextUtils.isDigitsOnly(codeSecretStr)) {
                            int codeSecretInt = Integer.parseInt(codeSecretStr);

                            // Authentifier l'utilisateur avec le ViewModel
                            utilisateurViewModel.authentifierUtilisateur(identifiantStr, codeSecretInt).observe(MainActivity.this, utilisateur ->  {
                                    if (utilisateur != null) {
                                        // Authentification réussie, passer à l'activité HomeActivity avec les extras
                                        identifiantUtilisateurConnecte = identifiantStr;
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        intent.putExtra("identifiant", identifiantStr);
                                        startActivity(intent);
                                    } else {
                                        // Authentification échouée, afficher un message d'erreur
                                        Toast.makeText(MainActivity.this, "Erreur: l'authentification a échoué.", Toast.LENGTH_SHORT).show();
                                        Log.e("ConnexionError", "Aucun compte pour l'utilisateur : "+ identifiantStr);
                                    }
                            });

                        } else {
                            // Le code secret n'est pas un entier valide (on ne peut pas le transformer en entier)
                            Toast.makeText(MainActivity.this, "Veuillez entrer un code secret valide", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Le champ du code secret est vide
                        Toast.makeText(MainActivity.this, "Veuillez entrer un code secret", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Le champ de l'identifiant est vide
                    Toast.makeText(MainActivity.this, "Veuillez entrer un identifiant", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void deconnecterUtilisateur() {
        // Déconnectez l'utilisateur
        if (identifiantUtilisateurConnecte != null) {
            utilisateurViewModel.deconnecterUtilisateur(identifiantUtilisateurConnecte);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (identifiantUtilisateurConnecte != null) {
            deconnecterUtilisateur();
        }
    }


}