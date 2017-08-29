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
import com.example.lutin.esanatori.dao.DefinitionDaoInterface;
import com.example.lutin.esanatori.dapter.DefinitionsAdapter;
import com.example.lutin.esanatori.model.RealmDefinition;
import com.example.lutin.esanatori.model.RealmDefinitions;
import com.example.lutin.esanatori.model.ResponseDefinition;
import com.example.lutin.esanatori.model.ResponseDefinitions;
import com.example.lutin.esanatori.service.WordsAPIService;

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
    private DefinitionDaoInterface dao = new DefinitionDao();
    private DefinitionsAdapter adapter;
    private RecyclerView recyclerView;
    private RealmList<RealmDefinition> list;


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

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordStr = word.getText().toString();
                Log.d(TAG, wordStr);
                if (wordStr.length() == 0) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, ERRORS.get(0), duration);
                    toast.show();
                } else {
                    Log.d(TAG, "Connection successful "+dao.toString());
                    realmDefinitions = dao.isWordExist(wordStr);
                    if(realmDefinitions != null){
                        Log.d(TAG, "Word is exist");
                        list = realmDefinitions.getRealmDefinitions();
                        Context context = getApplicationContext();
                        setupView(list,context);
                    } else {
                        Context context = getApplicationContext();
                        Log.d(TAG, "This word is not exsit in database");
                        fetchDefinitionThenStoreToRealm(wordStr, context);
                    }
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        dao.open();
    }

    private void setupView(RealmList<RealmDefinition> list, Context context){
        recyclerView = (RecyclerView) findViewById(R.id.definitionRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new DefinitionsAdapter(list, context);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "Show data to recyclerview");
    }


    private void fetchDefinitionThenStoreToRealm(final String wordStr, final Context context) {
        responseDefinitionsCall = apiService.getDefinitions(wordStr);
        responseDefinitionsCall.enqueue(new Callback<ResponseDefinitions>() {
            @Override
            public void onResponse(Call<ResponseDefinitions> call, Response<ResponseDefinitions> response) {
                if (response != null && response.isSuccessful()) {
                    responseDefinitions = response.body();
                    for(ResponseDefinition rd : responseDefinitions.getDefinitions()){
                        Log.d(TAG, rd.getDefinition());
                    }
                    //realmDefinitions.setWord(wordStr);
                    RealmList<RealmDefinition> list = new RealmList<>();
                    for(int i=0; i < responseDefinitions.getDefinitions().size(); i++){
                        RealmDefinition realmDefinition = new RealmDefinition();
                        realmDefinition.setPartOfSpeech(responseDefinitions.getDefinitions().get(i).getPartOfSpeech());
                        realmDefinition.setDefinition(responseDefinitions.getDefinitions().get(i).getDefinition());
                        list.add(realmDefinition);
                    }
                    setupView(list, context);
                    Log.d(TAG, "Definitions määrä "+responseDefinitions.getDefinitions().size());
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


