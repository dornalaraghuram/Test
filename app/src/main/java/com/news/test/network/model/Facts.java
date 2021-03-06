/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Facts {

    @SerializedName("rows")
    @Expose
    private List<Rows> rows = null;
    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
}
