package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Teacher;
import com.itm.vuz.common.domain.Discipline;
import com.itm.vuz.common.controllers.BaseVocabulariesController;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 7:04:32
 */
public class DisciplineTeacherController extends BaseVocabulariesController
        implements SubmitListener, PageListener {
    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _disciplineSelect;
    public com.salmonllc.html.HtmlDropDownList _teacherSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _disciplines;
    public com.salmonllc.html.HtmlText _disciplineCAP;
    public com.salmonllc.html.HtmlText _disciplinesCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _teacherCAP;
    public com.salmonllc.html.HtmlText _teachersCAP;
    public com.salmonllc.html.HtmlText _teacherss;
    public com.salmonllc.html.HtmlText _teacherss1;
    public com.salmonllc.html.HtmlText _teacherss2;
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
    public com.itm.vuz.vocabularies.models.HibernateDisciplineModel _hibernateDisciplineModel;
    public com.itm.vuz.vocabularies.models.HibernateDisciplineTeacherModel _hibernateDisciplineTeacherDatasource;
    public com.itm.vuz.vocabularies.models.HibernateTeacherModel _hibernateTeacherModel;
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

            initializeDisciplineComboBox();
            initializeTeacherComboBox();
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

    private void initializeTeacherComboBox() throws Exception {
        _teacherSelect.resetOptions();
        _hibernateTeacherModel.reset();
        _hibernateTeacherModel.retrieve();
        for (int i = 0; i < _hibernateTeacherModel.getRowCount(); i++) {
            Teacher teacher = (Teacher)_hibernateTeacherModel.getDataRow(i).getBean();
            if (teacher != null) {
                _teacherSelect.addOption(teacher.getTeacherId().toString(),
                        teacher.getPersonal().getFamilyName() + " " +
                        teacher.getPersonal().getName() + " " +
                        teacher.getPersonal().getPatronymic());
            }
        }
    }

    private void initializeDisciplineComboBox() throws Exception {
        _disciplineSelect.resetOptions();
        _hibernateDisciplineModel.reset();
        _hibernateDisciplineModel.retrieve();
        for (int i = 0; i < _hibernateDisciplineModel.getRowCount(); i++) {
            Discipline discipline = (Discipline)
                    _hibernateDisciplineModel.getDataRow(i).getBean();
            if (discipline != null) {
                _disciplineSelect.addOption(discipline.getDisciplineId().toString(),
                        discipline.getBaseDiscipline().getName());
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
