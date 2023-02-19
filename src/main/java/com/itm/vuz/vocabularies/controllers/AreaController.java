package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Region;
import com.itm.vuz.common.domain.Area;
import com.itm.vuz.common.controllers.BaseVocabulariesController;

/**
 * User: Alexei
 * Date: 06.07.2006
 * Time: 17:38:38
 */
public class AreaController extends BaseVocabulariesController
        implements SubmitListener, PageListener {
    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _regionSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _areas;
    public com.salmonllc.html.HtmlText _areaCAP;
    public com.salmonllc.html.HtmlText _areasCAP;
    public com.salmonllc.html.HtmlText _regionCAP;
    public com.salmonllc.html.HtmlText _regions;
    public com.salmonllc.html.HtmlText _regionsCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _area;
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
    public com.itm.vuz.vocabularies.models.HibernateAreaModel _hibernateAreaDatasource;
    public com.itm.vuz.vocabularies.models.HibernateRegionModel _hibernateRegionModel;
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

        if(event.getSource()==_d.getSaveButton()){
            _refreshFlag.setValue("true");
            return true;
        }

        _d.setConfirmDelete(true);

        if(event.getSource() == _d.getDeleteButton()){
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // area
            Area area = (Area)_hibernateAreaDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete area with name = [" +
                    area.getName() + "]", this);


            if(area.getSettlements()!=null && area.getSettlements().size()>0){
                // there are settlements that contain area as Area,
                // delete cannot be performed, delete all settlements first
                try{
                    _d.getValidator().addErrorMessage("Невозможно удалить область " + area.getName() + ". Есть связанные местоположения, сперва удалите их.");
                    _d.tryCancel();
                }catch(Exception e){
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

            initializeRegionComboBox();
            if(_refreshFlag.getValue().equals("true")){
                try{
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                }catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
                }
            }
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializeRegionComboBox() throws Exception {
        _regionSelect.resetOptions();
        _hibernateRegionModel.reset();
        _hibernateRegionModel.retrieve();
        for (int i = 0; i < _hibernateRegionModel.getRowCount(); i++) {
            Region region = (Region)_hibernateRegionModel.getDataRow(i).getBean();
            if (region != null) {
                _regionSelect.addOption(region.getRegionId().toString(),
                        region.getName());
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
