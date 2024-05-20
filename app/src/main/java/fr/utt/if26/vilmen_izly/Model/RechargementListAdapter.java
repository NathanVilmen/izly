package fr.utt.if26.vilmen_izly.Model;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import android.view.ViewGroup;

public class RechargementListAdapter extends ListAdapter<Rechargement, RechargementViewHolder> {
    public RechargementListAdapter(@NonNull DiffUtil.ItemCallback<Rechargement> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RechargementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RechargementViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RechargementViewHolder holder, int position) {
        Rechargement current = getItem(position);
        holder.bind(current);
    }

    public static class RechargementDiff extends DiffUtil.ItemCallback<Rechargement> {

        @Override
        public boolean areItemsTheSame(@NonNull Rechargement oldItem, @NonNull Rechargement newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Rechargement oldItem, @NonNull Rechargement newItem) {
            return oldItem.getRechargementId() == (newItem.getRechargementId());
        }
    }
}
