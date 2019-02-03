/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.arch.lifecycle.ViewModelProviders;
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
import com.news.test.rxbus.RxBus;
import com.news.test.ui.adapter.HomeRowsAdapter;
import com.news.test.ui.base.BaseFragment;
import com.news.test.ui.model.FactsData;
import com.news.test.ui.model.RowData;
import com.news.test.ui.navigator.AppNavigator;

import java.util.List;

import javax.inject.Inject;

/**
 * Home listing screen.
 */
public class HomeFragment extends BaseFragment {

    FragmentHomeBinding binding;
    private HomeRowsAdapter mAdapter;

    @Inject
    DataSource mDataSource;

    @Inject
    AppNavigator mNavigator;

    @Inject
    RxBus mRxBus;

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
        requestApiData();
        subscribeLiveData();
        initRecyclerView();
        return binding.getRoot();
    }

    private void requestApiData() {
        getViewModel().setAppNavigator(mNavigator);
        getViewModel().setDataSource(mDataSource);
        getViewModel().setRxBus(mRxBus);
        getViewModel().loadFactsData();
    }

    /**
     * initializing the recycler view
     */
    private void initRecyclerView() {
        mAdapter = new HomeRowsAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewHome.setAdapter(mAdapter);
        binding.recyclerViewHome.setLayoutManager(manager);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void handleEvents(Object event) {

    }

    private void subscribeLiveData() {
        getViewModel().getFactsData().observe(this, this::updateUI);
    }

    private void updateUI(FactsData factsData) {
        if(factsData != null) {
            List<RowData> rows = factsData.getRowsData();
            setTitle(factsData.getTitle());
            mAdapter.updateRowsData(rows);
        }
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        return mHomeViewModel;
    }


}
