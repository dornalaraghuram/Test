/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test;

import android.arch.lifecycle.ViewModelProviders;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.news.test.ui.home.HomeActivity;
import com.news.test.ui.home.HomeViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);
    private HomeViewModel viewModel;

    @Before
    public void addFragment() {
        mActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        viewModel = ViewModelProviders.of(mActivityTestRule.getActivity()).get(HomeViewModel.class);
    }

    @Test
    public void testSuccess() {
        try {
            setMockHttpResponse("facts_success_response.json", HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewModel.loadFactsData(URL_FACTS_TEST, null);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFail() {
        try {
            setMockHttpResponse("facts_error_response.json", HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewModel.loadFactsData(URL_FACTS_TEST, null);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
