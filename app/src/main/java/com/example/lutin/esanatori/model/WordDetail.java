package com.example.lutin.esanatori.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Lutin on 8/30/17.
 */

public class WordDetail extends BaseObservable {
    @Bindable
    private String word;
    @Bindable
    private String detail;

    public WordDetail(String word, String detail) {
        this.word = word;
        this.detail = detail;
    }

    public WordDetail() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
