/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.recycler_adapter;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.news.test.rxbus.RxBus;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Base ViewHolder class to extend in subclasses.
 *
 * @author Aleksandar Gotev
 */
public abstract class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

    private final WeakReference<RecyclerAdapterNotifier> adapter;

    private ViewDataBinding binding;

    private String mPageId;

    private RxBus mRxBus;

    public RecyclerAdapterViewHolder(ViewDataBinding itemView, RecyclerAdapterNotifier adapter) {
        super(itemView.getRoot());
        ButterKnife.bind(this, itemView.getRoot());
        this.binding = itemView;
        this.adapter = new WeakReference<>(adapter);
        mPageId = adapter.getPageId();
        mRxBus = adapter.getRxBus();
    }

    protected void bind(Object object) {
        if (object != null) {
//            binding.setVariable(BR.data, object);
//            binding.executePendingBindings();
        }
    }

    /**
     * Sends an event to the adapter.
     *
     * @param data additional event data
     */
    protected final void sendEvent(Bundle data) {
        this.adapter.get().sendEvent(this, data);
    }

    protected final View findViewById(int id) {
        return itemView.findViewById(id);
    }

    public ViewDataBinding getBinder() {
        return binding;
    }

    public String getPageId() {
        return mPageId;
    }

    public RxBus getRxBus() {
        return mRxBus;
    }

}
