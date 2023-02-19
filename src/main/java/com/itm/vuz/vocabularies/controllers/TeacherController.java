//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Personal;
import com.itm.vuz.common.domain.Subfaculty;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * TeacherController: a SOFIA generated controller
 */
public class TeacherController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _personalSelect;
    public com.salmonllc.html.HtmlDropDownList _subfacultySelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _personals;
    public com.salmonllc.html.HtmlText _personalsCAP;
    public com.salmonllc.html.HtmlText _personals1CAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _subfacultys;
    public com.salmonllc.html.HtmlText _subfacultysCAP;
    public com.salmonllc.html.HtmlText _subfacultys1CAP;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernatePersonalModel _hibernatePersonalModel;
    public com.itm.vuz.vocabularies.models.HibernateSubfacultyModel _hibernateSubfacultyModel;
    public com.itm.vuz.vocabularies.models.HibernateTeacherModel _hibernateTeacherDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;


    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";
    public HtmlHiddenField _refreshFlag;

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
        if (event.getSource() == _d.getSaveButton()) {
            _refreshFlag.setValue("true");
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
            initializeSubfacultyComboBox();
            if (_refreshFlag.getValue().equals("true")) {
                try {
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
            }

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializePersonalComboBox() throws Exception {
        _personalSelect.resetOptions();
        _hibernatePersonalModel.reset();
        _hibernatePersonalModel.retrieve();
        for (int i = 0; i < _hibernatePersonalModel.getRowCount(); i++) {
            Personal personal = (Personal)
                    _hibernatePersonalModel.getDataRow(i).getBean();
            if (personal != null) {
                _personalSelect.addOption(personal.getPersonalId().toString(),
                        personal.getFamilyName() + " " + personal.getName() + " " + personal.getPatronymic());
            }
        }
    }

    private void initializeSubfacultyComboBox() throws Exception {
        _subfacultySelect.resetOptions();
        _hibernateSubfacultyModel.reset();
        _hibernateSubfacultyModel.retrieve();
        for (int i = 0; i < _hibernateSubfacultyModel.getRowCount(); i++) {
            Subfaculty subfaculty = (Subfaculty)
                    _hibernateSubfacultyModel.getDataRow(i).getBean();
            if (subfaculty != null) {
                _subfacultySelect.addOption(subfaculty.getSubfacultyId().toString(),
                        subfaculty.getName());
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