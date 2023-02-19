package com.itm.vuz.personalcard.models;

import com.salmonllc.hibernate.*;

import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.models.BaseHibernateModel;
/**
 * User: artem1
 * Date: 20.04.2006
 * Time: 21:16:47
 */
public class StudentStatusModel extends BaseHibernateModel{

    public StudentStatusModel() {
        this.addBeanDefinition(Student.class, "s");
    }

}
