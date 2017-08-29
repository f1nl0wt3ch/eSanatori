package com.example.lutin.esanatori.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lutin.esanatori.R;

/**
 * Created by Lutin on 8/29/17.
 */

public class AllWordViewHolder extends RecyclerView.ViewHolder {
    public TextView dateTextView;
    public CheckBox dateCheckBox;
    public AllWordViewHolder(View itemView) {
        super(itemView);
        this.dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        this.dateCheckBox = (CheckBox) itemView.findViewById(R.id.dateCheckBox);
    }
}
