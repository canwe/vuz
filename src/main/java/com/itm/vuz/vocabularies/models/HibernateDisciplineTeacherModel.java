package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.DisciplineTeacher;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 7:03:36
 */
public class HibernateDisciplineTeacherModel extends HibernateDataStore {

    public static final String NAME = "DisciplineTeacher.discipline.baseDiscipline.name";

    public HibernateDisciplineTeacherModel() {
        addBeanDefinition(DisciplineTeacher.class, "DisciplineTeacher");
    }
}
