package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.EducationPlan;

/**
 * User: Alexei
 * Date: 28.05.2006
 * Time: 20:08:02
 */
public class HibernateEducationPlanModel extends HibernateDataStore {

	public static final String DISCIPLINES_EMPTY = "EducationPlan.disciplines.empty";
	public static final String EDUCATIONPLANID = "EducationPlan.educationPlanId";
	public static final String DATASTART = "EducationPlan.dataStart";
	public static final String DATAEND = "EducationPlan.dataEnd";
	public static final String SPECIALITY_NAME = "EducationPlan.speciality.name";
	public static final String SPECIALITY_NUMBER = "EducationPlan.speciality.number";
	public static final String SPECIALITY_FACULTY_NAME = "EducationPlan.speciality.faculty.name";
	public static final String SPECIALITY_FACULTY_FACULTYID = "EducationPlan.speciality.faculty.facultyId";
	public static final String SPECIALITY_FACULTY_SHORTNAME = "EducationPlan.speciality.faculty.shortName";
	public static final String SPECIALITY_FACULTY_FACULTYGROUPCODES_EMPTY = "EducationPlan.speciality.faculty.facultyGroupCodes.empty";
	public static final String SPECIALITY_FACULTY_SUBFACULTIES_EMPTY = "EducationPlan.speciality.faculty.subfaculties.empty";
	public static final String SPECIALITY_FACULTY_SPECIALITIES_EMPTY = "EducationPlan.speciality.faculty.specialities.empty";
	public static final String SPECIALITY_SPECIALITYID = "EducationPlan.speciality.specialityId";
	public static final String SPECIALITY_SHORTNAME = "EducationPlan.speciality.shortname";
	public static final String SPECIALITY_STUDENTGROUPS_EMPTY = "EducationPlan.speciality.studentGroups.empty";
	public static final String SPECIALITY_EDUCATIONPLANS_EMPTY = "EducationPlan.speciality.educationPlans.empty";

	public HibernateEducationPlanModel() {
		addBeanDefinition(EducationPlan.class, "EducationPlan");
	}
}
