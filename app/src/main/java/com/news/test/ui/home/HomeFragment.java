/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.test.R;
import com.news.test.databinding.FragmentHomeBinding;
import com.news.test.network.DataSource;
import com.news.test.ui.base.BaseFragment;
import com.news.test.ui.recycler_adapter.RecyclerAdapter;

import javax.inject.Inject;

/**
 * Home listing screen.
 */
public class HomeFragment extends BaseFragment {


    FragmentHomeBinding binding;
    private RecyclerAdapter mAdapter;

    @Inject
    DataSource mDataSource;

    @Inject
    HomeViewModel mHomeViewModel;

    public static Fragment getInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayoutId(), container, false);
        requestNetworkApiCall();
        initRecyclerView();
        return binding.getRoot();
    }


    private void requestNetworkApiCall() {
        mHomeViewModel.loadFactsData();
    }

    /**
     * initializing the recycler view
     */
    private void initRecyclerView() {
        mAdapter = new RecyclerAdapter(getRxBus());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        binding.recyclerViewHome.setLayoutManager(manager);
        binding.recyclerViewHome.setAdapter(mAdapter);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void handleEvents(Object event) {

    }


}
