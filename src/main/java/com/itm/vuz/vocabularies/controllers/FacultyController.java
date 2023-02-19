//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.*;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Faculty;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * FacultyController: a SOFIA generated controller
 */
public class FacultyController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _codeCAP;
    public com.salmonllc.html.HtmlText _faculties;
    public com.salmonllc.html.HtmlText _facultiesCAP;
    public com.salmonllc.html.HtmlText _facultyCAP;
    public com.salmonllc.html.HtmlText _facultyCodes;
    public com.salmonllc.html.HtmlText _facultyCodesCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _shortNameCAP;
    public com.salmonllc.html.HtmlText _shortNames;
    public com.salmonllc.html.HtmlText _shortNamesCAP;
    public com.salmonllc.html.HtmlTextEdit _faculty;
    public com.salmonllc.html.HtmlTextEdit _facultyCode;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
    public com.salmonllc.html.HtmlTextEdit _shortName;
    public com.salmonllc.jsp.JspBox _box1;
    public com.salmonllc.jsp.JspBox _box2;
    public com.salmonllc.jsp.JspBox _box3;
    public com.salmonllc.jsp.JspBox _box4;
    public com.salmonllc.jsp.JspContainer _noCache;
    public com.salmonllc.jsp.JspDataTable _datatable1;
    public com.salmonllc.jsp.JspForm _pageForm;
    public com.salmonllc.jsp.JspListFormDisplayBox _l;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateFacultyModel _hibernateFacultyDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_NAME = "Faculty.name";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_FACULTYID = "Faculty.facultyId";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_SHORTNAME = "Faculty.shortName";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_FACULTYCODE = "Faculty.facultyCode";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_FACULTYGROUPCODES_EMPTY = "Faculty.facultyGroupCodes.empty";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_SUBFACULTIES_EMPTY = "Faculty.subfaculties.empty";
    public static final String HIBERNATEFACULTYDATASOURCE_FACULTY_SPECIALITIES_EMPTY = "Faculty.specialities.empty";

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {

        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // faculty
            Faculty faculty = (Faculty) _hibernateFacultyDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete faculty with name = [" +
                    faculty.getName() + "]", this);


            if ((faculty.getSubfaculties() != null && faculty.getSubfaculties().size() > 0) ||
                    (faculty.getSpecialities() != null && faculty.getSpecialities().size() > 0) ||
                    (faculty.getFacultyGroupCodes() != null && faculty.getFacultyGroupCodes().size() > 0)
            ) {
                // there are subfaculties, specialities, facultygroupcodes that contain faculty as Faculty,
                // delete cannot be performed, delete all including information first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить факультет " + faculty.getName() + ". Есть связанная информация(специальности, кафедры, комментарии), сперва удалите её.");
                    _d.tryCancel();
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
                return false;

            }
        }

        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) {
        try {

            super.pageRequested(event);

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) throws Exception {

    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
        _box2.setVisible(true);
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

}