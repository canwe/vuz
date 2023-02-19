package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.domain.Language;
import com.itm.vuz.common.models.BaseHibernateModel;

/**
 * User: artem1
 * Date: 20.04.2006
 * Time: 21:16:47
 */
public class LanguageModel extends BaseHibernateModel{

    public LanguageModel() {
        this.addBeanDefinition(Language.class, "language");
    }

}
