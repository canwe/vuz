package com.itm.vuz.common.models;

import com.itm.vuz.common.domain.User;
import com.salmonllc.hibernate.HibernateDataStore;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 22.07.2006
 * Time: 16:23:12
 */
public class HibernateUsersModel extends HibernateDataStore{

    public static final String ENTITY_ID = "User";
    public static final String USER_LOGIN = ENTITY_ID + ".login";
    public static final String USER_PASSWORD = ENTITY_ID + ".password";

    public HibernateUsersModel(){
        addBeanDefinition(User.class, ENTITY_ID);
    }
}
