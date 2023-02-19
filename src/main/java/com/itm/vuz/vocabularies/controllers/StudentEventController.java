//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.StudentEvent;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * StudentEventController: a SOFIA generated controller
 */
public class StudentEventController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _commentCAP;
    public com.salmonllc.html.HtmlText _comments;
    public com.salmonllc.html.HtmlText _commentsCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _studentEventCAP;
    public com.salmonllc.html.HtmlText _studentEvents;
    public com.salmonllc.html.HtmlText _studentEventsCAP;
    public com.salmonllc.html.HtmlTextEdit _comment;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
    public com.salmonllc.html.HtmlTextEdit _studentEvent;
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
    public com.itm.vuz.vocabularies.models.HibernateStudentEventModel _hibernateStudentEventDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String HIBERNATESTUDENTEVENTDATASOURCE_STUDENTEVENT_EVENTID = "StudentEvent.eventId";
    public static final String HIBERNATESTUDENTEVENTDATASOURCE_STUDENTEVENT_STUDENTHISTORIES_EMPTY = "StudentEvent.studentHistories.empty";
    public static final String HIBERNATESTUDENTEVENTDATASOURCE_STUDENTEVENT_NAME = "StudentEvent.name";
    public static final String HIBERNATESTUDENTEVENTDATASOURCE_STUDENTEVENT_COMMENT = "StudentEvent.comment";

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
            int rowNo = _l.getRowToEdit();
            StudentEvent studentEvent = (StudentEvent) _hibernateStudentEventDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete studentEvent with name = [" +
                    studentEvent.getName() + "]", this);


            if (studentEvent.getStudentHistories() != null && studentEvent.getStudentHistories().size() > 0) {
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить событие " + studentEvent.getName());
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