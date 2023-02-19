//package statement
package com.itm.vuz.edplans.controllers.lookup;

//Salmon import statements

import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;
import com.salmonllc.html.HtmlLookUpComponent;


/**
 * BaseDisciplinesLookupController: a SOFIA generated controller
 */
public class BaseDisciplinesLookupController extends JspController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlText _baseDisciplineName;
    public com.salmonllc.html.HtmlHiddenField _baseDisciplineIdQBE;
    public com.salmonllc.html.HtmlText _baseDisciplineNameCAP;
    public com.salmonllc.html.HtmlText _baseDisciplineNameQBECAP;
    public com.salmonllc.html.HtmlTextEdit _baseDisciplineNameQBE;
    public JspDataTable _datatable1;
    public JspForm _pageForm1;
    public JspListFormDisplayBox _baseDisciplinesDisplay;
    public JspSearchFormDisplayBox _baseDisciplinesSearch;
    public JspTable _baseDisciplinesSearchTable;
    public JspTableCell _baseDisciplinesSearchTableTDRow0;
    public JspTableCell _baseDisciplinesSearchTableTDRow1;
    public JspTableCell _datatable1TDHeader0;
    public JspTableCell _datatable1TDRow0;
    public JspTableRow _baseDisciplinesSearchTableTRRow0;
    public JspTableRow _datatable1TRHeader0;
    public JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.edplans.models.BaseDisciplinesQBEBuilder _baseDisciplinesQBE;
    public com.itm.vuz.edplans.models.HibernateBaseDisciplinesModel _baseDisciplinesModel;

//DataSource Column Constants
    public static final String BASEDISCIPLINESQBE_BASEDISCIPLINE_NAME = "BaseDiscipline.name";

    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_NAME = "BaseDiscipline.name";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_BASEDISCIPLINEID = "BaseDiscipline.baseDisciplineId";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_NAME = "BaseDiscipline.subfaculty.name";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_SUBFACULTYID = "BaseDiscipline.subfaculty.subfacultyId";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_SUBFACULTYNUMBER = "BaseDiscipline.subfaculty.subfacultyNumber";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_NAME = "BaseDiscipline.subfaculty.faculty.name";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_SHORTNAME = "BaseDiscipline.subfaculty.faculty.shortName";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_FACULTYID = "BaseDiscipline.subfaculty.faculty.facultyId";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_FACULTYCODE = "BaseDiscipline.subfaculty.faculty.facultyCode";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_FACULTYGROUPCODES_EMPTY = "BaseDiscipline.subfaculty.faculty.facultyGroupCodes.empty";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_SUBFACULTIES_EMPTY = "BaseDiscipline.subfaculty.faculty.subfaculties.empty";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_FACULTY_SPECIALITIES_EMPTY = "BaseDiscipline.subfaculty.faculty.specialities.empty";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_BASEDISCIPLINES_EMPTY = "BaseDiscipline.subfaculty.baseDisciplines.empty";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_SUBFACULTY_TEACHERS_EMPTY = "BaseDiscipline.subfaculty.teachers.empty";
    public static final String BASEDISCIPLINESMODEL_BASEDISCIPLINE_DISCIPLINES_EMPTY = "BaseDiscipline.disciplines.empty";


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        addPageListener(this);
        _datatable1.setScrollOnClickSort(false);
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
    public void pageRequested(PageEvent event) throws Exception {
        if (!isReferredByCurrentPage()) {
            doSearch(HtmlLookUpComponent.getParentLookupValue(this));
        }
    }

    private void doSearch(String value) throws Exception {
        //get the current value of the lookup component and run a search with it

        if (value != null) {
            try {
                Integer.parseInt(value);
                _baseDisciplineIdQBE.setValue(value);
            } catch (Exception ex) {
                _baseDisciplineIdQBE.setValue(null);
                _baseDisciplineNameQBE.setValue("");
            }
            _baseDisciplinesSearch.doSearch();
        } else {
            _baseDisciplineNameQBE.setValue("");
            _baseDisciplineNameQBE.setFocus();
            _baseDisciplinesSearch.getDataStoreBuffer().reset();
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