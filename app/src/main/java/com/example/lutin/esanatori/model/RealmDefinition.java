package com.example.lutin.esanatori.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lutin on 8/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealmDefinition extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String definition;
    private String partOfSpeech;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
