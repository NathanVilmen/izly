package fr.utt.if26.vilmen_izly.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.vilmen_izly.Model.CarteBancaire;
import fr.utt.if26.vilmen_izly.Model.CarteBancaireRepository;

public class CarteBancaireViewModel extends AndroidViewModel {

    private CarteBancaireRepository carteBancaireRepository;
    public CarteBancaireViewModel(@NonNull Application application) {
        super(application);
        this.carteBancaireRepository = new CarteBancaireRepository(application);
    }

    public void insertCarteBancaire(CarteBancaire carteBancaire){
        carteBancaireRepository.insertCarteBancaire(carteBancaire);
    }
    public LiveData<List<CarteBancaire>> getAllCartesBancaires(){
        return carteBancaireRepository.getAllCartesBancaires();
    }
    public LiveData<List<CarteBancaire>> getCartesBancairesParIdentifiant(String identifiant){
        return carteBancaireRepository.getCartesBancairesParIdentifiant(identifiant);
    }

}
