package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.EducationPlan;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 6:17:12
 */
public class HibernateEducationPlanModel extends HibernateDataStore {

    public HibernateEducationPlanModel() {
        addBeanDefinition(EducationPlan.class, "EducationPlan");
    }
}
