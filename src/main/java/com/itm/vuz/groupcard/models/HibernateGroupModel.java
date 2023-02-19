/*
 * $Id$
 * Created on 18.05.2006 15:26:06
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.StudentGroup;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateGroupModel extends HibernateDataStore{

    public static final String ENTITY_ID = "StudentGroup";
    public static final String GROUP_ID = ENTITY_ID + "." + "studentGroupId";
    public static final String GROUP_NUMBER = ENTITY_ID + "." + "number";
    public static final String FACULTY_SHORT_NAME = ENTITY_ID + "." + "speciality.faculty.shortName";
    public HibernateGroupModel(){
        addBeanDefinition(StudentGroup.class, ENTITY_ID);
    }


}
