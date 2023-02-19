package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Discipline;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 6:17:01
 */
public class HibernateDisciplineModel extends HibernateDataStore {

    public static final String NAME = "Discipline.baseDiscipline.name";

    public HibernateDisciplineModel() {
        addBeanDefinition(Discipline.class, "Discipline");

        try {
            addRequiredRule("Discipline.hours", "Введите часы");
            addRequiredRule("Discipline.semester", "Введите семестр");
            addRequiredRule("Discipline.course", "Введите курс");
            addRequiredRule("Discipline.checkingForm", "Введите форму проверки");
            addRequiredRule("Discipline.year", "Введите год");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
