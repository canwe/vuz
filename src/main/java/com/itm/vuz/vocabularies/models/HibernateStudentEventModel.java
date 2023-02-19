package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.StudentEvent;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 8:11:49
 */
public class HibernateStudentEventModel extends HibernateDataStore {

    public static final String NAME = "StudentEvent.name";

    public HibernateStudentEventModel() {
        addBeanDefinition(StudentEvent.class, "StudentEvent");
        try {
               addRequiredRule(NAME, "Введите событие");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
