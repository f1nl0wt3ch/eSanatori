package com.example.lutin.esanatori.dapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.dao.DefinitionDao;
import com.example.lutin.esanatori.dao.DefinitionDaoInterface;
import com.example.lutin.esanatori.viewholder.AllWordViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lutin on 8/29/17.
 */

public class AllWordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dateList;
    private Context context;
    static final List<String> selectedDate = new ArrayList<>();
    private DefinitionDaoInterface dao = new DefinitionDao();

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        dao.open();
        String dateStr = dateList.get(position);
        ((AllWordViewHolder) holder).dateCheckBox.setText(dateStr+" [ "+dao.findWordsByDate(dateStr).size()+" words found ]");
        ((AllWordViewHolder) holder).dateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                    ((AllWordViewHolder) holder).dateCheckBox.setChecked(true);
                    ((AllWordViewHolder) holder).dateCheckBox.setChecked(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
