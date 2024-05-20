package fr.utt.if26.vilmen_izly.Model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class UtilisateurRepository {
    private final UtilisateurDao utilisateurDAO;
    private final LiveData<List<Utilisateur>> liveUtilisateurList;

    public UtilisateurRepository(Application application){
        RoomDatabase bd = RoomDatabase.getDatabaseFromJava(application);
        utilisateurDAO = bd.utilisateurDAO();
        liveUtilisateurList = utilisateurDAO.getAllUtilisateurs();
    }

    public LiveData<List<Utilisateur>> getLiveUtilisateurList() {
        return liveUtilisateurList;
    }

    public LiveData<Utilisateur> getUtilisateurParIdentifiant(String identifiant) {
        return utilisateurDAO.getUtilisateurParIdentifiant(identifiant);
    }

    public void inserUtilisateur(Utilisateur utilisateur){

        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.insertUtilisateur(utilisateur);
        });
    }

    public void deleteUtilisateur(Utilisateur utilisateur){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.deleteUtilisateurByEntity(utilisateur);
        });
    }

    public LiveData<Utilisateur> authentifierUtilisateur(String identifiant, int codeSecret) {
        LiveData<Utilisateur> utilisateurLiveData = utilisateurDAO.authentifierUtilisateur(identifiant, codeSecret);

        MediatorLiveData<Utilisateur> resultLiveData = new MediatorLiveData<>();
        resultLiveData.addSource(utilisateurLiveData, new Observer<Utilisateur>() {
            @Override
            public void onChanged(Utilisateur utilisateur) {
                if (utilisateur != null) {
                    // Authentification réussie, mettre à jour l'état de connexion
                    setUtilisateurConnecte(identifiant);

                    // Propager l'utilisateur authentifié
                    resultLiveData.setValue(utilisateur);
                } else {
                    // Authentification échouée
                    resultLiveData.setValue(null);
                }

                // Ne plus observer le LiveData après la première mise à jour
                resultLiveData.removeSource(utilisateurLiveData);
            }
        });

        return resultLiveData;
    }

    private void setUtilisateurConnecte(String identifiant) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.setUtilisateurConnecte(identifiant);
        });
    }

    public void deconnecterUtilisateur(String identifiant) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.deconnecterUtilisateur(identifiant);
        });
        Log.i("User", "Utilisateur déconnecté :" + identifiant);
    }

    public void addToSoldeUtilisateur(double montant, String identifiant){
        double previousSolde = utilisateurDAO.getSolde(identifiant);
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.updateSoldeUtilisateur(previousSolde+montant, identifiant);
        });
    }

    public void removeFromSoldeUtilisateur(double montant, String identifiant){
        double previousSolde = utilisateurDAO.getSolde(identifiant);
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            utilisateurDAO.updateSoldeUtilisateur(previousSolde-montant, identifiant);
        });
    }

}
