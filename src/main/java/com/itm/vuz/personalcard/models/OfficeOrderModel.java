package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.domain.OfficeOrder;
import com.salmonllc.sql.DataStoreException;


/**
 * User: artem1
 * Date: 25.06.2006
 * Time: 21:12:07
 */
public class OfficeOrderModel extends BaseCheckableModel {

    public static final String ENTITY = "OfficeOrder";
    public static final String OFFICE_ORDER_ID = ENTITY + ".officeOrderId";
    public static final String DATE_ORDER = ENTITY + ".dateOrder";
    public static final String EXTERANL_NUMBER = ENTITY + ".externalNumber";
    public static final String IS_SIGNED = ENTITY + ".issigned";
    public static final String ORDER_CATEGORY = ENTITY + ".orderCategory";

    public OfficeOrderModel() {
        super();
        this.addBeanDefinition(OfficeOrder.class, ENTITY);
    }

    /**
     * Возвращает экземпляр приказа из текущей строки
     * @return Приказ
     * @throws DataStoreException
     */
    public OfficeOrder getOfficeOrder() throws DataStoreException {
        return this.getOfficeOrder(this.getRow());
    }

    /**
     * Возвращает экземпляр приказа из указанной строки
     * @param row Индекс строки в наборе данных
     * @return Приказ
     * @throws DataStoreException
     */
    public OfficeOrder getOfficeOrder(int row) throws DataStoreException {
        return (OfficeOrder)this.getBean(row);
    }
}
