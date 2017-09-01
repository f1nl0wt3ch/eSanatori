package com.example.lutin.esanatori.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lutin.esanatori.R;

/**
 * Created by Lutin on 8/29/17.
 */

public class AllWordViewHolder extends RecyclerView.ViewHolder {
    public CheckBox dateCheckBox;
    public TextView selectedTextView;

    public AllWordViewHolder(View itemView) {
        super(itemView);
        this.dateCheckBox = (CheckBox) itemView.findViewById(R.id.dateCheckBox);
        this.selectedTextView = (TextView) itemView.findViewById(R.id.selectedDate);
    }
}
