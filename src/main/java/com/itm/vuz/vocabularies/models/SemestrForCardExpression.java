package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.itm.vuz.common.domain.Card;

/**
 * Created by IntelliJ IDEA.
 * User: sanek
 * Date: 13.08.2006
 * Time: 22:31:40
 * To change this template use File | Settings | File Templates.
 */
public class SemestrForCardExpression implements DataStoreExpression{

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
        DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
        Card card = (Card)dsRow.getDSDataRow().getBean();
        return (!card.isSemester() ? "первый" : "второй");
    }
}
