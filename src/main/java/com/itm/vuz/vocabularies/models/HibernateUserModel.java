package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.User;


public class HibernateUserModel extends HibernateDataStore {

    public HibernateUserModel() {
        addBeanDefinition(User.class, "User");
    }
     
}
