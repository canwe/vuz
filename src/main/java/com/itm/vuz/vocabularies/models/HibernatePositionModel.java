package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Position;


/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 8:54:34
 */
public class HibernatePositionModel extends HibernateDataStore {

    public HibernatePositionModel() {
        addBeanDefinition(Position.class, "Position");

        try {
            addRequiredRule("Position.name", "Введите позицию");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
