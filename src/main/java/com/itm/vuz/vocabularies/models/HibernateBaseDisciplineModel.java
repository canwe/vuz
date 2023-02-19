package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.BaseDiscipline;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:59:48
 */
public class HibernateBaseDisciplineModel
        extends HibernateDataStore {

    public static final String NAME = "BaseDiscipline.name";
    public static final String BASEDISCIPLINEID = "BaseDiscipline.baseDisciplineId";
    public static final String SUBFACULTY_NAME = "BaseDiscipline.subfaculty.name";
    public static final String SUBFACULTY_SUBFACULTYID = "BaseDiscipline.subfaculty.subfacultyId";
    public static final String SUBFACULTY_SUBFACULTYNUMBER = "BaseDiscipline.subfaculty.subfacultyNumber";
    public static final String SUBFACULTY_FACULTY_NAME = "BaseDiscipline.subfaculty.faculty.name";
    public static final String SUBFACULTY_FACULTY_FACULTYID = "BaseDiscipline.subfaculty.faculty.facultyId";
    public static final String SUBFACULTY_FACULTY_SHORTNAME = "BaseDiscipline.subfaculty.faculty.shortName";
    public static final String SUBFACULTY_FACULTY_FACULTYCODE = "BaseDiscipline.subfaculty.faculty.facultyCode";
    public static final String SUBFACULTY_FACULTY_FACULTYGROUPCODES_EMPTY = "BaseDiscipline.subfaculty.faculty.facultyGroupCodes.empty";
    public static final String SUBFACULTY_FACULTY_SUBFACULTIES_EMPTY = "BaseDiscipline.subfaculty.faculty.subfaculties.empty";
    public static final String SUBFACULTY_FACULTY_SPECIALITIES_EMPTY = "BaseDiscipline.subfaculty.faculty.specialities.empty";
    public static final String SUBFACULTY_BASEDISCIPLINES_EMPTY = "BaseDiscipline.subfaculty.baseDisciplines.empty";
    public static final String SUBFACULTY_TEACHERS_EMPTY = "BaseDiscipline.subfaculty.teachers.empty";
    public static final String DISCIPLINES_EMPTY = "BaseDiscipline.disciplines.empty";

    public HibernateBaseDisciplineModel() {
        addBeanDefinition(BaseDiscipline.class, "BaseDiscipline");

        try {
            addRequiredRule(NAME, "Введите BaseDiscipline");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
