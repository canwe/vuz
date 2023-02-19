package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.OrderCategory;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:37:49
 */
public class HibernateOrderCategoryModel extends HibernateDataStore {

    public HibernateOrderCategoryModel() {
        addBeanDefinition(OrderCategory.class, "OrderCategory");

        try {
            addRequiredRule("OrderCategory.orderCategory", "Введите Категорию приказа");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
