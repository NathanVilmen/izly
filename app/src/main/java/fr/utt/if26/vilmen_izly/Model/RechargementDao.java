package fr.utt.if26.vilmen_izly.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RechargementDao {

    @Insert
    void insertRechargement(Rechargement rechargement);
    @Query("DELETE FROM table_rechargement")
    void deleteAllRechargements();
    @Query("DELETE FROM table_rechargement WHERE utilisateurId = :user_identifiant")
    public void deleteRechargementsByPseudo(String user_identifiant);
    @Query("DELETE FROM table_rechargement WHERE rechargementId = :id")
    public void deleteRechargementById(int id);
    @Query("SELECT * FROM table_rechargement ORDER BY rechargementId DESC")
    public LiveData<List<Rechargement>> getAllRechargements(); // ordonné par id
    @Query("SELECT * FROM table_rechargement WHERE rechargementId = :id")
    public LiveData<Rechargement> getRechargementParId(int id);
    @Query("SELECT * FROM table_rechargement WHERE utilisateurId = :user_identifiant ORDER BY rechargementId DESC")
    public LiveData<List<Rechargement>> getRechargementsByIdentifiantUtilisateur(String user_identifiant);

}
