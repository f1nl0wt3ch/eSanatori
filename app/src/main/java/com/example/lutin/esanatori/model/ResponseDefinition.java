package com.example.lutin.esanatori.model;

/**
 * Created by Lutin on 8/23/17.
 */

public class ResponseDefinition {
    private String definition;
    private String partOfSpeech;

    public ResponseDefinition() {

    }

    public ResponseDefinition(String definition, String partOfSpeech) {
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
    }

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


}
