package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Discipline;

/**
 * User: Alexei
 * Date: 28.05.2006
 * Time: 22:03:41
 */
public class HibernateDisciplineModel extends HibernateDataStore {

	public static final String HOURS = "Discipline.hours";
	public static final String YEAR = "Discipline.year";
	public static final String EXAMMARKS_EMPTY = "Discipline.examMarks.empty";
	public static final String MARKS_EMPTY = "Discipline.marks.empty";
	public static final String TEACHERS_EMPTY = "Discipline.teachers.empty";
	public static final String DISCIPLINE_ID = "Discipline.disciplineId";
	public static final String SEMESTER = "Discipline.semester";
	public static final String EDUCATIONPLAN_EDUCATION_PLAN_ID = "Discipline.educationPlan.educationPlanId";
	public static final String EDUCATIONPLAN_DATA_START = "Discipline.educationPlan.dataStart";
	public static final String EDUCATIONPLAN_DATA_END = "Discipline.educationPlan.dataEnd";
	public static final String EDUCATIONPLAN_DISCIPLINES_EMPTY = "Discipline.educationPlan.disciplines.empty";
	public static final String EDUCATIONPLAN_SPECIALITY_NAME = "Discipline.educationPlan.speciality.name";
	public static final String EDUCATIONPLAN_SPECIALITY_NUMBER = "Discipline.educationPlan.speciality.number";
	public static final String EDUCATIONPLAN_SPECIALITY_EDUCATIONPLANS_EMPTY = "Discipline.educationPlan.speciality.educationPlans.empty";
	public static final String EDUCATIONPLAN_SPECIALITY_SPECIALITY_ID = "Discipline.educationPlan.speciality.specialityId";
	public static final String EDUCATIONPLAN_SPECIALITY_FACULTY_NAME = "Discipline.educationPlan.speciality.faculty.name";
	public static final String EDUCATIONPLAN_SPECIALITY_FACULTY_FACULTYID = "Discipline.educationPlan.speciality.faculty.facultyId";
	public static final String EDUCATIONPLAN_SPECIALITY_FACULTY_SHORTNAME = "Discipline.educationPlan.speciality.faculty.shortName";
	public static final String EDUCATIONPLAN_SPECIALITY_SHORT_NAME = "Discipline.educationPlan.speciality.shortname";
	public static final String EDUCATIONPLAN_SPECIALITY_STUDENT_GROUPS_EMPTY = "Discipline.educationPlan.speciality.studentGroups.empty";
	public static final String BASEDISCIPLINE_NAME = "Discipline.baseDiscipline.name";
	public static final String BASEDISCIPLINE_BASE_DISCIPLINE_ID = "Discipline.baseDiscipline.baseDisciplineId";
	public static final String BASEDISCIPLINE_SUBFACULTY_NAME = "Discipline.baseDiscipline.subfaculty.name";
	public static final String BASEDISCIPLINE_SUBFACULTY_BASEDISCIPLINES_EMPTY = "Discipline.baseDiscipline.subfaculty.baseDisciplines.empty";
	public static final String BASEDISCIPLINE_SUBFACULTY_SUBFACULTY_ID = "Discipline.baseDiscipline.subfaculty.subfacultyId";
	public static final String BASEDISCIPLINE_SUBFACULTY_SUBFACULTY_NUMBER = "Discipline.baseDiscipline.subfaculty.subfacultyNumber";
	public static final String BASEDISCIPLINE_SUBFACULTY_FACULTY_NAME = "Discipline.baseDiscipline.subfaculty.faculty.name";
	public static final String BASEDISCIPLINE_SUBFACULTY_FACULTY_FACULTYID = "Discipline.baseDiscipline.subfaculty.faculty.facultyId";
	public static final String BASEDISCIPLINE_SUBFACULTY_FACULTY_SHORT_NAME = "Discipline.baseDiscipline.subfaculty.faculty.shortName";
	public static final String BASEDISCIPLINE_SUBFACULTY_TEACHERS_EMPTY = "Discipline.baseDiscipline.subfaculty.teachers.empty";
	public static final String BASEDISCIPLINE_DISCIPLINES_EMPTY = "Discipline.baseDiscipline.disciplines.empty";
	public static final String DISCIPLINETEACHERS_EMPTY = "Discipline.disciplineTeachers.empty";

	public HibernateDisciplineModel() {
		addBeanDefinition(Discipline.class, "Discipline");
	}
}
