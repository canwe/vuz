/*
 * $Id$
 * Created on 23.05.2006 9:19:14
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardDisiplineQBEBuilder extends QBEBuilder{

    public GroupCardDisiplineQBEBuilder() {
        try{
            addCriteria(HibernateDisciplinesModel.SEMESTER, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateDisciplinesModel.SEMESTER);
            //addCriteria(HibernateDisciplinesModel.COURSE, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateDisciplinesModel.COURSE);
            addCriteria(HibernateDisciplinesModel.YEAR, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateDisciplinesModel.YEAR);
            addCriteria(HibernateDisciplinesModel.EDUCATION_PLAN_ID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateDisciplinesModel.EDUCATION_PLAN_ID);
            setOrderBy(HibernateDisciplinesModel.CHECKING_FORM);

        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }

    }
}
