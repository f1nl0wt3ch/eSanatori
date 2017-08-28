package com.example.lutin.esanatori.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lutin.esanatori.R;

/**
 * Created by Lutin on 8/28/17.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    private TextView type;
    private TextView definition;
    public CustomViewHolder(View itemView) {
        super(itemView);
        this.type = (TextView) itemView.findViewById(R.id.typeTextView);
        this.definition = (TextView) itemView.findViewById(R.id.definitionTextView);
    }

    public TextView getType() {
        return type;
    }

    public void setType(TextView type) {
        this.type = type;
    }

    public TextView getDefinition() {
        return definition;
    }

    public void setDefinition(TextView definition) {
        this.definition = definition;
    }
}
