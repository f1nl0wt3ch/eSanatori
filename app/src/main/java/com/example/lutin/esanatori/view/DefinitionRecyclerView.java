package com.example.lutin.esanatori.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Lutin on 8/22/17.
 */

public class DefinitionRecyclerView extends RecyclerView {

    private int triggerLimit = 1;
    private LoadMoreListener listener;
    private final OnScrollListener scrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int state) {
            super.onScrollStateChanged(recyclerView, state);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (getAdapter() == null || listener == null) {
                return;
            }
            int lastVisibleItemPosition = getLastVisibleItemPosition();
            if (lastVisibleItemPosition >= getAdapter().getItemCount() - triggerLimit) {
                listener.shouldLoadMore();
            }

        }
    };

    public DefinitionRecyclerView(Context context) {
        super(context);
    }

    public DefinitionRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefinitionRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTriggerLimit(int triggerLimit) {
        this.triggerLimit = triggerLimit;
    }

    public void setListener(LoadMoreListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnScrollListener(scrollListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        super.onAttachedToWindow();
    }

    public int getLastVisibleItemPosition() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            return ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else {
            return 0;
        }
    }


    public interface LoadMoreListener {
        void shouldLoadMore();
    }


}
