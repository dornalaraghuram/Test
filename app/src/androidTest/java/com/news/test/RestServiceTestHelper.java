/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestServiceTestHelper {

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public String getStringFromFile(String filePath) throws Exception {
        final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}
