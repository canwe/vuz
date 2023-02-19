package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.FacultyGroupCode;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 7:41:14
 */
public class HibernateFacultyGroupCodeModel extends HibernateDataStore {

    public HibernateFacultyGroupCodeModel() {
        addBeanDefinition(FacultyGroupCode.class, "FacultyGroupCode");
    }
}
