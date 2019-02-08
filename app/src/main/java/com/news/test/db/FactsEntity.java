/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.news.test.constants.DBConstants;
import com.news.test.db.converters.AppTypeConverters;
import com.news.test.network.model.Rows;

import java.util.List;

@Entity(tableName = DBConstants.TBL_FACTS)
public class FactsEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String title;

    @NonNull
    @TypeConverters(AppTypeConverters.class)
    private List<Rows> rows;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(@NonNull List<Rows> rows) {
        this.rows = rows;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FactsEntity && ((FactsEntity) obj).getId() == getId();
    }
}
