package com.itm.vuz.personalcard.models;

import com.itm.vuz.common.domain.Benefit;
import com.itm.vuz.common.models.BaseHibernateModel;

/**
  * User: artem1
 * Date: 20.04.2006
 * Time: 21:16:47
  */
public class BenefitModel extends BaseHibernateModel{

    public BenefitModel() {
        this.addBeanDefinition(Benefit.class, "b");
    }

}
