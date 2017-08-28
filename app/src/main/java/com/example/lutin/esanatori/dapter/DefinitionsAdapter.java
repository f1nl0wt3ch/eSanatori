package com.example.lutin.esanatori.dapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.model.RealmDefinition;
import com.example.lutin.esanatori.viewholder.CustomViewHolder;

import io.realm.RealmList;

/**
 * Created by Lutin on 8/21/17.
 */

public class DefinitionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmList<RealmDefinition> realmDefinitionRealmList;
    private Context context;

    public DefinitionsAdapter(RealmList<RealmDefinition> realmDefinitionRealmList, Context context) {
        this.realmDefinitionRealmList = realmDefinitionRealmList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RealmDefinition realmDefinition = realmDefinitionRealmList.get(position);
        ((CustomViewHolder) holder).type.setText(realmDefinition.getPartOfSpeech());
        ((CustomViewHolder) holder).definition.setText(realmDefinition.getDefinition());

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_layout, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return realmDefinitionRealmList.size();
    }
}
