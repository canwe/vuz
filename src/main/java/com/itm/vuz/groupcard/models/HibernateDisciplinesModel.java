/*
 * $Id$
 * Created on 18.05.2006 17:17:06
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreBuffer;
import com.itm.vuz.common.domain.Discipline;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateDisciplinesModel extends HibernateDataStore{

    public static final String ENTITY_ID = "Discipline";
    public static final String EDUCATION_PLAN_ID = ENTITY_ID + "." + "educationPlan.educationPlanId";
    public static final String SEMESTER = ENTITY_ID + "." + "semester";
    public static final String COURSE = ENTITY_ID + "." + "course";
    public static final String YEAR = ENTITY_ID + "." + "year";
    public static final String CHECKING_FORM = ENTITY_ID + "." + "checkingForm";


    public HibernateDisciplinesModel(){
        addBeanDefinition(Discipline.class, ENTITY_ID);
    }

    protected void initValues() {
        super.initValues();
        setBooleanDataType(DataStoreBuffer.DATATYPE_SHORT, new Short((short)1),new Short((short)0));
    }

}
