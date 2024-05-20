package fr.utt.if26.vilmen_izly.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RechargementRepository {
    private RechargementDao rechargementDao;

    public RechargementRepository(Application application) {
        RoomDatabase bd = RoomDatabase.getDatabaseFromJava(application);
        rechargementDao = bd.rechargementDao();
    }

    public void insertRechargement(Rechargement rechargement) {
        rechargementDao.insertRechargement(rechargement);
    }

    public LiveData<List<Rechargement>> getAllRechargements() {
        return rechargementDao.getAllRechargements();
    }

    public LiveData<List<Rechargement>> getRechargementsByIdentifiantUtilisateur(String identifiant){
        return rechargementDao.getRechargementsByIdentifiantUtilisateur(identifiant);
    }
}
