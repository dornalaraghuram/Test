/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.model;

import com.news.test.network.model.Facts;
import com.news.test.network.model.Rows;

import java.util.ArrayList;
import java.util.List;

/**
 * Model observale data class for Facts
 */
public class FactsData {

    private List<RowData> rowsData;

    private String title;

    public FactsData(Facts facts) {
        init(facts);
    }

    private void init(Facts facts) {
        if(facts != null) {
            title = facts.getTitle();
            rowsData = new ArrayList<>();
            List<Rows> rows = facts.getRows();
            if(rows != null && rows.size() > 0) {
                for(Rows row : rows) {
                    if(!row.isValid()) continue;
                    RowData rowData = new RowData(row);
                    rowsData.add(rowData);
                }
            }
        }
    }

    public List<RowData> getRowsData() {
        return rowsData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mesg) {
        this.title = mesg;
    }
}
