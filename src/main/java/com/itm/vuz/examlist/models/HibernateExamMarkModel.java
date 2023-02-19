package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.ExamMark;

/**
 * User: Alexei
 * Date: 28.05.2006
 * Time: 22:05:02
 */
public class HibernateExamMarkModel extends HibernateDataStore {

	public static final String EXAMMARKID = "ExamMark.examMarkId";
	public static final String DISCIPLINE_HOURS = "ExamMark.discipline.hours";
	public static final String DISCIPLINE_YEAR = "ExamMark.discipline.year";
	public static final String DISCIPLINE_EXAMMARKS_EMPTY = "ExamMark.discipline.examMarks.empty";
	public static final String DISCIPLINE_MARKS_EMPTY = "ExamMark.discipline.marks.empty";
	public static final String DISCIPLINE_TEACHERS_EMPTY = "ExamMark.discipline.teachers.empty";
	public static final String DISCIPLINE_DISCIPLINE_ID = "ExamMark.discipline.disciplineId";
	public static final String DISCIPLINE_SEMESTER = "ExamMark.discipline.semester";
	public static final String DISCIPLINE_EDUCATIONPLAN_EDUCATION_PLAN_ID = "ExamMark.discipline.educationPlan.educationPlanId";
	public static final String DISCIPLINE_EDUCATIONPLAN_DATA_START = "ExamMark.discipline.educationPlan.dataStart";
	public static final String DISCIPLINE_EDUCATIONPLAN_DATA_END = "ExamMark.discipline.educationPlan.dataEnd";
	public static final String DISCIPLINE_EDUCATIONPLAN_SPECIALITY_NAME = "ExamMark.discipline.educationPlan.speciality.name";
	public static final String DISCIPLINE_EDUCATIONPLAN_SPECIALITY_NUMBER = "ExamMark.discipline.educationPlan.speciality.number";
	public static final String DISCIPLINE_EDUCATIONPLAN_SPECIALITY_SPECIALITY_ID = "ExamMark.discipline.educationPlan.speciality.specialityId";
	public static final String DISCIPLINE_EDUCATIONPLAN_SPECIALITY_SHORT_NAME = "ExamMark.discipline.educationPlan.speciality.shortname";
	public static final String DISCIPLINE_EDUCATIONPLAN_DISCIPLINES_EMPTY = "ExamMark.discipline.educationPlan.disciplines.empty";
	public static final String DISCIPLINE_BASEDISCIPLINE_NAME = "ExamMark.discipline.baseDiscipline.name";
	public static final String DISCIPLINE_BASEDISCIPLINE_BASE_DISCIPLINE_ID = "ExamMark.discipline.baseDiscipline.baseDisciplineId";
	public static final String DISCIPLINE_BASEDISCIPLINE_DISCIPLINES_EMPTY = "ExamMark.discipline.baseDiscipline.disciplines.empty";
	public static final String DISCIPLINE_BASEDISCIPLINE_SUBFACULTY_NAME = "ExamMark.discipline.baseDiscipline.subfaculty.name";
	public static final String DISCIPLINE_BASEDISCIPLINE_SUBFACULTY_SUBFACULTY_ID = "ExamMark.discipline.baseDiscipline.subfaculty.subfacultyId";
	public static final String DISCIPLINE_BASEDISCIPLINE_SUBFACULTY_SUBFACULTY_NUMBER = "ExamMark.discipline.baseDiscipline.subfaculty.subfacultyNumber";
	public static final String DISCIPLINE_DISCIPLINE_TEACHERS_EMPTY = "ExamMark.discipline.disciplineTeachers.empty";
	public static final String STUDENTGROUP_NUMBER = "ExamMark.studentGroup.number";
	public static final String STUDENTGROUP_SPECIALITY_NAME = "ExamMark.studentGroup.speciality.name";
	public static final String STUDENTGROUP_SPECIALITY_NUMBER = "ExamMark.studentGroup.speciality.number";
	public static final String STUDENTGROUP_SPECIALITY_EDUCATIONPLANS_EMPTY = "ExamMark.studentGroup.speciality.educationPlans.empty";
	public static final String STUDENTGROUP_SPECIALITY_SPECIALITY_ID = "ExamMark.studentGroup.speciality.specialityId";
	public static final String STUDENTGROUP_SPECIALITY_FACULTY_NAME = "ExamMark.studentGroup.speciality.faculty.name";
	public static final String STUDENTGROUP_SPECIALITY_FACULTY_FACULTY_ID = "ExamMark.studentGroup.speciality.faculty.facultyId";
	public static final String STUDENTGROUP_SPECIALITY_FACULTY_SHORT_NAME = "ExamMark.studentGroup.speciality.faculty.shortName";
	public static final String STUDENTGROUP_SPECIALITY_SHORT_NAME = "ExamMark.studentGroup.speciality.shortname";
	public static final String STUDENTGROUP_SPECIALITY_STUDENT_GROUPS_EMPTY = "ExamMark.studentGroup.speciality.studentGroups.empty";
	public static final String STUDENTGROUP_EXAMMARKS_EMPTY = "ExamMark.studentGroup.examMarks.empty";
	public static final String STUDENTGROUP_MARKS_EMPTY = "ExamMark.studentGroup.marks.empty";
	public static final String STUDENTGROUP_STUDENT_GROUP_ID = "ExamMark.studentGroup.studentGroupId";
	public static final String STUDENTGROUP_EDUCATION_DURATION = "ExamMark.studentGroup.educationDuration";
	public static final String STUDENTGROUP_PERSONAL_NAME = "ExamMark.studentGroup.personal.name";
	public static final String STUDENTGROUP_PERSONAL_USERS_EMPTY = "ExamMark.studentGroup.personal.users.empty";
	public static final String STUDENTGROUP_PERSONAL_POSITION_NAME = "ExamMark.studentGroup.personal.position.name";
	public static final String STUDENTGROUP_PERSONAL_POSITION_POSITION_ID = "ExamMark.studentGroup.personal.position.positionId";
	public static final String STUDENTGROUP_PERSONAL_FAMILY_NAME = "ExamMark.studentGroup.personal.familyName";
	public static final String STUDENTGROUP_PERSONAL_TEACHERS_EMPTY = "ExamMark.studentGroup.personal.teachers.empty";
	public static final String STUDENTGROUP_PERSONAL_STUDENT_GROUPS_EMPTY = "ExamMark.studentGroup.personal.studentGroups.empty";
	public static final String STUDENTGROUP_PERSONAL_PATRONYMIC = "ExamMark.studentGroup.personal.patronymic";
	public static final String STUDENTGROUP_PERSONAL_PERSONAL_ID = "ExamMark.studentGroup.personal.personalId";
	public static final String STUDENTGROUP_PERSONAL_DEPARTAMENT_NAME = "ExamMark.studentGroup.personal.departament.name";
	public static final String STUDENTGROUP_PERSONAL_DEPARTAMENT_DEPARTMANENT_ID = "ExamMark.studentGroup.personal.departament.departmanentId";
	public static final String STUDENTGROUP_NOTES_EMPTY = "ExamMark.studentGroup.notes.empty";
	public static final String STUDENTGROUP_STUDENTS_EMPTY = "ExamMark.studentGroup.students.empty";
	public static final String EXAMLIST_NUMBER = "ExamMark.examList.number";
	public static final String EXAMLIST_EXAM_LIST_ID = "ExamMark.examList.examListId";
	public static final String EXAMLIST_DATE_CREATION = "ExamMark.examList.dateCreation";
	public static final String EXAMLIST_EXAMMARKS_EMPTY = "ExamMark.examList.examMarks.empty";
	public static final String EXAMLIST_MARKS_EMPTY = "ExamMark.examList.marks.empty";
	public static final String STUDENT_ADDRESS_ADDRESS = "ExamMark.student.address.address";
	public static final String STUDENT_ADDRESS_STUDENTS_EMPTY = "ExamMark.student.address.students.empty";
	public static final String STUDENT_ADDRESS_ADDRESS_ID = "ExamMark.student.address.addressId";
	public static final String STUDENT_ADDRESS_SETTLEMENT_NAME = "ExamMark.student.address.settlement.name";
	public static final String STUDENT_ADDRESS_SETTLEMENT_SETTLEMENTID = "ExamMark.student.address.settlement.settlementId";
	public static final String STUDENT_NAME = "ExamMark.student.name";
	public static final String STUDENT_LANGUAGE_NAME = "ExamMark.student.language.name";
	public static final String STUDENT_LANGUAGE_STUDENTS_EMPTY = "ExamMark.student.language.students.empty";
	public static final String STUDENT_LANGUAGE_LANGUAGE_ID = "ExamMark.student.language.languageId";
	public static final String STUDENT_COUNTRY_NAME = "ExamMark.student.country.name";
	public static final String STUDENT_COUNTRY_STUDENTS_EMPTY = "ExamMark.student.country.students.empty";
	public static final String STUDENT_COUNTRY_COUNTRYID = "ExamMark.student.country.countryId";
	public static final String STUDENT_COUNTRY_REGIONS_EMPTY = "ExamMark.student.country.regions.empty";
	public static final String STUDENT_FAMILYNAME = "ExamMark.student.familyName";
	public static final String STUDENT_EXAMMARKS_EMPTY = "ExamMark.student.examMarks.empty";
	public static final String STUDENT_MARKS_EMPTY = "ExamMark.student.marks.empty";
	public static final String STUDENT_STUDENTGROUP_NUMBER = "ExamMark.student.studentGroup.number";
	public static final String STUDENT_STUDENTGROUP_SPECIALITY_NAME = "ExamMark.student.studentGroup.speciality.name";
	public static final String STUDENT_STUDENTGROUP_SPECIALITY_NUMBER = "ExamMark.student.studentGroup.speciality.number";
	public static final String STUDENT_STUDENTGROUP_SPECIALITY_SPECIALITYID = "ExamMark.student.studentGroup.speciality.specialityId";
	public static final String STUDENT_STUDENTGROUP_SPECIALITY_SHORTNAME = "ExamMark.student.studentGroup.speciality.shortname";
	public static final String STUDENT_STUDENTGROUP_EXAMMARKS_EMPTY = "ExamMark.student.studentGroup.examMarks.empty";
	public static final String STUDENT_STUDENTGROUP_MARKS_EMPTY = "ExamMark.student.studentGroup.marks.empty";
	public static final String STUDENT_STUDENTGROUP_STUDENTGROUPID = "ExamMark.student.studentGroup.studentGroupId";
	public static final String STUDENT_STUDENTGROUP_EDUCATIONDURATION = "ExamMark.student.studentGroup.educationDuration";
	public static final String STUDENT_STUDENTGROUP_PERSONAL_NAME = "ExamMark.student.studentGroup.personal.name";
	public static final String STUDENT_STUDENTGROUP_PERSONAL_FAMILYNAME = "ExamMark.student.studentGroup.personal.familyName";
	public static final String STUDENT_STUDENTGROUP_PERSONAL_PATRONYMIC = "ExamMark.student.studentGroup.personal.patronymic";
	public static final String STUDENT_STUDENTGROUP_PERSONAL_PERSONALID = "ExamMark.student.studentGroup.personal.personalId";
	public static final String STUDENT_STUDENTGROUP_NOTES_EMPTY = "ExamMark.student.studentGroup.notes.empty";
	public static final String STUDENT_STUDENTGROUP_STUDENTS_EMPTY = "ExamMark.student.studentGroup.students.empty";
	public static final String STUDENT_NOTES_EMPTY = "ExamMark.student.notes.empty";
	public static final String STUDENT_STUDENTID = "ExamMark.student.studentId";
	public static final String STUDENT_PATRONYMIC = "ExamMark.student.patronymic";
	public static final String STUDENT_STUDENT_NUMBER = "ExamMark.student.studentNumber";
	public static final String STUDENT_REGISTRATION = "ExamMark.student.registration";
	public static final String STUDENT_PASSPORT_DATA = "ExamMark.student.passportData";
	public static final String STUDENT_HOSPICENEEDED = "ExamMark.student.hospiceNeeded";
	public static final String STUDENT_SEX = "ExamMark.student.sex";
	public static final String STUDENT_CONTRACT = "ExamMark.student.contract";
	public static final String STUDENT_COURSE_LEADER = "ExamMark.student.courseLeader";
	public static final String STUDENT_LAST_EDUCATION_PLACE = "ExamMark.student.lastEducationPlace";
	public static final String STUDENT_YEAR_STARTING = "ExamMark.student.yearStarting";
	public static final String STUDENT_STUDENTSTATUS_NAME = "ExamMark.student.studentStatus.name";
	public static final String STUDENT_STUDENTSTATUS_STUDENTS_EMPTY = "ExamMark.student.studentStatus.students.empty";
	public static final String STUDENT_STUDENTSTATUS_STUDENTSTATUSID = "ExamMark.student.studentStatus.studentStatusId";
	public static final String STUDENT_BENEFIT_NAME = "ExamMark.student.benefit.name";
	public static final String STUDENT_BENEFIT_STUDENTS_EMPTY = "ExamMark.student.benefit.students.empty";
	public static final String STUDENT_BENEFIT_BENEFITID = "ExamMark.student.benefit.benefitId";
	public static final String STUDENT_HOSPICE_NAME = "ExamMark.student.hospice.name";
	public static final String STUDENT_HOSPICE_STUDENTS_EMPTY = "ExamMark.student.hospice.students.empty";
	public static final String STUDENT_HOSPICE_HOSPICEID = "ExamMark.student.hospice.hospiceId";
	public static final String STUDENT_HOSPICE_STREET = "ExamMark.student.hospice.street";
	public static final String STUDENT_HOSPICE_HOUSE = "ExamMark.student.hospice.house";
	public static final String STUDENT_HOSPICE_BUILDING = "ExamMark.student.hospice.building";
	public static final String STUDENT_STUDENTHISTORIES_EMPTY = "ExamMark.student.studentHistories.empty";

	public HibernateExamMarkModel() {
		addBeanDefinition(ExamMark.class, "ExamMark");


	}
}
