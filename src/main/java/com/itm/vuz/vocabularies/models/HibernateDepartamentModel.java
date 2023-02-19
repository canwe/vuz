package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Departament;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 22:43:21
 */
public class HibernateDepartamentModel extends HibernateDataStore {

    public static final String NAME = "Departament.name";
    public static final String PERSONALS_EMPTY = "Departament.personals.empty";
    public static final String DEPARTMANENTID = "Departament.departmanentId";

    public HibernateDepartamentModel() {
        addBeanDefinition(Departament.class, "Departament");

        try {
            addRequiredRule("Departament.name", "Введите Departament");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
