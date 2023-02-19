package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Hospice;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 22:54:27
 */
public class HibernateHospiceModel extends HibernateDataStore {

    public HibernateHospiceModel() {
        addBeanDefinition(Hospice.class, "Hospice");

        try {
            addRequiredRule("Hospice.name", "Введите общежитие");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
