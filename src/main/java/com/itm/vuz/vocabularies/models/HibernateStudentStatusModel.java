package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.StudentStatus;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:43:52
 */
public class HibernateStudentStatusModel extends HibernateDataStore {

    public HibernateStudentStatusModel() {
        addBeanDefinition(StudentStatus.class, "StudentStatus");

        try {
            addRequiredRule("StudentStatus.name", "Введите StudentStatus");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
