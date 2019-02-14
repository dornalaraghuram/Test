/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.news.test.network.DataSource;
import com.news.test.network.model.Facts;
import com.news.test.network.model.Rows;
import com.news.test.ui.home.HomeViewModel;
import com.news.test.ui.model.FactsData;
import com.news.test.ui.navigator.AppNavigator;
import com.news.test.util.Config;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class FactsDataTest {

    @Mock
    AppNavigator mAppNavigator;

    @Mock
    DataSource mDataSource;

    @Mock
    Facts mockedFactsData;

    private HomeViewModel mHomeViewModel;
    private TestScheduler mTestScheduler;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Application app = mock(Application.class);
        mTestScheduler = new TestScheduler();
        mHomeViewModel = new HomeViewModel(app);
        mHomeViewModel.setAppNavigator(mAppNavigator);
        mHomeViewModel.setDataSource(mDataSource);
        mockedFactsData = getMockedFactsData();
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
    public void fetchFactsDataSuccess() {

        MutableLiveData<FactsData> liveData = mHomeViewModel.getFactsData();

        try {
            FactsData value = LiveDataTestUtil.getValue(liveData);
            assertNull(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doReturn(Observable.just(mockedFactsData))
                .when(mDataSource)
                .getFacts(Config.URL_FACTS_TEST);

        mHomeViewModel.loadFactsData(Config.URL_FACTS_TEST, mTestScheduler);
        mTestScheduler.triggerActions();
        mTestScheduler.advanceTimeBy(4, TimeUnit.SECONDS);
        FactsData factsLiveData = null;
        try {
            factsLiveData = LiveDataTestUtil.getValue(liveData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat (factsLiveData.getTitle(), equalTo(mockedFactsData.getTitle()));
        assertEquals(factsLiveData.getRowsData().size(), mockedFactsData.getRows().size());

    }

    @Test
    public void fetchFactsDataFailure() {

        MutableLiveData<String> errorMessageLiveData = mHomeViewModel.getErrorMessage();

        Exception exception = new Exception("Network Error");
        doReturn(Observable.error(exception))
                .when(mDataSource)
                .getFacts(Config.URL_FACTS_TEST);

        mHomeViewModel.loadFactsData(Config.URL_FACTS_TEST, mTestScheduler);
        mTestScheduler.triggerActions();
        mTestScheduler.advanceTimeBy(4, TimeUnit.SECONDS);

        String errorMessage = null;
        try {
            errorMessage = LiveDataTestUtil.getValue(errorMessageLiveData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mAppNavigator).showErrorResponseDialog(exception);
        assertThat (errorMessage, equalTo(exception.getMessage()));
    }

    private Facts getMockedFactsData() {
        Facts facts = new Facts();
        facts.setTitle("About Canada");

        List<Rows> rows = new ArrayList<>();
        Rows rows1 = new Rows();
        rows1.setTitle("Housing");
        rows1.setDescription("Warmer than you might think.");
        rows1.setImageHref("http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png");

        Rows rows2 = new Rows();
        rows2.setTitle("Public Shame");
        rows2.setDescription("Sadly it's true.");
        rows2.setImageHref("http://static.guim.co.uk/sys-images/Music/Pix/site_furniture/2007/04/19/avril_lavigne.jpg");

        rows.add(rows1);
        rows.add(rows2);

        facts.setRows(rows);

        return facts;
    }

}