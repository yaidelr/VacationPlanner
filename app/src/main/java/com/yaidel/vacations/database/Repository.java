package com.yaidel.vacations.database;

import android.app.Application;

import com.yaidel.vacations.dao.ExcursionDAO;
import com.yaidel.vacations.dao.VacationDAO;
import com.yaidel.vacations.entity.Excursion;
import com.yaidel.vacations.entity.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    public static ExcursionDAO mexcursionDao;
    public static VacationDAO mvacationDao;

    private List<Vacation> mAllVacations;
    private List<Excursion> mAllExcursions;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        VacationDatabaseBuilder db = VacationDatabaseBuilder.getDatabase(application);
        mvacationDao = db.vacationDAO();
        mexcursionDao = db.excursionDAO();
    }

    //** VACATIONS **
    // get all vacations
    public List<Vacation> getAllVacations() {
        databaseWriteExecutor.execute(() -> {
            mAllVacations = mvacationDao.getAllVacations();
        });

        // gives time for database operation to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllVacations;
    }

    // insert vacation
    public void insert(Vacation vacation){
        databaseWriteExecutor.execute(()-> {
            mvacationDao.insert(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // update vacation
    public void update(Vacation vacation) {
        databaseWriteExecutor.execute(()-> {
            mvacationDao.update(vacation);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // delete vacation
    public void delete (Vacation vacation){
        databaseWriteExecutor.execute(()-> {
            mvacationDao.delete(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    //** EXCURSIONS **
    // get all excursions
    public List<Excursion>getAllExcursions(){
        databaseWriteExecutor.execute(()->{
            mAllExcursions=mexcursionDao.getAllExcursions();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllExcursions;
    }

    // get associated excursions
    public List<Excursion>getAssociatedExcursions(int vacationID){
        databaseWriteExecutor.execute(()->{
            mAllExcursions=mexcursionDao.getAssociatedExcursions(vacationID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllExcursions;
    }

    // insert excursion
    public void insert(Excursion excursion){
        databaseWriteExecutor.execute(()->{
            mexcursionDao.insert(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // update excursion
    public void update(Excursion excursion){
        databaseWriteExecutor.execute(()->{
            mexcursionDao.update(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // delete excursion
    public void delete (Excursion excursion){
        databaseWriteExecutor.execute(()->{
            mexcursionDao.delete(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//end delete

    // get excursion by id
    public Excursion getExcursionById(int excursionId) {
        final Excursion[] excursion = new Excursion[1];
        databaseWriteExecutor.execute(() -> {
            excursion[0] = mexcursionDao.getExcursionById(excursionId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return excursion[0];
    }


}//end class



