package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Speciality;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 8:01:26
 */
public class HibernateSpecialityModel extends HibernateDataStore {

    public static final String NAME = "Speciality.name";
    public static final String NUMBER = "Speciality.number";
    public static final String SHORT_NAME = "Speciality.shortName";


    public HibernateSpecialityModel() {
        addBeanDefinition(Speciality.class, "Speciality");
        try {
               addRequiredRule(NAME, "Введите специальность");
               addRequiredRule(NUMBER, "Введите номер");
               addRequiredRule(SHORT_NAME, "Введите сокращённое название");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
