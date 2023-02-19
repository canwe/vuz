//package statement
package com.itm.vuz.edplans.controllers;

//Salmon import statements

import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;
import com.salmonllc.html.HtmlText;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.edplans.FacultyHelper;

import java.util.Set;
import java.util.Iterator;


/**
 * EducationPlansController: a SOFIA generated controller
 */
public class EducationPlansController extends BaseVUZController implements SubmitListener, PageListener, ValueChangedListener {

//Visual Components
    //public com.salmonllc.html.HtmlCheckBox _deleteCheckBox;
    public com.salmonllc.html.HtmlDropDownList _facultySearchSelect;
    public com.salmonllc.html.HtmlDropDownList _specialitySearchSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public HtmlText _dateEnd;
    public HtmlText _dateEndCap;
    public HtmlText _dateStart;
    public HtmlText _dateStartCap;
    //public com.salmonllc.html.HtmlText _educationPlanIdCap;
    public HtmlText _facultySearchLabel;
    public HtmlText _specialitySearchLabel;
    public com.salmonllc.html.HtmlTextEdit _facultyShortName;
    public JspContainer _noCache;
    public JspDataTable _datatable1;
    public JspForm _pageForm;
    public JspListFormDisplayBox _educationPlansDisplay;
    public JspSearchFormDisplayBox _edPlansSearch;
    public JspTable _groupSearch;
    public JspTableCell _datatable1TDHeader0;
    public JspTableCell _datatable1TDHeader1;
    public JspTableCell _datatable1TDHeader2;
    public JspTableCell _datatable1TDRow0;
    public JspTableCell _datatable1TDRow1;
    public JspTableCell _datatable1TDRow2;
    public JspTableCell _groupSearchTDRow0;
    public JspTableCell _groupSearchTDRow1;
    public JspTableCell _groupSearchTDRow2;
    public JspTableCell _groupSearchTDRow3;
    public JspTableRow _datatable1TRHeader0;
    public JspTableRow _datatable1TRRow0;
    public JspTableRow _groupSearchTRRow0;
    public JspTableRow _groupSearchTRRow1;

    public HtmlHiddenField _specialityHidden;

//DataSources
    public com.itm.vuz.edplans.models.EducationPlansQBEBuuilder _educationPlanQBE;
    public com.itm.vuz.edplans.models.HibernateEducationPlansModel _educationPlanModel;
    public com.itm.vuz.examlist.models.HibernateFacultyModel _facultyModel;

//DataSource Column Constants
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_EDUCATIONPLANID = "EducationPlan.educationPlanId";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_DATASTART = "EducationPlan.dataStart";

    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_DATAEND = "EducationPlan.dataEnd";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_SPECIALITYID = "EducationPlan.speciality.specialityId";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_SHORTNAME = "EducationPlan.speciality.shortname";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_SHORTNAME = "EducationPlan.speciality.faculty.shortName";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_FACULTYID = "EducationPlan.speciality.faculty.facultyId";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_SPECIALITIES_EMPTY = "EducationPlan.speciality.faculty.specialities.empty";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_FACULTYCODE = "EducationPlan.speciality.faculty.facultyCode";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_FACULTYGROUPCODES_EMPTY = "EducationPlan.speciality.faculty.facultyGroupCodes.empty";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_SUBFACULTIES_EMPTY = "EducationPlan.speciality.faculty.subfaculties.empty";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_FACULTY_NAME = "EducationPlan.speciality.faculty.name";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_STUDENTGROUPS_EMPTY = "EducationPlan.speciality.studentGroups.empty";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_EDUCATIONPLANS_EMPTY = "EducationPlan.speciality.educationPlans.empty";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_NAME = "EducationPlan.speciality.name";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_SPECIALITY_NUMBER = "EducationPlan.speciality.number";
    public static final String EDUCATIONPLANMODEL_EDUCATIONPLAN_DISCIPLINES_EMPTY = "EducationPlan.disciplines.empty";

    public static final String FACULTYMODEL_FACULTY_SHORTNAME = "Faculty.shortName";
    public static final String FACULTYMODEL_FACULTY_FACULTYID = "Faculty.facultyId";
    public static final String FACULTYMODEL_FACULTY_SPECIALITIES_EMPTY = "Faculty.specialities.empty";
    public static final String FACULTYMODEL_FACULTY_FACULTYCODE = "Faculty.facultyCode";
    public static final String FACULTYMODEL_FACULTY_FACULTYGROUPCODES_EMPTY = "Faculty.facultyGroupCodes.empty";
    public static final String FACULTYMODEL_FACULTY_SUBFACULTIES_EMPTY = "Faculty.subfaculties.empty";
    public static final String FACULTYMODEL_FACULTY_NAME = "Faculty.name";

    public static final String EDUCATIONPLANQBE_EDUCATIONPLAN_SPECIALITY_SPECIALITYID = "EducationPlan.speciality.specialityId";


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {

        super.initialize();
        addPageListener(this);
        _edPlansSearch.getSearchButton().setDisplayName("Поиск");
        _datatable1.setScrollOnClickSort(true);
        _facultySearchSelect.addValueChangedListener(this);
        //_specialitySearchSelect.addValueChangedListener(this);
        retrieveSearchData();
    }

    public boolean valueChanged(ValueChangedEvent e) throws Exception {
        retrieveSearchData();
        return true;
    }


    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        MessageLog.writeDebugMessage("submitPerformed", this);
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event)  throws Exception{
        super.pageRequested(event);

        if(login!=null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)){
            _edPlansSearch.getAddButton().setVisible(true);
            _educationPlansDisplay.getAddButton().setVisible(true);
        }else{
            _edPlansSearch.getAddButton().setVisible(false);
            _educationPlansDisplay.getAddButton().setVisible(false);

        }

        MessageLog.writeDebugMessage("pageRequested", this);
    }

    private void retrieveSearchData() {
        try {
            FacultyHelper helper = new FacultyHelper();
            Faculty faculty = helper.initializeFacultySearchSelect(_facultySearchSelect, _facultyModel);
            Speciality speciality = helper.initializeSpecialitySearchSelect(faculty, _specialitySearchSelect);

        } catch (Throwable e) {
            MessageLog.writeErrorMessage("Error in retrieveData", e, this);
        }
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