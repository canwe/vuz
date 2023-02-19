//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.*;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Hospice;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * HospiceController: a SOFIA generated controller
 */
public class HospiceController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _buildingCAP;
    public com.salmonllc.html.HtmlText _buildings;
    public com.salmonllc.html.HtmlText _buildingsCAP;
    public com.salmonllc.html.HtmlText _hospiceCAP;
    public com.salmonllc.html.HtmlText _hospices;
    public com.salmonllc.html.HtmlText _hospicesCAP;
    public com.salmonllc.html.HtmlText _houseCAP;
    public com.salmonllc.html.HtmlText _houses;
    public com.salmonllc.html.HtmlText _housesCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _streetCAP;
    public com.salmonllc.html.HtmlText _streets;
    public com.salmonllc.html.HtmlText _streetsCAP;
    public com.salmonllc.html.HtmlTextEdit _building;
    public com.salmonllc.html.HtmlTextEdit _hospice;
    public com.salmonllc.html.HtmlTextEdit _house;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
    public com.salmonllc.html.HtmlTextEdit _street;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow3;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateHospiceModel _hibernateHospiceDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";

    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_NAME = "Hospice.name";
    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_HOSPICEID = "Hospice.hospiceId";
    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_STREET = "Hospice.street";
    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_HOUSE = "Hospice.house";
    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_BUILDING = "Hospice.building";
    public static final String HIBERNATEHOSPICEDATASOURCE_HOSPICE_STUDENTS_EMPTY = "Hospice.students.empty";


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
            // hospice
            Hospice hospice = (Hospice) _hibernateHospiceDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete hospice with name = [" +
                    hospice.getName() + "]", this);


            if (hospice.getStudents() != null && hospice.getStudents().size() > 0) {
                // there are students that contain hospice as Hospice,
                // delete cannot be performed, delete all students first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить общежитие " + hospice.getName() + ". Есть студенты, проживающие в нём, сперва удалите их.");
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