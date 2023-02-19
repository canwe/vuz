package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.SettlementDefinition;

/**
 * User: Alexei
 * Date: 06.07.2006
 * Time: 16:53:06
 */
public class HibernateSettlementDefinitionModel
        extends HibernateDataStore {

    public static final String NAME = "SettlementDefinition.name";
    public static final String SETTLEMENTS_EMPTY = "SettlementDefinition.settlements.empty";
    public static final String SETTLEMENTDEFINITIONID = "SettlementDefinition.settlementDefinitionId";

    public HibernateSettlementDefinitionModel() {
        addBeanDefinition(SettlementDefinition.class, "SettlementDefinition");
          try {
            addRequiredRule(NAME, "Введите тип месторасположения");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
