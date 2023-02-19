//package statement
package com.itm.vuz.groupcard.lookup;

//Salmon import statements

import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;


/**
 * StudentGroopLookupController: a SOFIA generated controller
 */
public class StudentGroopLookupController extends JspController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlText _facultyName;
    public com.salmonllc.html.HtmlText _facultyNameCAP;
    public com.salmonllc.html.HtmlText _groupNumberCAP;
    public com.salmonllc.html.HtmlText _specialityName;
    public com.salmonllc.html.HtmlText _specialityNameCAP;
    public com.salmonllc.html.HtmlText _specialityNumberCAP;
    public com.salmonllc.html.HtmlTextEdit _groupNumber;
    public JspDataTable _datatable1;
    public JspForm _pageForm;
    public JspListFormDisplayBox _studentGroupDisplay;
    public JspSearchFormDisplayBox _studentGroupSearch;
    public JspTable _groupSearch;
    public JspTableCell _datatable1TDHeader0;
    public JspTableCell _datatable1TDHeader1;
    public JspTableCell _datatable1TDHeader2;
    public JspTableCell _datatable1TDRow0;
    public JspTableCell _datatable1TDRow1;
    public JspTableCell _datatable1TDRow2;
    public JspTableCell _groupSearchTDRow0;
    public JspTableCell _groupSearchTDRow1;
    public JspTableRow _datatable1TRHeader0;
    public JspTableRow _datatable1TRRow0;
    public JspTableRow _groupSearchTRRow0;

//DataSources
    public com.itm.vuz.groupcard.models.HibernateStudentGroupModel _studentGroupModel;
    public com.itm.vuz.groupcard.models.StudentGroupQBEBuilder _studentGroupQBE;

//DataSource Column Constants
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_NUMBER = "StudentGroup.number";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_STUDENTGROUPID = "StudentGroup.studentGroupId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_COURSE = "StudentGroup.course";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_EDUCATIONDURATION = "StudentGroup.educationDuration";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_NAME = "StudentGroup.speciality.name";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_NUMBER = "StudentGroup.speciality.number";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_SPECIALITYID = "StudentGroup.speciality.specialityId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_SHORTNAME = "StudentGroup.speciality.shortname";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_NAME = "StudentGroup.speciality.faculty.name";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_SHORTNAME = "StudentGroup.speciality.faculty.shortName";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_FACULTYID = "StudentGroup.speciality.faculty.facultyId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_FACULTYGROUPCODES_EMPTY = "StudentGroup.speciality.faculty.facultyGroupCodes.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_SUBFACULTIES_EMPTY = "StudentGroup.speciality.faculty.subfaculties.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_FACULTY_SPECIALITIES_EMPTY = "StudentGroup.speciality.faculty.specialities.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_STUDENTGROUPS_EMPTY = "StudentGroup.speciality.studentGroups.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_SPECIALITY_EDUCATIONPLANS_EMPTY = "StudentGroup.speciality.educationPlans.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_NAME = "StudentGroup.personal.name";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_FAMILYNAME = "StudentGroup.personal.familyName";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_USERS_EMPTY = "StudentGroup.personal.users.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_POSITION_NAME = "StudentGroup.personal.position.name";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_POSITION_POSITIONID = "StudentGroup.personal.position.positionId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_POSITION_PERSONALS_EMPTY = "StudentGroup.personal.position.personals.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_STUDENTGROUPS_EMPTY = "StudentGroup.personal.studentGroups.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_PERSONALID = "StudentGroup.personal.personalId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_PATRONYMIC = "StudentGroup.personal.patronymic";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_DEPARTAMENT_NAME = "StudentGroup.personal.departament.name";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_DEPARTAMENT_PERSONALS_EMPTY = "StudentGroup.personal.departament.personals.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_DEPARTAMENT_DEPARTMANENTID = "StudentGroup.personal.departament.departmanentId";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_PERSONAL_TEACHERS_EMPTY = "StudentGroup.personal.teachers.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_EXAMMARKS_EMPTY = "StudentGroup.examMarks.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_NOTES_EMPTY = "StudentGroup.notes.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_MARKS_EMPTY = "StudentGroup.marks.empty";
    public static final String STUDENTGROUPMODEL_STUDENTGROUP_STUDENTS_EMPTY = "StudentGroup.students.empty";

    public static final String STUDENTGROUPQBE_STUDENTGROUP_NUMBER = "StudentGroup.number";


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        addPageListener(this);
        _datatable1.setScrollOnClickSort(true);
        _datatable1.setRowsPerPage(5);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) {
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) {
    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

}