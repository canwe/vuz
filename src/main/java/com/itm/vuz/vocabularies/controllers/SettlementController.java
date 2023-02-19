package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Area;
import com.itm.vuz.common.domain.SettlementDefinition;
import com.itm.vuz.common.domain.Settlement;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * User: Alexei
 * Date: 06.07.2006
 * Time: 17:05:37
 */
public class SettlementController extends BaseVocabulariesController
        implements SubmitListener, PageListener {

    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _areaSelect;
    public com.salmonllc.html.HtmlDropDownList _settlementDefinitionSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _settlements;
    public com.salmonllc.html.HtmlText _areaCAP;
    public com.salmonllc.html.HtmlText _areasCAP;
    public com.salmonllc.html.HtmlText _areas;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _settlementCAP;
    public com.salmonllc.html.HtmlText _settlementsCAP;
    public com.salmonllc.html.HtmlText _settlementDefinitionCAP;
    public com.salmonllc.html.HtmlText _settlementDefinitionsCAP;
    public com.salmonllc.html.HtmlText _settlementDefinitions;
    public com.salmonllc.html.HtmlTextEdit _address;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateSettlementModel _hibernateSettlementDatasource;
    public com.itm.vuz.vocabularies.models.HibernateAreaModel _hibernateAreaModel;
    public com.itm.vuz.vocabularies.models.HibernateSettlementDefinitionModel _hibernateSettlementDefinitionModel;
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
        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            int rowNo = _l.getRowToEdit();
            Settlement settlement = (Settlement) _hibernateSettlementDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete settlement with name = [" +
                    settlement.getName() + "]", this);


            if (settlement.getAddresses() != null && settlement.getAddresses().size() > 0) {
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить месторасположение " + settlement.getName());
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

            initializeAreaComboBox();
            initializeDefinitionComboBox();
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

    private void initializeAreaComboBox() throws Exception {
        _areaSelect.resetOptions();
        _hibernateAreaModel.reset();
        _hibernateAreaModel.retrieve();
        _areaSelect.addOption("", "");
        for (int i = 0; i < _hibernateAreaModel.getRowCount(); i++) {
            Area area = (Area) _hibernateAreaModel.getDataRow(i).getBean();
            if (area != null) {
                _areaSelect.addOption(area.getAreaId().toString(),
                        area.getName());
            }
        }
    }

    private void initializeDefinitionComboBox() throws Exception {
        _settlementDefinitionSelect.resetOptions();
        _hibernateSettlementDefinitionModel.reset();
        _hibernateSettlementDefinitionModel.retrieve();
        _settlementDefinitionSelect.addOption("", "");
        for (int i = 0; i < _hibernateSettlementDefinitionModel.getRowCount(); i++) {
            SettlementDefinition settlementDefinition =
                    (SettlementDefinition) _hibernateSettlementDefinitionModel.getDataRow(i).getBean();
            if (settlementDefinition != null) {
                _settlementDefinitionSelect.addOption(settlementDefinition.getSettlementDefinitionId().toString(),
                        settlementDefinition.getName());
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
