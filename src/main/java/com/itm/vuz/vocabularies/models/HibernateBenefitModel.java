package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Benefit;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 21:23:43
 */
public class HibernateBenefitModel extends HibernateDataStore {

    public HibernateBenefitModel() {
        addBeanDefinition(Benefit.class, "Benefit");

        try {
            addRequiredRule("Benefit.name", "Введите Льготу");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
