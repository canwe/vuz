package com.itm.vuz.examlist.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.itm.vuz.groupcard.GroupCardStudent;
import com.itm.vuz.groupcard.GroupCard;
import com.itm.vuz.common.domain.ExamMark;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 12.08.2006
 * Time: 17:42:37
 */
public class SignExpression implements DataStoreExpression{

    ExamMarksHelper helper;

    public SignExpression(){
        helper = new ExamMarksHelper();
    }

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {
        String ret = "";
        DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
        ExamMark mark = (ExamMark)dsRow.getDSDataRow().getBean();

        if(mark!=null && mark.getDiscipline().getCheckingForm() == GroupCard.EXAM){
            Byte value = mark.getValue();
            ret = helper.getExamValue((value.intValue()==0) ? value.intValue() : (value.intValue()-1));
        }else{
            ret = "";
        }
        return ret;

    }
}
