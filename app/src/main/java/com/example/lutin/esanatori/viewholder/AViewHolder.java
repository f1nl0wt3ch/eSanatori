package com.example.lutin.esanatori.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lutin on 8/21/17.
 */

public abstract class AViewHolder<T> extends RecyclerView.ViewHolder {
    public AViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T object);
}
