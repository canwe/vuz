/*
 * $Id$
 * Created on 27.05.2006 12:19:43
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

/**
 * constants
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public interface GroupCardColumnHeaders {

    public final static String FREE_ATTENDANCE = "Свободное посещение";
    public final static String REBUKE = "Выговор";
    public final static String LAST_EXAM_DATE = "Последний экзамен";
    public final static String SESSION_PROLONGATION = "Продление сессии";
    public final static String SESSION_CLOSING_DATE = "Закрытие сессии";
    public final static String STUDENT_GRANT = "Стипендия";
    public final static String PRACTICE = "Практика";


    public static final String []headers =
        {FREE_ATTENDANCE, REBUKE, LAST_EXAM_DATE, SESSION_PROLONGATION, SESSION_CLOSING_DATE, STUDENT_GRANT, PRACTICE};

    public static final String []values =
        {"Student.Notes.FreeAttendance",
         "Student.Notes.Rebuke",
         "Student.Notes.LastExamDate",
         "Student.Notes.SessionProlongationDate",
         "Student.Notes.SessionClosingDate",
         "Student.Notes.Studentgrant",
         "Student.Notes.Practice"};
/*
    public static final Class []classes =
        {java.lang.Short.class,
         java.lang.String.class,
         java.util.Date.class,
         java.util.Date.class,
         java.util.Date.class,
         java.lang.String.class,
         java.lang.String.class};
*/
    public static final Class []classes =
        {String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class};

}
