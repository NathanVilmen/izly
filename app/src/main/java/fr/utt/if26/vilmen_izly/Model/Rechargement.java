package fr.utt.if26.vilmen_izly.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="table_rechargement")
public class Rechargement {

    @PrimaryKey(autoGenerate = true)
    private int rechargementId;

    /**
     * Clé étrangère sur l'utilisateur.
     */
    @NonNull
    private String utilisateurId;
    private String date;
    private String heure;
    private double montant;
    private StatutRechargement statut;

    public Rechargement(@NonNull String utilisateurId, String date, String heure, double montant, StatutRechargement statut) {
        this.utilisateurId = utilisateurId;
        this.date = date;
        this.heure = heure;
        this.montant = montant;
        this.statut = statut;
    }

    public int getRechargementId() {
        return rechargementId;
    }

    public void setRechargementId(int rechargementId) {
        this.rechargementId = rechargementId;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public StatutRechargement getStatut() {
        return statut;
    }

    public void setStatut(StatutRechargement statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Rechargement{" +
                "rechargementId=" + rechargementId +
                ", utilisateurId='" + utilisateurId + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", montant=" + montant +
                ", statut=" + statut +
                '}';
    }
}
