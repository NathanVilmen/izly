package fr.utt.if26.vilmen_izly.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UtilisateurDao {

    @Insert
    public void insertUtilisateur(Utilisateur utilisateur);
    @Update
    public void updateUtilisateur(Utilisateur utilisateur);
    @Delete
    public void deleteUtilisateurByEntity(Utilisateur utilisateur);
    @Query("DELETE FROM table_utilisateur")
    void deleteAllUtilisateurs();
    @Query("DELETE FROM table_utilisateur WHERE identifiant = :user_identifiant")
    public void deleteUtilisateurByPseudo(String user_identifiant);
    @Query("SELECT * FROM table_utilisateur ORDER BY identifiant")
    public LiveData<List<Utilisateur>> getAllUtilisateurs(); // ordonné par identifiant
    @Query("SELECT * FROM table_utilisateur WHERE identifiant = :user_identifiant")
    public LiveData<Utilisateur> getUtilisateurParIdentifiant(String user_identifiant);
    @Query("SELECT * FROM table_utilisateur WHERE identifiant = :user_identifiant AND `code secret` = :codeSecret")
    public LiveData<Utilisateur> authentifierUtilisateur(String user_identifiant, int codeSecret);
    @Query("UPDATE table_utilisateur SET estConnecte = 1 WHERE identifiant = :user_identifiant")
    public void setUtilisateurConnecte(String user_identifiant);
    @Query("UPDATE table_utilisateur SET estConnecte = 0 WHERE identifiant = :user_identifiant")
    public void deconnecterUtilisateur(String user_identifiant);
    @Query("UPDATE table_utilisateur SET solde = :solde WHERE identifiant = :user_identifiant")
    public void updateSoldeUtilisateur(double solde, String user_identifiant);
    @Query("SELECT solde FROM table_utilisateur WHERE identifiant = :user_identifiant")
    public double getSolde(String user_identifiant);

}
