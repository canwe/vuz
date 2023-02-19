//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.StudentGroup;
import com.itm.vuz.common.domain.Personal;
import com.itm.vuz.common.domain.Speciality;
import com.itm.vuz.common.controllers.BaseVocabulariesController;

/**
 * StudentGroupController: a SOFIA generated controller
 */
public class StudentGroupController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _curatorSelect;
    public com.salmonllc.html.HtmlDropDownList _specialitySelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlDropDownList _courseSelect;
    public com.salmonllc.html.HtmlText _courseCAP;
    public com.salmonllc.html.HtmlText _courses;
    public com.salmonllc.html.HtmlText _coursesCAP;
    public com.salmonllc.html.HtmlText _curatorCAP;
    public com.salmonllc.html.HtmlText _curators;
    public com.salmonllc.html.HtmlText _curatorsCAP;
    public com.salmonllc.html.HtmlText _educationdurationCAP;
    public com.salmonllc.html.HtmlText _educationdurations;
    public com.salmonllc.html.HtmlText _educationdurationsCAP;
    public com.salmonllc.html.HtmlText _numberCAP;
    public com.salmonllc.html.HtmlText _numbers;
    public com.salmonllc.html.HtmlText _numbersCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _specialities;
    public com.salmonllc.html.HtmlText _specialitiesCAP;
    public com.salmonllc.html.HtmlText _specialityCAP;
    public com.salmonllc.html.HtmlTextEdit _educationduration;
    public com.salmonllc.html.HtmlTextEdit _number;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader4;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow4;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernatePersonalModel _hibernatePersonalModel;
    public com.itm.vuz.vocabularies.models.HibernateSpecialityModel _hibernateSpecialityModel;
    public com.itm.vuz.vocabularies.models.HibernateStudentGroupModel _hibernateStudentGroupDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";
    public HtmlHiddenField _refreshFlag;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
         for(int i=1;i<=6;i++)
        _courseSelect.addOption("" +i,"" +i);
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
            // discipline
            StudentGroup studentGroup = (StudentGroup) _hibernateStudentGroupDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete group with number = [" +
                    studentGroup.getNumber() + "]", this);


            if (studentGroup.getStudents() != null && studentGroup.getStudents().size() > 0) {
                // there are disciplines that contain baseDiscipline as Base Discipline,
                // delete cannot be performed, delete all disciplines first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить группу с номером " + studentGroup.getNumber() + ". Есть студенты, закреплённые за ней; сперва удалите их.");
                    _d.tryCancel();
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
                return false;

            }
        }
        if (event.getSource() == _d.getSaveButton()) {
            _refreshFlag.setValue("true");
            //if(_curatorSelect.getValue().equals("")){}
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

            initializePersonalComboBox();
            initializeSpecialityComboBox();
            if (_refreshFlag.getValue().equals("true")) {
                try {
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                } catch (Exception e) {
                    MessageLog.writeErrorMessage("Error in ", e, this);
                }
            }
            //initializeSemesterTextField();
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }


    private void initializePersonalComboBox() throws Exception {
        _curatorSelect.resetOptions();
        _hibernatePersonalModel.reset();
        _hibernatePersonalModel.retrieve();
        _curatorSelect.addOption("", "");
        for (int i = 0; i < _hibernatePersonalModel.getRowCount(); i++) {
            Personal personal = (Personal)
                    _hibernatePersonalModel.getDataRow(i).getBean();
            if (personal != null) {
                _curatorSelect.addOption(personal.getPersonalId().toString(),
                        personal.getFamilyName() + " " + personal.getName() + " " + personal.getPatronymic());
            }
        }
    }

    private void initializeSpecialityComboBox() throws Exception {
        _specialitySelect.resetOptions();
        _hibernateSpecialityModel.reset();
        _hibernateSpecialityModel.retrieve();
        for (int i = 0; i < _hibernateSpecialityModel.getRowCount(); i++) {
            Speciality speciality = (Speciality)
                    _hibernateSpecialityModel.getDataRow(i).getBean();
            if (speciality != null) {
                _specialitySelect.addOption(speciality.getSpecialityId().toString(),
                        speciality.getName());
            }
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