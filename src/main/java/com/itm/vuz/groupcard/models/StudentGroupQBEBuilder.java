/*
 * $Id$
 * Created on 28.05.2006 11:15:58
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
public class StudentGroupQBEBuilder extends QBEBuilder{

    public StudentGroupQBEBuilder(){
        try{
            addCriteria(HibernateGroupModel.GROUP_NUMBER, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateGroupModel.GROUP_NUMBER);
            addCriteria(HibernateGroupModel.FACULTY_SHORT_NAME, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateGroupModel.FACULTY_SHORT_NAME);

        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
    }

}
