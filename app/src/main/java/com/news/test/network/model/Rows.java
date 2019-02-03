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

public class Rows {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }
}
