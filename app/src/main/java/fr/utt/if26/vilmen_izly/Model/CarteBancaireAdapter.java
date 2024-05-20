package fr.utt.if26.vilmen_izly.Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CarteBancaireAdapter extends ArrayAdapter<CarteBancaire> {

    public CarteBancaireAdapter(@NonNull Context context, @NonNull List<CarteBancaire> cartesBancaires) {
        super(context, android.R.layout.simple_spinner_item, cartesBancaires);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        CarteBancaire carte = (CarteBancaire) getItem(position);
        if (carte != null) {
            view.setText(carte.getCardName());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        CarteBancaire carte = (CarteBancaire) getItem(position);
        if (carte != null) {
            view.setText(carte.getCardName());
        }
        return view;
    }
}