/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test;

import android.app.Application;
import android.content.Context;

import com.news.test.network.DataSource;
import com.news.test.network.model.Facts;
import com.news.test.ui.home.HomeViewModel;
import com.news.test.ui.model.FactsData;
import com.news.test.ui.navigator.AppNavigator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    AppNavigator mAppNavigator;

    @Mock
    DataSource mDataSource;

    @Mock
    Facts facts;

    @Mock
    FactsData factsData;


    private HomeViewModel mHomeViewModel;
    private TestScheduler mTestScheduler;


    @Before
    public void setUp() throws Exception {
        Application app = mock(Application.class);
        mTestScheduler = new TestScheduler();
        mHomeViewModel = new HomeViewModel(app);
        mHomeViewModel.setAppNavigator(mAppNavigator);
        mHomeViewModel.setDataSource(mDataSource);
        factsData.setTitle("About Canada");
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @After
    public void tearDown() throws Exception {
        mAppNavigator = null;
        mDataSource = null;
        mHomeViewModel = null;
    }


    @Test
    public void fetchFactsData() {

        doReturn(Observable.just(factsData))
                .when(mDataSource)
                .getFacts();

        mHomeViewModel.loadFactsData();
        mTestScheduler.triggerActions();

        verify(factsData).setTitle("About Canada");

    }
}