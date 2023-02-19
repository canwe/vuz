/*
 * $Id$
 * Created on 27.05.2006 13:58:19
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Note;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateStudentNoteModel extends HibernateDataStore{

    public static final String ENTITY_ID = "Note";
    public static final String GROUP_ID = ENTITY_ID + "." + "studentGroup.studentGroupId";
    public static final String STUDENT_ID = ENTITY_ID + "." + "student.studentId";
    public static final String CARD_COURSE = ENTITY_ID + "." + "card.course";
    public static final String CARD_SEMESTER = ENTITY_ID + "." + "card.semester";
    public static final String CARD_YEAR = ENTITY_ID + "." + "card.year";
    
    public HibernateStudentNoteModel(){
        addBeanDefinition(Note.class, ENTITY_ID);
    }

}
