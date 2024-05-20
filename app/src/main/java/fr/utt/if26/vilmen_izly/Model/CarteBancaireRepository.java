package fr.utt.if26.vilmen_izly.Model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarteBancaireRepository {
    private CarteBancaireDao carteBancaireDao;

    public CarteBancaireRepository(Application application) {
        RoomDatabase bd = RoomDatabase.getDatabaseFromJava(application);
        carteBancaireDao = bd.carteBancaireDao();
    }

    public void insertCarteBancaire(CarteBancaire carteBancaire){
        carteBancaireDao.insertCarteBancaire(carteBancaire);
        Log.i("New CB", "Carte bancaire ajoutée : "+carteBancaire.toString());
    }
    public LiveData<List<CarteBancaire>> getAllCartesBancaires(){
        return carteBancaireDao.getAllCartesBancaires();
    }
    public LiveData<List<CarteBancaire>> getCartesBancairesParIdentifiant(String identifiant){
        return carteBancaireDao.getCartesBancairesParIdentifiant(identifiant);
    }
}
