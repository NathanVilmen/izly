package fr.utt.if26.vilmen_izly.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarteBancaireDao {

    @Insert
    public void insertCarteBancaire(CarteBancaire carteBancaire);
    @Query("DELETE FROM carte_table")
    void deleteAllCartesBancaires();
    @Query("SELECT * FROM carte_table ORDER BY cardId")
    public LiveData<List<CarteBancaire>> getAllCartesBancaires();
    @Query("SELECT * FROM carte_table WHERE utilisateurId = :identifiant ORDER BY cardId")
    public LiveData<List<CarteBancaire>> getCartesBancairesParIdentifiant(String identifiant);
}
