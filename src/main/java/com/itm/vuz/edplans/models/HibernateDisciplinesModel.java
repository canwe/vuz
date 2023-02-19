package com.itm.vuz.edplans.models;

import com.itm.vuz.common.domain.Discipline;
import com.salmonllc.hibernate.HibernateDataStore;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 02.07.2006
 * Time: 14:00:17
 */
public class HibernateDisciplinesModel extends HibernateDataStore {
    public static final String EDUCATION_PLAN_ID = "Discipline.educationPlan.educationPlanId";

    public HibernateDisciplinesModel() {
        addBeanDefinition(Discipline.class, "Discipline");
    }

}
