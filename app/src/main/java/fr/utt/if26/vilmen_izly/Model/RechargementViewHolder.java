package fr.utt.if26.vilmen_izly.Model;


import static java.security.AccessController.getContext;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.vilmen_izly.R;

public class RechargementViewHolder extends RecyclerView.ViewHolder {

    TextView dateTv, heureTv, montantTv;
    public RechargementViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTv = itemView.findViewById(R.id.rechargement_date_tv);
        heureTv = itemView.findViewById(R.id.rechargement_heure_tv);
        montantTv = itemView.findViewById(R.id.rechargement_montant_tv);
    }

    public void bind(Rechargement rechargement){
        dateTv.setText(rechargement.getDate());
        heureTv.setText(rechargement.getHeure());
        if (rechargement.getMontant()<0){
            montantTv.setText(String.valueOf(rechargement.getMontant()));
        } else{
            String text = "+" + rechargement.getMontant();
            montantTv.setText(text);
        }


        StatutRechargement statut = rechargement.getStatut();
        if (statut == StatutRechargement.SUCCES){
            montantTv.setTextColor(Color.GREEN);
        } else if (statut == StatutRechargement.EN_ATTENTE){
            montantTv.setTextColor(Color.YELLOW);
        } else if (statut == StatutRechargement.REFUSE){
            montantTv.setTextColor(Color.RED);
        }
    }

    static RechargementViewHolder create(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rechargement_item, parent, false);
        return new RechargementViewHolder(view);
    }
}
