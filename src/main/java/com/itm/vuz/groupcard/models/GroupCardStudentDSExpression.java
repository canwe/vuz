package com.itm.vuz.groupcard.models;

import com.salmonllc.sql.DataStoreExpression;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreRow;
import com.itm.vuz.groupcard.GroupCardStudent;
import com.itm.vuz.common.domain.Student;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 12.08.2006
 * Time: 15:32:38
 */
public class GroupCardStudentDSExpression implements DataStoreExpression{

    public Object evaluateExpression(DataStoreBuffer dsBuf, int row) throws DataStoreException {

        String familyName = null;
        String name = null;
        String patronymicName = null;
        String ret = "";

        DataStoreRow dsRow = dsBuf.getDataStoreRow(row, DataStoreBuffer.BUFFER_STANDARD);
        GroupCardStudent stud = (GroupCardStudent)dsRow.getDSDataRow().getBean();
        Student student = stud.getStudent();
        if(student!=null){
            familyName = student.getFamilyName();
            name = student.getName();
            patronymicName = student.getPatronymic();
        }

        ret = (familyName == null ? "" : (familyName + " ")) +
                (name == null ? "" : (name + " ")) +
                (patronymicName == null ? "" : (patronymicName + " "));

        if(student.getCourseLeader()!=null && student.getCourseLeader().booleanValue()==true){
            ret+=" " + "СТ";
        }
        if(student.getProfLeader()!=null && student.getProfLeader().booleanValue()==true){
            ret+=" " + "ПРОФ";
        }
        return ret;
    }
}
