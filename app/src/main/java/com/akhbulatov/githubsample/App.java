package com.akhbulatov.githubsample;

import android.app.Application;

import com.akhbulatov.githubsample.data.global.DataManager;
import com.akhbulatov.githubsample.data.global.DataManagerImpl;
import com.akhbulatov.githubsample.data.global.factories.DatabaseFactory;
import com.akhbulatov.githubsample.data.global.factories.NetworkFactory;
import com.akhbulatov.githubsample.data.global.factories.PrefsFactory;

public class App extends Application {

    private static DataManager dataManager;

    public static DataManager getDataManager() {
        return dataManager;
    }

    @Override public void onCreate() {
        super.onCreate();
        initDataManager();
    }

    private void initDataManager() {
        dataManager = new DataManagerImpl(
                NetworkFactory.getApi(),
                DatabaseFactory.getDatabase(this),
                PrefsFactory.getPrefsHelper(this)
        );
    }
}
