package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.BaseDiscipline;

/**
 * User: Alexei
 * Date: 28.05.2006
 * Time: 20:09:23
 */
public class HibernateBaseDisciplineModel extends HibernateDataStore {

	public static final String NAME = "BaseDiscipline.name";
	public static final String BASE_DISCIPLINE_ID = "BaseDiscipline.baseDisciplineId";
	public static final String SUBFACULTY_NAME = "BaseDiscipline.subfaculty.name";
	public static final String SUBFACULTY_SUBFACULTY_ID = "BaseDiscipline.subfaculty.subfacultyId";
	public static final String SUBFACULTY_SUBFACULTY_NUMBER = "BaseDiscipline.subfaculty.subfacultyNumber";
	public static final String SUBFACULTY_FACULTY_NAME = "BaseDiscipline.subfaculty.faculty.name";
	public static final String SUBFACULTY_FACULTY_FACULTY_ID = "BaseDiscipline.subfaculty.faculty.facultyId";
	public static final String SUBFACULTY_FACULTY_SHORT_NAME = "BaseDiscipline.subfaculty.faculty.shortName";
	public static final String SUBFACULTY_FACULTY_FACULTY_GROUP_CODES_EMPTY = "BaseDiscipline.subfaculty.faculty.facultyGroupCodes.empty";
	public static final String SUBFACULTY_FACULTY_SUBFACULTIES_EMPTY = "BaseDiscipline.subfaculty.faculty.subfaculties.empty";
	public static final String SUBFACULTY_FACULTY_SPECIALITIES_EMPTY = "BaseDiscipline.subfaculty.faculty.specialities.empty";
	public static final String SUBFACULTY_BASE_DISCIPLINES_EMPTY = "BaseDiscipline.subfaculty.baseDisciplines.empty";
	public static final String SUBFACULTY_TEACHERS_EMPTY = "BaseDiscipline.subfaculty.teachers.empty";
	public static final String DISCIPLINES_EMPTY = "BaseDiscipline.disciplines.empty";

	public HibernateBaseDisciplineModel() {
		addBeanDefinition(BaseDiscipline.class, "BaseDiscipline");

	}
}
