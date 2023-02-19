//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.SettlementDefinition;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * SettlementDifinitionController: a SOFIA generated controller
 */
public class SettlementDifinitionController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _countriesCAP;
    public com.salmonllc.html.HtmlText _definitionCAP;
    public com.salmonllc.html.HtmlText _definitions;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _definition;
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
    public com.itm.vuz.vocabularies.models.HibernateSettlementDefinitionModel _hibernateSettlementDefinitionDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";

    public static final String HIBERNATESETTLEMENTDEFINITIONDATASOURCE_SETTLEMENTDEFINITION_SETTLEMENTDEFINITIONID = "SettlementDefinition.settlementDefinitionId";
    public static final String HIBERNATESETTLEMENTDEFINITIONDATASOURCE_SETTLEMENTDEFINITION_SETTLEMENTS_EMPTY = "SettlementDefinition.settlements.empty";
    public static final String HIBERNATESETTLEMENTDEFINITIONDATASOURCE_SETTLEMENTDEFINITION_NAME = "SettlementDefinition.name";


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
            SettlementDefinition settlementDefinition = (SettlementDefinition) _hibernateSettlementDefinitionDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete settlementDefinition with name = [" +
                    settlementDefinition.getName() + "]", this);


            if (settlementDefinition.getSettlements() != null && settlementDefinition.getSettlements().size() > 0) {
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить тип месторасположения " + settlementDefinition.getName());
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