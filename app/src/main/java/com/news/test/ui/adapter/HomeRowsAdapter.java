/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.news.test.R;
import com.news.test.databinding.ItemHomeCardRowViewBinding;
import com.news.test.ui.model.RowData;

import java.util.List;

/**
 * Adapter that handles rowsData
 */
public class HomeRowsAdapter extends RecyclerView.Adapter<HomeRowsAdapter.Holder> {


    private List<RowData> mRowsData;

    public HomeRowsAdapter() {

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHomeCardRowViewBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(position);
    }


    public void updateRowsData(List<RowData> rowsData) {
        if(mRowsData != null) {
            mRowsData.clear();
        }
        this.mRowsData = rowsData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.item_home_card_row_view;
    }

    @Override
    public int getItemCount() {
        return mRowsData == null ? 0 : mRowsData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ItemHomeCardRowViewBinding binding;

        public Holder(ItemHomeCardRowViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(int position) {
            binding.setData(mRowsData.get(position));
            binding.setPosition(position);
            binding.executePendingBindings();
        }
    }
}
