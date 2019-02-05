/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.news.test.network.model.Rows;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AppTypeConverters {

    @TypeConverter
    public static List<Rows> stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Rows>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Rows> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
