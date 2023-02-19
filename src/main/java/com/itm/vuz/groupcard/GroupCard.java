/*
 * $Id$
 * Created on 17.05.2006 10:28:57
 * Last modification $Date$
 */

package com.itm.vuz.groupcard;

import com.itm.vuz.common.domain.StudentGroup;

import java.util.Set;
import java.util.List;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCard {

    public static final byte TEST = 0;
    public static final byte EXAM = 1;

    public static final String STR_TEST = "Зачет";
    public static final String STR_EXAM = "Экзамен";
    public static final String STR_UNKNOWN = "Не установлено";

    private StudentGroup group;

    // Set<Discipline>
    private List disciplines;

    // Set<GroupCardStudent>
    private List groupCardStudents;


    public GroupCard() {
    }


    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public List getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List disciplines) {
        this.disciplines = disciplines;
    }

    public List getGroupCardStudents() {
        return groupCardStudents;
    }

    public void setGroupCardStudents(List groupCardStudents) {
        this.groupCardStudents = groupCardStudents;
    }

    public static String checkingFormToString(byte checkingForm) {
        String ret = null;
        switch(checkingForm){
            case TEST:
                ret = STR_TEST;
                break;
            case EXAM:
                ret = STR_EXAM;
                break;
            default:
                ret = STR_UNKNOWN;
                break;
        }
        return ret;
    }
}
