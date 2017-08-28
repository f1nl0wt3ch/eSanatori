package com.example.lutin.esanatori.dapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.model.ResponseDefinition;
import com.example.lutin.esanatori.viewholder.CustomViewHolder;
import com.example.lutin.esanatori.viewholder.DefinitionViewHolder;

import java.util.zip.Inflater;

import io.realm.RealmList;

/**
 * Created by Lutin on 8/21/17.
 */

public class DefinitionsAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private RealmList<ResponseDefinition> responseDefinitions;
    private Context context;

    public DefinitionsAdapter(RealmList<ResponseDefinition> responseDefinitions, Context context) {
        this.responseDefinitions = responseDefinitions;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_layout, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ResponseDefinition responseDefinition = responseDefinitions.get(position);
        holder.getType().setText(responseDefinition.getPartOfSpeech());
        holder.getDefinition().setText(responseDefinition.getDefinition());
    }

    @Override
    public int getItemCount() {
        return responseDefinitions.size();
    }
}
