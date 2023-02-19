package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.domain.StudentGroup;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 30.07.2006
 * Time: 11:33:11
 */
public class HibernateStudentGroupModel extends HibernateDataStore {

    public HibernateStudentGroupModel() {
        addBeanDefinition(StudentGroup.class, "StudentGroup");
    }

}
