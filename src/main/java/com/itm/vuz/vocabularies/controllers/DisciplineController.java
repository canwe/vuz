package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.EducationPlan;
import com.itm.vuz.common.domain.BaseDiscipline;
import com.itm.vuz.common.domain.Discipline;
import com.itm.vuz.common.controllers.BaseVocabulariesController;
import com.itm.vuz.vocabularies.models.SemestrDSExpression;
import com.itm.vuz.vocabularies.models.checkingFormDSExpression;
import com.itm.vuz.vocabularies.models.hoursDSExpression;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 6:43:09
 */
public class DisciplineController extends BaseVocabulariesController
        implements SubmitListener, PageListener {
    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _baseDisciplineSelect;
    public com.salmonllc.html.HtmlDropDownList _educationPlanSelect;
    public com.salmonllc.html.HtmlDropDownList _semesterSelect;
    public com.salmonllc.html.HtmlDropDownList _courseSelect;
    public com.salmonllc.html.HtmlDropDownList _checkingFormSelect;
    public com.salmonllc.html.HtmlDropDownList _yearSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _baseDisciplines;
    public com.salmonllc.html.HtmlText _baseDisciplineCAP;
    public com.salmonllc.html.HtmlText _baseDisciplinesCAP;
    public com.salmonllc.html.HtmlText _checkingFormCAP;
    public com.salmonllc.html.HtmlText _checkingForms;
    public com.salmonllc.html.HtmlText _checkingFormsCAP;
    public com.salmonllc.html.HtmlText _courseCAP;
    public com.salmonllc.html.HtmlText _courses;
    public com.salmonllc.html.HtmlText _coursesCAP;
    public com.salmonllc.html.HtmlText _educationPlanCAP;
    public com.salmonllc.html.HtmlText _educationPlans;
    public com.salmonllc.html.HtmlText _educationPlansCAP;
    public com.salmonllc.html.HtmlText _independenthourCAP;
    public com.salmonllc.html.HtmlText _independenthoursCAP;
    public com.salmonllc.html.HtmlText _allhoursCAP;
    public com.salmonllc.html.HtmlText _audiencehourCAP;
    public com.salmonllc.html.HtmlText _audiencehoursCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _semesterCAP;
    public com.salmonllc.html.HtmlText _semesters;
    public com.salmonllc.html.HtmlText _semestersCAP;
    public com.salmonllc.html.HtmlText _yearCAP;
    public com.salmonllc.html.HtmlText _years;
    public com.salmonllc.html.HtmlText _yearsCAP;
    public com.salmonllc.html.HtmlText _audiencehourses;
    public com.salmonllc.html.HtmlText _independenthourses;
    public com.salmonllc.html.HtmlText _allhourses;
    public com.salmonllc.html.HtmlTextEdit _audiencehours;
    public com.salmonllc.html.HtmlTextEdit _independenthours;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader4;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader5;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader6;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow4;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow5;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow6;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateBaseDisciplineModel _hibernateBaseDisciplineModel;
    public com.itm.vuz.vocabularies.models.HibernateDisciplineModel _hibernateDisciplineDatasource;
    public com.itm.vuz.vocabularies.models.HibernateEducationPlanModel _hibernateEducationPlanModel;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final int HISTORY_YEARS = 10;
    public static final int FUTURE_YEARS = 20;

    public HtmlHiddenField _refreshFlag;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        _semesterSelect.addOption("false","первый");
        _semesterSelect.addOption("true","второй");
        try{
            _semesters.setExpression(_hibernateDisciplineDatasource, new SemestrDSExpression());
            _checkingForms.setExpression(_hibernateDisciplineDatasource, new checkingFormDSExpression());
            _allhourses.setExpression(_hibernateDisciplineDatasource, new hoursDSExpression());
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
        for(int i=1;i<=6;i++)
                _courseSelect.addOption("" +i,"" +i);

        _checkingFormSelect.addOption("0","зачет");
        _checkingFormSelect.addOption("1","экзамен");

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int startYear = currentYear - HISTORY_YEARS;

         for(int i=startYear; i<=currentYear + FUTURE_YEARS;i++){
                   _yearSelect.addOption("" + i, "" + i);
                }

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

        if (event.getSource() == _d.getAddButton()) {
            _independenthours.setValue("");
            _audiencehours.setValue("");
            _courseSelect.setValue("");
            _checkingFormSelect.setValue("");
            _yearSelect.setValue("");
        }

        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // discipline
            Discipline discipline = (Discipline) _hibernateDisciplineDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete discipline with name = [" +
                    discipline.getBaseDiscipline().getName() + "]", this);           
            //discipline.get

            if ((discipline.getDisciplineTeachers() != null && discipline.getDisciplineTeachers().size() > 0) ||
                    (discipline.getMarks() != null && discipline.getMarks().size() > 0) ||
                    //(discipline.getTeachers()!=null && discipline.getTeachers().size()>0)   ||
                    (discipline.getExamMarks() != null && discipline.getExamMarks().size() > 0)
            ) {
                //there are disciplines that contain baseDiscipline as Base Discipline,
                //delete cannot be performed, delete all disciplines first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить дисциплину " + discipline.getBaseDiscipline().getName() + ". Есть связанная информация, сперва удалите её.");
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

            initializeBaseDisciplineComboBox();
            initializeEducationPlanComboBox();
            if (_refreshFlag.getValue().equals("true")) {
                try {
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                } catch (Exception e) {
                    MessageLog.writeErrorMessage("Error in ", e, this);
                }
            }
            //initializeSemesterTextField();
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializeEducationPlanComboBox() throws Exception {
        _educationPlanSelect.resetOptions();
        _hibernateEducationPlanModel.reset();
        _hibernateEducationPlanModel.retrieve();
        _educationPlanSelect.addOption("", "");
        for (int i = 0; i < _hibernateEducationPlanModel.getRowCount(); i++) {
            EducationPlan educationPlan = (EducationPlan)
                    _hibernateEducationPlanModel.getDataRow(i).getBean();
            if (educationPlan != null) {
                _educationPlanSelect.addOption(educationPlan.getEducationPlanId().toString(),
                        getEducationPlanString(educationPlan));
            }
        }
    }

    private String getEducationPlanString(EducationPlan educationPlan) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(DATE_FORMAT.format(educationPlan.getDataStart()));
        buffer.append(" - ");
        buffer.append(DATE_FORMAT.format(educationPlan.getDataEnd()));
        buffer.append(" ");
        buffer.append(educationPlan.getSpeciality().getShortname());
        return buffer.toString();
    }

    private void initializeBaseDisciplineComboBox() throws Exception {
        _baseDisciplineSelect.resetOptions();
        _hibernateBaseDisciplineModel.reset();
        _hibernateBaseDisciplineModel.retrieve();
        //_baseDisciplineSelect.addOption("","");
        for (int i = 0; i < _hibernateBaseDisciplineModel.getRowCount(); i++) {
            BaseDiscipline baseDiscipline = (BaseDiscipline)
                    _hibernateBaseDisciplineModel.getDataRow(i).getBean();
            if (baseDiscipline != null) {
                _baseDisciplineSelect.addOption(baseDiscipline.getBaseDisciplineId().toString(),
                        baseDiscipline.getName());
            }
        }
    }

    /*private void initializeSemesterTextField() {
        String yes="да";
        String no="нет";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < _hibernateDisciplineDatasource.getRowCount(); i++) {
            Discipline discipline = (Discipline)
                    _hibernateDisciplineDatasource.getDataRow(i).getBean();
            if (discipline != null) {
                if(discipline.isSemester()==true) {
                    _semesters.setText(yes);
                }
                else _semesters.setText(no);
            }
        }
    } */

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
