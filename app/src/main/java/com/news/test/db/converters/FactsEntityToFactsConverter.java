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
 * Class to convert facts entity to facts
 */
public class FactsEntityToFactsConverter implements ModelConverter<FactsEntity, Facts> {

    @Override
    public Facts transform(FactsEntity factsEntity) {
        Facts facts = new Facts();
        facts.setTitle(factsEntity.getTitle());
        facts.setRows(factsEntity.getRows());
        return facts;
    }
}
