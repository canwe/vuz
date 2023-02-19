package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.itm.vuz.common.domain.Discipline;

/**
 * Created by IntelliJ IDEA.
 * User: sanek
 * Date: 21.08.2006
 * Time: 15:01:58
 * To change this template use File | Settings | File Templates.
 */
public class hoursDSExpression implements DataStoreExpression {
     public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
         DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
         Discipline discipline = (Discipline)dsRow.getDSDataRow().getBean();
         return String.valueOf((discipline.getIndependenthours() + discipline.getAudiencehours()));
     }
}
