/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network.model;


/**
 * Error response model class
 */
public class ErrorResponse extends Throwable {

    //TODO Model class which can have status code, error messages etc.
    private int statusCode;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
