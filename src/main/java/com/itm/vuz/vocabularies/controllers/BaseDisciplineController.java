package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Subfaculty;
import com.itm.vuz.common.domain.BaseDiscipline;
import com.itm.vuz.common.controllers.BaseVocabulariesController;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 23:54:57
 */
public class BaseDisciplineController extends BaseVocabulariesController
        implements SubmitListener, PageListener {
    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _subfacultySelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _baseDisciplineCAP;
    public com.salmonllc.html.HtmlText _baseDisciplines;
    public com.salmonllc.html.HtmlText _baseDisciplinesCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _subfaculties;
    public com.salmonllc.html.HtmlText _subfacultyCAP;
    public com.salmonllc.html.HtmlTextEdit _baseDiscipline;
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
    public com.itm.vuz.vocabularies.models.HibernateBaseDisciplineModel _hibernateBaseDisciplineDatasource;
    public com.itm.vuz.vocabularies.models.HibernateSubfacultyModel _hibernateSubfacultyModel;
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

        _d.setConfirmDelete(true);

        //is delete button submitted?
        if(event.getSource() == _d.getDeleteButton()){
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // discipline
            BaseDiscipline baseDiscipline = (BaseDiscipline)_hibernateBaseDisciplineDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete base discipline with name = [" +
                    baseDiscipline.getName() + "]", this);


            if(baseDiscipline.getDisciplines()!=null && baseDiscipline.getDisciplines().size()>0){
                // there are disciplines that contain baseDiscipline as Base Discipline,
                // delete cannot be performed, delete all disciplines first
                try{
                    _d.getValidator().addErrorMessage("???????????????????? ?????????????? ?????????????? ???????????????????? " + baseDiscipline.getName() + ". ???????? ?????????????????? ????????????????????, ???????????? ?????????????? ????.");
                    _d.tryCancel();
                }catch(Exception e){
                    MessageLog.writeErrorMessage(e, this);
                }
                return false;

            }
        }
/*
        if(event.getSource() == _d.getSaveButton() || event.getSource() == _d.getAddButton()){
            try{
                //int rowNo = _l.getRowToEdit();
                // discipline
                //BaseDiscipline baseDiscipline = (BaseDiscipline)_hibernateBaseDisciplineDatasource.getDataRow(rowNo).getBean();
                //_hibernateBaseDisciplineDatasource.beanUpdated(baseDiscipline.getSubfaculty(), event.getSource() == _d.getAddButton());
                //if(event.getSource() == _d.getSaveButton()){
                    //_d.doSave();
                //}else{
                    //_d.tryAdd();
                    _hibernateBaseDisciplineDatasource.update();
                    _hibernateSubfacultyModel.update();
                //}
                return false;
            }catch(Exception e){
                MessageLog.writeErrorMessage(e, this);
            }
        }
*/
        if(event.getSource()==_d.getSaveButton()){
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

            initializeComboBox();
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

    private void initializeComboBox() throws Exception {
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
