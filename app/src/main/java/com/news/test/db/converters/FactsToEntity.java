/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.db.converters;

import com.news.test.db.FactsEntity;
import com.news.test.network.model.Facts;
import com.news.test.network.model.Rows;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to convert {@link Facts} to {@link FactsEntity}
 */
public class FactsToEntity implements ModelConverter<Facts, FactsEntity> {

    @Override
    public FactsEntity transform(Facts facts) {

        FactsEntity factsEntity = new FactsEntity();
        factsEntity.setTitle(facts.getTitle());
        List<Rows> rows = new ArrayList<>(facts.getRows());
        factsEntity.setRows(rows);
        return factsEntity;
    }
}
