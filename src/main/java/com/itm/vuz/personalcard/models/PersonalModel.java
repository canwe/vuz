package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.models.BaseHibernateModel;
import com.itm.vuz.common.domain.Personal;

/**
 * User: artem1
 * Date: 25.06.2006
 * Time: 21:15:10
 */
public class PersonalModel extends BaseHibernateModel {

    public PersonalModel(){
        this.addBeanDefinition(Personal.class, "Personal");
    }
}
