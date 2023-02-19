package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.StudentGroup;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 7:47:24
 */
public class HibernateStudentGroupModel extends HibernateDataStore {


    public HibernateStudentGroupModel() {
        addBeanDefinition(StudentGroup.class, "StudentGroup");

        try {
            addRequiredRule("StudentGroup.course", "Введите курс");
            addRequiredRule("StudentGroup.educationDuration", "Введите длительность обучения");
            //addRequiredRule("StudentGroup.number", "Введите номер группы");

            // todo check facultyCode for int
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
