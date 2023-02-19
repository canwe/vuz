/*
 * $Id$
 * Created on 22.05.2006 18:27:30
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardQBEBuilder extends QBEBuilder{
    public GroupCardQBEBuilder(){
        try{
            addCriteria(HibernateGroupModel.GROUP_ID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateGroupModel.GROUP_ID);
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
    }
}
