package com.example.lutin.esanatori.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lutin.esanatori.EsanatoriApplication;
import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.dao.DefinitionDao;
import com.example.lutin.esanatori.dapter.DefinitionsAdapter;
import com.example.lutin.esanatori.model.RealmDefinitions;
import com.example.lutin.esanatori.model.ResponseDefinition;
import com.example.lutin.esanatori.model.ResponseDefinitions;
import com.example.lutin.esanatori.service.WordsAPIService;
import com.example.lutin.esanatori.view.DefinitionRecyclerView;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private EditText word;
    private Button searchBtn;
    private String KEYWORD = "bump";
    private String TAG = "WordsAPI";
    private WordsAPIService apiService;
    private Call<ResponseDefinitions> responseDefinitionsCall;
    private ResponseDefinitions responseDefinitions;
    int duration = Toast.LENGTH_LONG;
    private RealmDefinitions realmDefinitions;

    /*Variables for adding data to recyclerview*/
    private RecyclerView.LayoutManager layoutManager;
    private DefinitionDao dao;
    private DefinitionsAdapter adapter;
    private RecyclerView recyclerView;


    final static String APP_INFO = "eSanatori - Dinh Duc Thinh";
    final static List<String> ERRORS = Arrays.asList(
            "Oopps, you have not entered word yet, please enter any word",
            "Oopps, your input is not a word"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = EsanatoriApplication.getInstance().getWordsAPIService();
        word = (EditText) findViewById(R.id.enterWordText);
        final Context context = getApplicationContext();

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (word.getText().length() == 0) {

                    Toast toast = Toast.makeText(context, ERRORS.get(0), duration);
                    toast.show();
                } else {
                    dao.open();
                    realmDefinitions = dao.isWordExist(word.toString());
                    if(realmDefinitions != null){
                        recyclerView = (RecyclerView) findViewById(R.id.definitionRecyclerView);
                        layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);
                        RealmList<ResponseDefinition> list = realmDefinitions.getRealmDefinitions();
                        adapter = new DefinitionsAdapter(list, context);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.d(TAG, "This word is not exsit in database");
                    }
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void fetchDefinitionThenStoreToRealm(String wordStr) {
        responseDefinitionsCall = apiService.getDefinitions(wordStr);
        responseDefinitionsCall.enqueue(new Callback<ResponseDefinitions>() {
            @Override
            public void onResponse(Call<ResponseDefinitions> call, Response<ResponseDefinitions> response) {
                if (response != null && response.isSuccessful() == true) {
                    responseDefinitions = response.body();
                    dao.insertDefinitions(responseDefinitions);
                }
            }

            @Override
            public void onFailure(Call<ResponseDefinitions> call, Throwable t) {
                Log.d("Failure ", "Cannot fetch data from Rest API " + t.getMessage());
            }
        });

    }


}


