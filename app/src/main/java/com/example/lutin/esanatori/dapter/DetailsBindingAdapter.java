package com.example.lutin.esanatori.dapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.binding.DetailsViewHolder;
import com.example.lutin.esanatori.databinding.DetailsLayoutBinding;
import com.example.lutin.esanatori.model.WordDetail;

import java.util.List;

/**
 * Created by Lutin on 8/30/17.
 */

public class DetailsBindingAdapter extends RecyclerView.Adapter {
    private List<WordDetail> wordDetailList;

    public DetailsBindingAdapter(List<WordDetail> wordDetailList) {
        this.wordDetailList = wordDetailList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DetailsLayoutBinding view = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.details_layout, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DetailsViewHolder) holder).bind(wordDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordDetailList.size();
    }
}
