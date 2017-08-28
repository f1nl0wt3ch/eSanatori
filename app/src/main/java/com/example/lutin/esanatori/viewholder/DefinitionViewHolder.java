package com.example.lutin.esanatori.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.lutin.esanatori.R;
import com.example.lutin.esanatori.model.RealmDefinition;


/**
 * Created by Lutin on 8/21/17.
 */

public class DefinitionViewHolder extends AViewHolder<RealmDefinition> {
    TextView typeTextView;
    TextView definitionTextView;

    public DefinitionViewHolder(View itemView) {
        super(itemView);
        typeTextView = (TextView) itemView.findViewById(R.id.typeTextView);
        definitionTextView = (TextView) itemView.findViewById(R.id.definitionTextView);
    }

    public TextView getTypeTextView() {
        return typeTextView;
    }

    public void setTypeTextView(TextView typeTextView) {
        this.typeTextView = typeTextView;
    }

    public TextView getDefinitionTextView() {
        return definitionTextView;
    }

    public void setDefinitionTextView(TextView definitionTextView) {
        this.definitionTextView = definitionTextView;
    }

    @Override
    public void bind(RealmDefinition object) {

    }
}
