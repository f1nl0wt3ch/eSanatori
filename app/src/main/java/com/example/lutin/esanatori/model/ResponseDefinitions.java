package com.example.lutin.esanatori.model;

import java.util.List;

/**
 * Created by Lutin on 8/23/17.
 */

public class ResponseDefinitions {
    private String word;
    private List<ResponseDefinition> definitions;

    public ResponseDefinitions(){
        super();
    }

    public ResponseDefinitions(String word, List<ResponseDefinition> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<ResponseDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<ResponseDefinition> definitions) {
        this.definitions = definitions;
    }
}
