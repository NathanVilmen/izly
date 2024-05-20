package fr.utt.if26.vilmen_izly.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import fr.utt.if26.vilmen_izly.R;

public class RGPDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgpdactivity);

        ImageButton flecheRetour = findViewById(R.id.retour_bouton_rgpd);
        flecheRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}