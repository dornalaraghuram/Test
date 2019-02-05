/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db.converters;

/**
 * Contract to convert one model to another
 * @param <FROM>
 * @param <TO>
 */
public interface ModelConverter<FROM,TO> {
    TO transform(FROM from);
}