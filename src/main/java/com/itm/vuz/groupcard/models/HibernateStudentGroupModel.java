/*
 * $Id$
 * Created on 27.05.2006 14:59:22
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Mark;
import com.itm.vuz.common.domain.StudentGroup;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateStudentGroupModel extends HibernateDataStore{

    public static final String ENTITY_ID = "StudentGroup";

    public HibernateStudentGroupModel(){
        addBeanDefinition(StudentGroup.class, ENTITY_ID);
    }
}
