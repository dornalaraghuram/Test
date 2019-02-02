/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.event;

public class NavigationEvent<T> {

    public static final String EVENT_LAUNCH = "event_launch";

    private String flag;
    private String tag;
    private T data;
    private int screenType;
    private String filter;

    public NavigationEvent(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setScreenType(int type) {
        screenType = type;
    }

    public int getScreenType() {
        return screenType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
