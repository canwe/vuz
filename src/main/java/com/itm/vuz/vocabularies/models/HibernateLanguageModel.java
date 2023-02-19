package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Language;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:32:12
 */
public class HibernateLanguageModel extends HibernateDataStore {

    public HibernateLanguageModel() {
        addBeanDefinition(Language.class, "Language");

        try {
            addRequiredRule("Language.name", "Введите язык");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
