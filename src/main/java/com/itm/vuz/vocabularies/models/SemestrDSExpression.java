package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.itm.vuz.groupcard.GroupCardStudent;
import com.itm.vuz.common.domain.Discipline;

/**
 * Created by IntelliJ IDEA.
 * User: sanek
 * Date: 13.08.2006
 * Time: 16:51:17
 * To change this template use File | Settings | File Templates.
 */
public class SemestrDSExpression implements DataStoreExpression{

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
        DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
        Discipline discipline = (Discipline)dsRow.getDSDataRow().getBean();
        return (!discipline.isSemester() ? "первый" : "второй");
    }
}
