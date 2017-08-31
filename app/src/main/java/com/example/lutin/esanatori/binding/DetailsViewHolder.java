package com.example.lutin.esanatori.binding;

import android.support.v7.widget.RecyclerView;

import com.example.lutin.esanatori.databinding.DetailsLayoutBinding;
import com.example.lutin.esanatori.model.WordDetail;

/**
 * Created by Lutin on 8/30/17.
 */

public class DetailsViewHolder extends RecyclerView.ViewHolder {
    DetailsLayoutBinding binding;

    public DetailsViewHolder(DetailsLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(WordDetail object) {
        binding.setDetail(object);
    }

}
