/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.news.test.constants.DBConstants;

@Database(entities = {FactsEntity.class}, version = DBConstants.DB_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract FactsDao factsDao();
}
