package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Faculty;
import com.itm.vuz.common.domain.Subfaculty;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 8:13:35
 */
public class
        SubfacultyController extends BaseVocabulariesController
        implements SubmitListener, PageListener {
    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _facultySelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _subfaculties;
    public com.salmonllc.html.HtmlText _faculties;
    public com.salmonllc.html.HtmlText _facultiesCAP;
    public com.salmonllc.html.HtmlText _facultyCAP;
    public com.salmonllc.html.HtmlText _numberCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _subfacultiesCAP;
    public com.salmonllc.html.HtmlText _subfacultyCAP;
    public com.salmonllc.html.HtmlText _subfacultyNumberCAP;
    public com.salmonllc.html.HtmlText _subfacultyNumbers;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
    public com.salmonllc.html.HtmlTextEdit _subfaculty;
    public com.salmonllc.html.HtmlTextEdit _subfacultyNumber;
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

    public com.itm.vuz.vocabularies.models.HibernateFacultyModel _hibernateFacultyModel;
    public com.itm.vuz.vocabularies.models.HibernateSubfacultyModel _hibernateSubfacultyDatasource;
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
         if(event.getSource() == _d.getSaveButton()){
         _refreshFlag.setValue("true");
     }
       _d.setConfirmDelete(true);

        //is delete button submitted?
        if(event.getSource() == _d.getDeleteButton()){
            int rowNo = _l.getRowToEdit();
           Subfaculty subfaculty = (Subfaculty)_hibernateSubfacultyDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete subfaculty with name = [" +
                    subfaculty.getName() + "]", this);


            if(subfaculty.getBaseDisciplines()!=null && subfaculty.getBaseDisciplines().size()>0){
                try{
                    _d.getValidator().addErrorMessage("Невозможно удалить кафедру " + subfaculty.getName());
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

           initializeComboBox();
           if(_refreshFlag.getValue().equals("true")){
               try{
                  _searchformdisplaybox1.trySearch();
                  _refreshFlag.setValue("false");
               }catch(Exception e){
                   MessageLog.writeErrorMessage(e, this);
               }
           }

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializeComboBox() throws Exception {
        _facultySelect.resetOptions();
        _hibernateFacultyModel.reset();
        _hibernateFacultyModel.retrieve();
        for (int i = 0; i < _hibernateFacultyModel.getRowCount(); i++) {
            Faculty faculty = (Faculty) _hibernateFacultyModel.getDataRow(i).getBean();
            if (faculty != null) {
                _facultySelect.addOption(faculty.getFacultyId().toString(),
                        faculty.getName());
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
