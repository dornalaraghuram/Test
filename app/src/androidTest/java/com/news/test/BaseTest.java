/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class BaseTest {

    private MockWebServer mockServer;
    public static String URL_FACTS;
    private RestServiceTestHelper helper;

    @Before
    public void setUp(){
        this.configureMockServer();
    }

    @After
    public void tearDown(){
        this.stopMockServer();
    }


    public void stopMockServer() {

        try {
            mockServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void configureMockServer() {
        mockServer = new MockWebServer();
        try {
            mockServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        helper = new RestServiceTestHelper();

        URL_FACTS = mockServer.url("/").toString();
    }


    public String setMockHttpResponse(String fileName, int responseCode) {
        String stringFromFile = "";
        if(mockServer != null) {
            MockResponse mockResponse = new MockResponse();
            mockResponse.setResponseCode(responseCode);
            try {
                stringFromFile = helper.getStringFromFile(fileName);
                mockResponse.setBody(stringFromFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mockServer.enqueue(mockResponse);

        }
        return stringFromFile;
    }

}
