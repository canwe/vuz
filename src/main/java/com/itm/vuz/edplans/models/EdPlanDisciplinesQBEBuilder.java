package com.itm.vuz.edplans.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 02.07.2006
 * Time: 13:53:29
 */
public class EdPlanDisciplinesQBEBuilder extends QBEBuilder{
    public EdPlanDisciplinesQBEBuilder(){
        try{
            addCriteria(HibernateDisciplinesModel.EDUCATION_PLAN_ID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateDisciplinesModel.EDUCATION_PLAN_ID);
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
    }

}
