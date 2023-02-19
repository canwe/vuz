package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.UserRole;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:48:38
 */
public class HibernateUserRoleModel extends HibernateDataStore {

    public HibernateUserRoleModel() {
        addBeanDefinition(UserRole.class, "UserRole");

        try {
            addRequiredRule("UserRole.name", "Введите роль");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
