package fr.utt.if26.vilmen_izly.Model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Utilisateur.class, Rechargement.class, CarteBancaire.class}, version = 6, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract UtilisateurDao utilisateurDAO();
    public abstract RechargementDao rechargementDao();
    public abstract CarteBancaireDao carteBancaireDao();

    private static volatile RoomDatabase INSTANCE;

    public static RoomDatabase getDatabaseFromJava(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDatabase.class, "izly_room.db")
                            /*.addCallback(roomDatabasePopulateJava)*/
                            .allowMainThreadQueries()
                            /*.fallbackToDestructiveMigration()*/
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static androidx.room.RoomDatabase.Callback roomDatabasePopulateJava =
            new androidx.room.RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() -> {

                        UtilisateurDao utilisateurDAO = INSTANCE.utilisateurDAO();
                        utilisateurDAO.deleteAllUtilisateurs();

                        RechargementDao rechargementDao = INSTANCE.rechargementDao();
                        CarteBancaireDao carteBancaireDao = INSTANCE.carteBancaireDao();

                        Utilisateur utilisateur1 = new Utilisateur("nathan.vilmen", "Vilmen",
                                "Nathan", "27/09/2001", "1234",
                                "1234567890123", "10 rue des Tilleuls", 67750,
                                "Scherwiller", "France", "nathan.vilmen@utt.fr",
                                "0782692042", StatutEtudiant.BOURSIER, true);

                        utilisateurDAO.insertUtilisateur(utilisateur1);

                        Rechargement rechargement1 = new Rechargement("nathan.vilmen",
                                "07/01/2024", "11:23", 10, StatutRechargement.SUCCES);

                        rechargementDao.insertRechargement(rechargement1);
                        utilisateurDAO.updateSoldeUtilisateur(10.00, "nathan.vilmen");

                        CarteBancaire cb1 = new CarteBancaire("Carte BNP", "12345678910", "007", "11/11/24", "nathan.vilmen");
                        CarteBancaire cb2 = new CarteBancaire("Carte LCL", "12345678910", "118", "12/12/24", "nathan.vilmen");
                        carteBancaireDao.insertCarteBancaire(cb1);
                        carteBancaireDao.insertCarteBancaire(cb2);


                        Utilisateur utilisateur2 = new Utilisateur("clara.faso", "Faso",
                                "Clara", "22/01/2002", "123456",
                                "1234567890123", "80 rue de Montreuil", 75000,
                                "Paris", "France", "clara.faso@dau.fr",
                                "0102030405", StatutEtudiant.NORMAL, false);

                        utilisateurDAO.insertUtilisateur(utilisateur2);

                        Log.i("RoomDatabase", "Creation de utilisateur1 et utilisateur2");
                    });
                }
            };
}
