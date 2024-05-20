package fr.utt.if26.vilmen_izly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.utt.if26.vilmen_izly.Model.Rechargement;
import fr.utt.if26.vilmen_izly.Model.RechargementRepository;
import fr.utt.if26.vilmen_izly.Model.StatutRechargement;

public class RechargementViewModel extends AndroidViewModel {

    private RechargementRepository rechargementRepository;

    public RechargementViewModel(@NonNull Application application) {
        super(application);
        this.rechargementRepository = new RechargementRepository(application);
    }

    public LiveData<List<Rechargement>> getRechargementsByIdentifiantUtilisateur(String identifiant){
        return rechargementRepository.getRechargementsByIdentifiantUtilisateur(identifiant);
    }

    public void insertRechargement(Rechargement rechargement){
        rechargementRepository.insertRechargement(rechargement);
    }

    public Rechargement createRechargement(double montant, String utilisateurId){
        Rechargement newRechargement;
        StatutRechargement statut = StatutRechargement.SUCCES;

        //Obtention de la date au format dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        // Obtention de l'heure actuelle au format HH:mm
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String currentTime = timeFormat.format(new Date());

        newRechargement = new Rechargement(utilisateurId, currentDate, currentTime, montant, statut);
        return newRechargement;
    }

}
