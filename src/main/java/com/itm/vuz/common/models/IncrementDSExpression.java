package com.itm.vuz.common.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;

/**
 * used in incremented number column
 * User: Vitaly Shmelev
 * Date: 12.08.2006
 * Time: 13:44:31
 */
public class IncrementDSExpression implements DataStoreExpression{

    int startNumber;

    public IncrementDSExpression(int startNumber){
        this.startNumber = startNumber;
    }

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
        return "" + (startNumber + row);
    }
}
