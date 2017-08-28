package com.example.lutin.esanatori;

import android.support.multidex.MultiDexApplication;

import com.example.lutin.esanatori.service.WordsAPIService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Lutin on 8/17/17.
 */

public class EsanatoriApplication extends MultiDexApplication {
    private WordsAPIService wordsAPIService;
    private static EsanatoriApplication singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build());
        wordsAPIService = new WordsAPIService();
    }

    public static EsanatoriApplication getInstance() {
        return singleton;
    }

    public WordsAPIService getWordsAPIService() {
        return wordsAPIService;
    }
}
