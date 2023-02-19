package com.itm.vuz.edplans.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.EducationPlan;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 02.07.2006
 * Time: 11:55:06
 */
public class HibernateEducationPlansModel extends HibernateDataStore {

    public static final String EDUCATIONPLANID = "EducationPlan.educationPlanId";
    public static final String DATASTART = "EducationPlan.dataStart";
    public static final String DATAEND = "EducationPlan.dataEnd";
    public static final String SPECIALITY_SPECIALITYID = "EducationPlan.speciality.specialityId";

    public HibernateEducationPlansModel() {
        addBeanDefinition(EducationPlan.class, "EducationPlan");

    }

}
