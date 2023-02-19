//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.*;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Departament;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * DepartamentController: a SOFIA generated controller
 */
public class DepartamentController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _departamentCAP;
    public com.salmonllc.html.HtmlText _departaments;
    public com.salmonllc.html.HtmlText _departamentsCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _departament;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateDepartamentModel _hibernateDepartamentDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String HIBERNATEDEPARTAMENTDATASOURCE_DEPARTAMENT_NAME = "Departament.name";
    public static final String HIBERNATEDEPARTAMENTDATASOURCE_DEPARTAMENT_DEPARTMANENTID = "Departament.departmanentId";
    public static final String HIBERNATEDEPARTAMENTDATASOURCE_DEPARTAMENT_PERSONALS_EMPTY = "Departament.personals.empty";

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
            // departament
            Departament departament = (Departament) _hibernateDepartamentDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete base discipline with name = [" +
                    departament.getName() + "]", this);


            if (departament.getPersonals() != null && departament.getPersonals().size() > 0) {
                // there are personals that contain departament as Departament,
                // delete cannot be performed, delete all personals first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить отдел " + departament.getName() + ". Есть связанная информация о персонале, сперва удалите её.");
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