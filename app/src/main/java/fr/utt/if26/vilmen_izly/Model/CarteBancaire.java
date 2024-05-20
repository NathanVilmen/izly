package fr.utt.if26.vilmen_izly.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="carte_table")
public class CarteBancaire {
    @PrimaryKey(autoGenerate = true)
    private int cardId;
    @NonNull
    private String cardName;
    private String cardNumber;
    private String CCV;
    private String dateFinValidite;
    @NonNull
    private String utilisateurId; // clé étrangère sur l'utilisateur

    public CarteBancaire(@NonNull String cardName, String cardNumber, String CCV, String dateFinValidite, @NonNull String utilisateurId) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.CCV = CCV;
        this.dateFinValidite = dateFinValidite;
        this.utilisateurId = utilisateurId;
    }

    @NonNull
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @NonNull
    public String getCardName() {
        return cardName;
    }

    public void setCardName(@NonNull String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    @NonNull
    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(@NonNull String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    @Override
    public String toString() {
        return "CarteBancaire{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", CCV='" + CCV + '\'' +
                ", dateFinValidite='" + dateFinValidite + '\'' +
                ", utilisateurId='" + utilisateurId + '\'' +
                '}';
    }
}
