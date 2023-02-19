package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Teacher;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 7:02:17
 */
public class HibernateTeacherModel extends HibernateDataStore {
    public static final String FAMILY = "Teacher.personal.familyName";
    public static final String NAME = "Teacher.personal.name";
    public static final String PATRONYMIC = "Teacher.personal.patronymic";
                  
    public HibernateTeacherModel() {
        addBeanDefinition(Teacher.class, "Teacher");
    }
}
