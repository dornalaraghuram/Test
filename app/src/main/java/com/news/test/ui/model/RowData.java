/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.news.test.network.model.Rows;

/**
 * Model observable data class for Rows
 */
public class RowData extends BaseObservable{

    private String rowTitle;
    private String rowDescription;
    private String rowImageLink;


    public RowData(Rows row) {
        rowTitle = row.getTitle();
        rowDescription = row.getDescription();
        rowImageLink = row.getImageHref();
    }

    @Bindable
    public String getRowTitle() {
        return rowTitle;
    }

    @Bindable
    public String getRowDescription() {
        return rowDescription;
    }

    @Bindable
    public String getRowImageLink() {
        return rowImageLink;
    }
}
