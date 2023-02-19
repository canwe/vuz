package com.itm.vuz.groupcard;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.salmonllc.util.MessageLog;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 18.05.2006
 * Time: 7:38:31
 */
public class MarksExpression implements DataStoreExpression{

    public MarksExpression(Long _disciplineId) {
        disciplineId = _disciplineId;
    }

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
        DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
        GroupCardStudent stud = (GroupCardStudent)dsRow.getDSDataRow().getBean();
        return stud.getMarks(disciplineId);
    }

    public Long getDesciplineId(){
        return disciplineId;
    }

    private Long disciplineId;
}
