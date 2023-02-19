package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Subfaculty;

/**
 * User: Alexei
 * Date: 28.05.2006
 * Time: 20:04:53
 */
public class HibernateSubFacultyModel extends HibernateDataStore {

	public static final String NAME = "Subfaculty.name";
	public static final String SUBFACULTYID = "Subfaculty.subfacultyId";
	public static final String SUBFACULTYNUMBER = "Subfaculty.subfacultyNumber";
	public static final String FACULTY_NAME = "Subfaculty.faculty.name";
	public static final String FACULTY_FACULTYID = "Subfaculty.faculty.facultyId";
	public static final String FACULTY_SHORTNAME = "Subfaculty.faculty.shortName";
	public static final String FACULTY_FACULTYGROUPCODES_EMPTY = "Subfaculty.faculty.facultyGroupCodes.empty";
	public static final String FACULTY_SUBFACULTIES_EMPTY = "Subfaculty.faculty.subfaculties.empty";
	public static final String FACULTY_SPECIALITIES_EMPTY = "Subfaculty.faculty.specialities.empty";
	public static final String BASEDISCIPLINES_EMPTY = "Subfaculty.baseDisciplines.empty";
	public static final String TEACHERS_EMPTY = "Subfaculty.teachers.empty";

	public HibernateSubFacultyModel() {
		addBeanDefinition(Subfaculty.class, "Subfaculty");
	}
}
