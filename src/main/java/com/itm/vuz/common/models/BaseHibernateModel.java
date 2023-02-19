package com.itm.vuz.common.models;

import com.salmonllc.hibernate.HibernateDataStore;

public class BaseHibernateModel extends HibernateDataStore {

    /**
     * Naumov Artem
     * Идея метода взята из раздела GroupCard (com.itm.vuz.groupcard.models.HibernateDisciplinesModel),
     * в котором задается отобрадение значений true и false на 0 и 1 (значения поля bit в ms sql server)
     * TODO: Вероятно, это необходимо перенести в некий базовый для приложения класс,
     * от которого будут наследоваться все модели приложения (DONE: 16.06.2006)
    */
    protected void initValues() {
        super.initValues();
        setBooleanDataType(DATATYPE_SHORT, new Short((short)1), new Short((short)0));
    }
}
