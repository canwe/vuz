/*
 * $Id$
 * Created on 18.05.2006 17:59:41
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Mark;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateStudentMarkModel extends HibernateDataStore{

    public static final String ENTITY_ID = "Mark";
    public static final String GROUP_ID = ENTITY_ID + "." + "studentGroup.studentGroupId";
    public static final String STUDENT_ID = ENTITY_ID + "." + "student.studentId";
    public HibernateStudentMarkModel(){
        addBeanDefinition(Mark.class, ENTITY_ID);
    }

}
