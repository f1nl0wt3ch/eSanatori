package com.example.lutin.esanatori.service;

import com.example.lutin.esanatori.model.ResponseDefinitions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * Created by Lutin on 8/17/17.
 */

public interface WordsAPIServiceInterface {
    @Headers({
            "X-Mashape-Key:zSJ0lQwYhxmshbRe6hwAGx5SRWkbp1T4HLsjsnOJ6S60XD4RR2",
            "Accept:application/json"
    })
    @GET("words/{word}/definitions")
    Call<ResponseDefinitions> getDefinitions(@Path("word") String word);


}

