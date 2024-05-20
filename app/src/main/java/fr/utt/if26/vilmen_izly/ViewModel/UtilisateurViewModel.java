package fr.utt.if26.vilmen_izly.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import fr.utt.if26.vilmen_izly.Model.*;

import java.util.List;

public class UtilisateurViewModel extends AndroidViewModel {

    private UtilisateurRepository utilisateurRepository;
    private final LiveData<List<Utilisateur>> allUtilisateurs;

    public UtilisateurViewModel(@NonNull Application application) {
        super(application);
        this.utilisateurRepository = new UtilisateurRepository(application);
        this.allUtilisateurs = utilisateurRepository.getLiveUtilisateurList();
    }

    public LiveData<List<Utilisateur>> getAllUtilisateurs() {
        return allUtilisateurs;
    }

    public LiveData<Utilisateur> getUtilisateurParIdentifiant(String identifiant){
        return utilisateurRepository.getUtilisateurParIdentifiant(identifiant);
    }

    public void insert(Utilisateur utilisateur){
        utilisateurRepository.inserUtilisateur(utilisateur);
    }

    public void delete(Utilisateur utilisateur){
        utilisateurRepository.deleteUtilisateur(utilisateur);
    }

    public LiveData<Utilisateur> authentifierUtilisateur(String pseudo, int codeSecret) {
        return utilisateurRepository.authentifierUtilisateur(pseudo, codeSecret);
    }

    public void deconnecterUtilisateur(String identifiant) {
        utilisateurRepository.deconnecterUtilisateur(identifiant);
    }

    public void addToSoldeUtilisateur(double montant, String identifiant){
        utilisateurRepository.addToSoldeUtilisateur(montant, identifiant);
    }

    public void removeFromSoldeUtilisateur(double montant, String identifiant){
        utilisateurRepository.removeFromSoldeUtilisateur(montant, identifiant);
    }

}