/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.news.test.constants.DBConstants;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FactsEntity entry);


    @Query("SELECT * FROM " + DBConstants.TBL_FACTS)
    Single<List<FactsEntity>> getAll();
}