package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Subfaculty;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 8:10:47
 */
public class HibernateSubfacultyModel extends HibernateDataStore {

    public static final String NAME = "Subfaculty.name";
    public static final String SUBFACULTYID = "Subfaculty.subfacultyId";
    public static final String SUBFACULTYNUMBER = "Subfaculty.subfacultyNumber";
    public static final String FACULTY_NAME = "Subfaculty.faculty.name";
    public static final String FACULTY_SHORTNAME = "Subfaculty.faculty.shortName";
    public static final String FACULTY_FACULTYID = "Subfaculty.faculty.facultyId";
    public static final String FACULTY_FACULTYCODE = "Subfaculty.faculty.facultyCode";
    public static final String FACULTY_FACULTYGROUPCODES_EMPTY = "Subfaculty.faculty.facultyGroupCodes.empty";
    public static final String FACULTY_SUBFACULTIES_EMPTY = "Subfaculty.faculty.subfaculties.empty";
    public static final String FACULTY_SPECIALITIES_EMPTY = "Subfaculty.faculty.specialities.empty";
    public static final String BASEDISCIPLINES_EMPTY = "Subfaculty.baseDisciplines.empty";
    public static final String TEACHERS_EMPTY = "Subfaculty.teachers.empty";

    public HibernateSubfacultyModel() {
        addBeanDefinition(Subfaculty.class, "Subfaculty");

        try {
            addRequiredRule(NAME, "Введите кафедру");
            addRequiredRule(SUBFACULTYNUMBER, "Введите номер");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
