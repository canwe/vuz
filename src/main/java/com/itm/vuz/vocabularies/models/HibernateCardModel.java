package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Card;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 22:14:45
 */
public class HibernateCardModel extends HibernateDataStore {

    public static final String COURSE = "Card.course";

    public HibernateCardModel() {
        addBeanDefinition(Card.class, "Card");

        try {
            addRequiredRule("Card.course", "Введите курс");
            addRequiredRule("Card.semester", "Введите семестр");
            addRequiredRule("Card.year", "Введите год");

        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
