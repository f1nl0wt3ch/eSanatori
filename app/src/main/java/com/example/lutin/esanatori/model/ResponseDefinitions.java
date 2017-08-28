package com.example.lutin.esanatori.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Lutin on 8/23/17.
 */

public class ResponseDefinitions extends RealmObject {
    private String word;
    private RealmList<ResponseDefinition> definitions;

    public ResponseDefinitions(String word, RealmList<ResponseDefinition> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public RealmList<ResponseDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(RealmList<ResponseDefinition> definitions) {
        this.definitions = definitions;
    }
}
