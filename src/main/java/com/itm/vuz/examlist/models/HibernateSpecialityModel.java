package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Speciality;

/**
 * Created by IntelliJ IDEA.
 * User: Alexei
 * Date: 28.05.2006
 * Time: 19:50:25
 * To change this template use File | Settings | File Templates.
 */
public class HibernateSpecialityModel extends HibernateDataStore {

	public static final String NAME = "Speciality.name";
	public static final String NUMBER = "Speciality.number";
	public static final String FACULTY_NAME = "Speciality.faculty.name";
	public static final String FACULTY_FACULTY_ID = "Speciality.faculty.facultyId";
	public static final String FACULTY_SHORT_NAME = "Speciality.faculty.shortName";
	public static final String FACULTY_FACULTY_GROUP_CODES_EMPTY = "Speciality.faculty.facultyGroupCodes.empty";
	public static final String FACULTY_SUB_FACULTIES_EMPTY = "Speciality.faculty.subfaculties.empty";
	public static final String FACULTY_SPECIALITIES_EMPTY = "Speciality.faculty.specialities.empty";
	public static final String SPECIALITY_ID = "Speciality.specialityId";
	public static final String SHORT_NAME = "Speciality.shortname";
	public static final String STUDENT_GROUPS_EMPTY = "Speciality.studentGroups.empty";
	public static final String EDUCATION_PLANS_EMPTY = "Speciality.educationPlans.empty";

	public HibernateSpecialityModel() {
		addBeanDefinition(Speciality.class, "Speciality");
	}
}
