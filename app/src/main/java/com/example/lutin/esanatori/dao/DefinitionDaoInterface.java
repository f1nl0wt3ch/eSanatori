package com.example.lutin.esanatori.dao;

import com.example.lutin.esanatori.model.RealmDefinitions;
import com.example.lutin.esanatori.model.ResponseDefinitions;
import com.example.lutin.esanatori.model.WordDetail;

import java.util.List;

/**
 * Created by Lutin on 8/23/17.
 */

public interface DefinitionDaoInterface {
    public void open();

    public void close();

    public void insertDefinitions(ResponseDefinitions responseDefinitions);

    public RealmDefinitions findDefinitionsByWord(String word);

    public RealmDefinitions isWordExist(String word);

    public List<RealmDefinitions> findAllDefinitions();

    public void deleteDefinitions(String word);

    public List<RealmDefinitions> findDefinitionsByDate(String date);

    public RealmDefinitions getRandom(int row);

    public int getRandomNumber(int size);

    public List<String> getListDate();

    public void deleteDenifitionsByDate(String[] dateList);

    public void clearDatabase();

    public List<String> findWordsByDate(String dateStr);

    public List<WordDetail> findDetailsByDate(String[] dateList);
}
