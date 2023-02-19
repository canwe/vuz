package com.itm.vuz.personalcard.models;

/**
 * User: artem1
 * Date: 20.04.2006
 * Time: 21:22:35
 */
public class StudentQBE extends com.salmonllc.sql.QBEBuilder{

    public StudentQBE() {
        this.addCriteria("Name", CRITERIA_TYPE_COMPLEX, StudentModel.NAME);
        this.addCriteria("FamilyName", CRITERIA_TYPE_COMPLEX, StudentModel.FAMILY_NAME);
        this.addCriteria("Patronymic", CRITERIA_TYPE_COMPLEX, StudentModel.PATRONYMIC);
        this.addCriteria("Benefit", CRITERIA_TYPE_EQUALS, StudentModel.BENEFIT);
        this.addCriteria("Contract", CRITERIA_TYPE_EQUALS, StudentModel.CONTRACT);
        this.addCriteria("StudentNumber", CRITERIA_TYPE_CONTAINS_IGNORE_CASE, StudentModel.STUDENT_NUMBER);
        this.addCriteria("HospiceNeeded", CRITERIA_TYPE_EQUALS, StudentModel.HOSPICE_NEEDED);
        this.addCriteria("YearStarting", CRITERIA_TYPE_EQUALS, StudentModel.YEAR_STARTING);
        this.addCriteria("StudentGroup", CRITERIA_TYPE_EQUALS, StudentModel.STUDENT_GROUP_NUMBER);
        this.addCriteria("FacultyShortName", CRITERIA_TYPE_EQUALS_IGNORE_CASE, StudentModel.FACULTY_SHORTNAME);
        this.addCriteria("SpecialityShortName", CRITERIA_TYPE_EQUALS_IGNORE_CASE, StudentModel.SPECIALITY_SHORTNAME);
    }
}
