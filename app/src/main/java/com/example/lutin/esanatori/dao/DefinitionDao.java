package com.example.lutin.esanatori.dao;

import android.util.Log;

import com.example.lutin.esanatori.model.RealmDefinition;
import com.example.lutin.esanatori.model.RealmDefinitions;
import com.example.lutin.esanatori.model.ResponseDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

/**
 * Created by Lutin on 8/23/17.
 */

public class DefinitionDao implements DefinitionDaoInterface {

    private Realm realm;

    @Override
    public void open() {
        realm = Realm.getDefaultInstance();
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
        RealmDefinitions realmDefinitions = new RealmDefinitions();
        RealmList<RealmDefinition> list = new RealmList<>();
        for (int i = 0; i < response.getDefinitions().size(); i++) {
            RealmDefinition realmDefinition = new RealmDefinition();
            realmDefinition.setPartOfSpeech(response.getDefinitions().get(i).getPartOfSpeech());
            realmDefinition.setDefinition(response.getDefinitions().get(i).getDefinition());
            list.add(realmDefinition);
        }
        realmDefinitions.setRealmDefinitions(list);
        realmDefinitions.setWord(response.getWord());
        realm.beginTransaction();
        realm.copyToRealm(realmDefinitions);
        realm.commitTransaction();
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

    public int getRandomNumber(int size) {
        Random rm = new Random();
        int row = rm.nextInt(size);
        return row;
    }

    @Override
    public List<String> getListDate() {
        realm.beginTransaction();
        RealmResults<RealmDefinitions> results = realm.where(RealmDefinitions.class).distinct("dateStr");
        realm.commitTransaction();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i).getDateStr());
        }
        return list;
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
        realm.beginTransaction();
        RealmResults<RealmDefinitions> list = realm.where(RealmDefinitions.class).findAll();
        realm.commitTransaction();
        return list.get(row);
    }

    @Override
    public void deleteDenifitionsByDate(List<String> dateList) {
        realm.beginTransaction();
        for (String date : dateList) {
            RealmResults<RealmDefinitions> results = realm.where(RealmDefinitions.class).contains("dateStr", date).findAll();
            results.deleteAllFromRealm();
            Log.d("This date ", date + " has been deleted");
        }
        realm.commitTransaction();
    }

    public void clearDatabase() {
        realm.beginTransaction();
        RealmResults<RealmDefinitions> results = realm.where(RealmDefinitions.class).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public List<String> findWordsByDate(String dateStr) {
        List<String> words = new ArrayList<>();
        realm.beginTransaction();
        RealmResults<RealmDefinitions> results = realm.where(RealmDefinitions.class).contains("dateStr", dateStr).findAll();
        realm.commitTransaction();
        for(int i=0; i < results.size();i++){
            words.add(results.get(i).getWord());
        }
        return words;
    }
}
