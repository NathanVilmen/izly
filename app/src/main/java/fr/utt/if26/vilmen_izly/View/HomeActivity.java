package fr.utt.if26.vilmen_izly.View;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.Objects;

import fr.utt.if26.vilmen_izly.Model.Utilisateur;
import fr.utt.if26.vilmen_izly.R;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnButtonPlusClickListener {

    private BottomNavigationView bottomNavigationView;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        if (intent != null){
            String identifiant = "";
            if (intent.hasExtra("identifiant")){
                identifiant = intent.getStringExtra("identifiant");
                bundle.putString("identifiant", identifiant);
            }
        }

        firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);


        secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        thirdFragment = new ThirdFragment();
        thirdFragment.setArguments(bundle);

        fourthFragment = new FourthFragment();
        fourthFragment.setArguments(bundle);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.main_menu_accueil);


        firstFragment.setOnButtonPlusClickListener(this);
    }

    // Implémenter l'interface
    @Override
    public void onButtonPlusClick() {
        // Changer la sélection de l'item "Recharger"
        bottomNavigationView.setSelectedItemId(R.id.main_menu_recharger);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.main_menu_accueil) {
            if (!firstFragment.isAdded()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, firstFragment)
                        .commit();
            }

            return true;
        } else if (itemId == R.id.main_menu_recharger) {
            if (!secondFragment.isAdded()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, secondFragment)
                        .commit();
            }

            return true;
        } else if (itemId == R.id.main_menu_actus) {
            if (!thirdFragment.isAdded()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, thirdFragment)
                        .commit();
            }
            return true;
        } else if (itemId == R.id.main_menu_parametres) {
            if (!fourthFragment.isAdded()){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fourthFragment)
                        .commit();
            }
            return true;
        }
        return false;
    }


}
