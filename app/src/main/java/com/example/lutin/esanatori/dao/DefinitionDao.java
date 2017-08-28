package com.example.lutin.esanatori.dao;

import android.util.Log;

import com.example.lutin.esanatori.model.RealmDefinitions;
import com.example.lutin.esanatori.model.ResponseDefinitions;

import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

/**
 * Created by Lutin on 8/23/17.
 */

public class DefinitionDao implements DefinitionDaoInterface {

    private Realm realm;

    @Override
    public void open() {
        realm.getDefaultConfiguration();
        Log.d(TAG, "Open database connection");
    }

    @Override
    public void close() {
        realm.close();
        Log.d(TAG, "Close database connection");
    }

    @Override
    public void insertDefinitions(ResponseDefinitions response) {
        Log.d(TAG, "Create new definitions");
        final RealmDefinitions realmDefinitions = new RealmDefinitions();
        realmDefinitions.setWord(response.getWord());
        realmDefinitions.setRealmDefinitions(response.getDefinitions());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(realmDefinitions);
            }
        });
        Log.d(TAG, "New definitions was created successful");
    }

    @Override
    public RealmDefinitions isWordExist(String word) {
        Log.d(TAG, "Find definition by word");
        realm.beginTransaction();
        RealmDefinitions result = realm.where(RealmDefinitions.class).equalTo("word", word).findFirst();
        realm.commitTransaction();
        return (result == null) ? null : result;
    }

    @Override
    public List<RealmDefinitions> findAllDefinitions() {
        Log.d(TAG, "Find all definitions in database ");
        realm.beginTransaction();
        List<RealmDefinitions> allDefinitions = realm.where(RealmDefinitions.class).findAll();
        realm.commitTransaction();
        return (allDefinitions.size() == 0) ? null : allDefinitions;
    }

    @Override
    public void deleteDefinitions(String word) {
        Log.d(TAG, "Starting delete a definitions ");
        realm.beginTransaction();
        realm.where(RealmDefinitions.class).contains("word", word).findFirst().deleteFromRealm();
        realm.commitTransaction();

    }

    @Override
    public List<RealmDefinitions> findDefinitionsByDate(String dateStr) {
        Log.d(TAG, "Finding definitions by date ");
        realm.beginTransaction();
        List<RealmDefinitions> list = realm.where(RealmDefinitions.class).contains("dateStr", dateStr).findAll();
        return (list.size() == 0) ? null : list;
    }

    final int getRandomNumber(int size) {
        Random random = new Random(size);
        return random.nextInt();
    }

    @Override
    public RealmDefinitions findDefinitionsByWord(String word) {
        realm.beginTransaction();
        RealmDefinitions definitions = realm.where(RealmDefinitions.class).contains("word", word).findFirst();
        realm.commitTransaction();
        return definitions;
    }

    @Override
    public RealmDefinitions getRandom(int row) {
        Log.d(TAG, "Get random word from database ");
        RealmResults<RealmDefinitions> list = realm.where(RealmDefinitions.class).findAll();
        return list.get(row);
    }
}
