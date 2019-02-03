/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.test.rxbus.RxBus;
import com.news.test.rxbus.RxBusCallback;
import com.news.test.rxbus.RxBusHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * Base class for every fragment in the application
 */
public abstract class BaseFragment extends Fragment implements RxBusCallback {

    private String TAG = BaseFragment.class.getSimpleName();

    @Inject
    RxBus mRxBus;

    private Unbinder mUnBinder;

    private RxBusHelper mRxBusHelper;

    protected abstract int getFragmentLayoutId();

    public abstract BaseViewModel getViewModel();


    /**To handle the navigation events
     * @param event
     */
    protected abstract void handleEvents(Object event);

    protected View inflateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayoutId(), container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void registerEvents() {
        mRxBusHelper = new RxBusHelper();
        mRxBusHelper.registerEvents(getRxBus(), TAG, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        registerEvents();
        View view = inflateView(inflater, container, savedInstanceState);

        mUnBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        super.onDestroyView();
    }

    @Override
    public void onEventTrigger(Object event) {
        handleEvents(event);
    }


    protected RxBus getRxBus() {
        return mRxBus;
    }


    protected void setTitle(String title) {
        if(getActivity() != null && !TextUtils.isEmpty(title)) {
            getActivity().setTitle(title);
        }
    }

}
