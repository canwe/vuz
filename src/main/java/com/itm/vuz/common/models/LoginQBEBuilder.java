package com.itm.vuz.common.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 22.07.2006
 * Time: 16:24:49
 */
public class LoginQBEBuilder  extends QBEBuilder{

    public LoginQBEBuilder(){
        try{
            addCriteria(HibernateUsersModel.USER_LOGIN, QBEBuilder.CRITERIA_TYPE_EQUALS_IGNORE_CASE, HibernateUsersModel.USER_LOGIN);
            addCriteria(HibernateUsersModel.USER_PASSWORD, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateUsersModel.USER_PASSWORD);
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
    }
}
