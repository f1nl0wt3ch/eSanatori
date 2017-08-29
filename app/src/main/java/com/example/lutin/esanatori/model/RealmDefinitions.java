package com.example.lutin.esanatori.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lutin on 8/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealmDefinitions extends RealmObject {

    @Index
    private String dateStr = getToday();
    @PrimaryKey
    private String word;
    private RealmList<RealmDefinition> realmDefinitions;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public RealmList<RealmDefinition> getRealmDefinitions() {
        return realmDefinitions;
    }

    public void setRealmDefinitions(RealmList<RealmDefinition> realmDefinitions) {
        this.realmDefinitions = realmDefinitions;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public final String getToday() {
        Date date = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
        return simple.format(date);
    }
}
