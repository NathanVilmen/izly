package fr.utt.if26.vilmen_izly.Model;

import java.io.Serializable;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "table_utilisateur")
public class Utilisateur implements Serializable {
    @PrimaryKey
    @NonNull
    private String identifiant;
    @ColumnInfo(name = "sel")
    private byte[] salt;
    @NonNull
    private String nom;
    @NonNull
    @ColumnInfo(name = "prénom")
    private String prenom;
    @ColumnInfo(name = "date de naissance")
    private String dateNaissance;

    @ColumnInfo(name = "code secret")
    private String codeSecret;

    private String iban;
    private float solde;
    @ColumnInfo(name = "adresse")
    private String adresse;
    @ColumnInfo(name = "code postal")
    private int codePostal;
    private String ville;
    private String pays;
    private String email;
    @ColumnInfo(name = "numéro de téléphone")
    private String telephone;
    private StatutEtudiant statut;
    @ColumnInfo(name = "accepte les conditions")
    private boolean accepteConditions;
    private boolean estConnecte;


    public Utilisateur(@NonNull String identifiant, @NonNull String nom, @NonNull String prenom,
                       String dateNaissance, String codeSecret, String iban, String adresse,
                       int codePostal, String ville, String pays, String email,
                       String telephone, StatutEtudiant statut, boolean accepteConditions) {

        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.codeSecret = codeSecret;
        this.iban = iban;
        this.solde = 0;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.email = email;
        this.telephone = telephone;
        this.statut = statut;
        this.accepteConditions = accepteConditions;
        this.estConnecte = false;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "identifiant='" + identifiant + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", iban='" + iban + '\'' +
                ", solde=" + solde +
                ", adresse='" + adresse + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", statut=" + statut +
                ", estConnecte=" + estConnecte +
                '}';
    }

    @NonNull
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(@NonNull String identifiant) {
        this.identifiant = identifiant;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NonNull String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public StatutEtudiant getStatut() {
        return statut;
    }

    public void setStatut(StatutEtudiant statut) {
        this.statut = statut;
    }

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }

    public boolean isAccepteConditions() {
        return accepteConditions;
    }

    public void setAccepteConditions(boolean accepteConditions) {
        this.accepteConditions = accepteConditions;
    }

    public String getStatutName() {
        return statut.name();
    }

}
