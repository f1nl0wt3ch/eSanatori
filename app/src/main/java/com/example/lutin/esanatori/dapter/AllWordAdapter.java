package com.example.lutin.esanatori.dapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.viewholder.AllWordViewHolder;

import java.util.List;

/**
 * Created by Lutin on 8/29/17.
 */

public class AllWordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dateList;
    private Context context;

    public AllWordAdapter(List<String> dateList, Context context) {
        this.dateList = dateList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allwords_layout, null);
        AllWordViewHolder viewHolder = new AllWordViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String dateStr = dateList.get(position);
        //((AllWordViewHolder) holder).dateTextView.setText(dateStr);
        ((AllWordViewHolder) holder).dateCheckBox.setText(dateStr);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
