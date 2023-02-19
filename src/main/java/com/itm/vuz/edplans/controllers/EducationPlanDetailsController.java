//package statement
package com.itm.vuz.edplans.controllers;

//Salmon import statements
import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.EducationPlan;
import com.itm.vuz.common.domain.Discipline;
import com.itm.vuz.common.domain.Faculty;
import com.itm.vuz.common.domain.Speciality;
import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.models.IncrementDSExpression;
import com.itm.vuz.edplans.FacultyHelper;
import com.itm.vuz.vocabularies.models.hoursDSExpression;

import java.util.Calendar;


/**
 * EducationPlanDetailsController: a SOFIA generated controller
 */
public class EducationPlanDetailsController extends BaseVUZController implements SubmitListener, PageListener {


    public static final int HISTORY_YEARS = 10;
    public static final int FUTURE_YEARS = 20;
//Visual Components
      public com.itm.vuz.common.CalendarComponent _edDataEnd;
      public com.itm.vuz.common.CalendarComponent _edDataStart;
      public com.salmonllc.html.HtmlCheckBox _deleteDiscipline;
      public com.salmonllc.html.HtmlDropDownList _check;
      public com.salmonllc.html.HtmlDropDownList _course;
      public com.salmonllc.html.HtmlDropDownList _facultySearchSelect;
      public com.salmonllc.html.HtmlDropDownList _semestr;
      public com.salmonllc.html.HtmlDropDownList _specialitySearchSelect;
      public com.salmonllc.html.HtmlDropDownList _year;
      public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
      public com.salmonllc.html.HtmlLookUpComponent _disciplineName;
      public com.salmonllc.html.HtmlSubmitButton _add;
      public com.salmonllc.html.HtmlSubmitButton _save;
      public com.salmonllc.html.HtmlSubmitButton _sbtEducPlan;
      public com.salmonllc.html.HtmlSubmitButton _sbtEducPlanEdit;
      public com.salmonllc.html.HtmlText _checkCap;
      public com.salmonllc.html.HtmlText _courseCap;
      public com.salmonllc.html.HtmlText _deleteDisciplineCap;
      public com.salmonllc.html.HtmlText _disciplineNameCap;
      public com.salmonllc.html.HtmlText _disciplinesCount;
      public com.salmonllc.html.HtmlText _disciplinesCountCap;
      public com.salmonllc.html.HtmlText _edDataEndCAP;
      public com.salmonllc.html.HtmlText _edDataStartCAP;
      public com.salmonllc.html.HtmlText _facultySearchLabel;
      public com.salmonllc.html.HtmlText _hoursCap;
      public com.salmonllc.html.HtmlText _hours;

      public com.salmonllc.html.HtmlText _independenthoursCap;
      public com.salmonllc.html.HtmlText _audiencehoursCap;
      public com.salmonllc.html.HtmlText _hoursCount;
      public com.salmonllc.html.HtmlText _hoursCountCap;
      public com.salmonllc.html.HtmlText _number;
      public com.salmonllc.html.HtmlText _semesterCap;
      public com.salmonllc.html.HtmlText _specialitySearchLabel;
      public com.salmonllc.html.HtmlText _teDesc1;
      public com.salmonllc.html.HtmlText _teDesc2;
      public com.salmonllc.html.HtmlText _text2;
      public com.salmonllc.html.HtmlText _yearCap;
      public com.salmonllc.html.HtmlTextEdit _edPlanId;
      public com.salmonllc.html.HtmlTextEdit _audiencehours;
      public com.salmonllc.html.HtmlTextEdit _independenthours;
      public JspDataTable _datatableDisciplines;
      public JspDetailFormDisplayBox _detailformdisplaybox1;
      public JspDisplayBox _educationPlanDisciplinesDisplaybox;
      public JspDisplayBox _summary;
      public JspForm _pageForm;
      public JspLink _homelink;
      public JspLink _logoutlink;
      public JspTable _educationPlanTable;
      public JspTable _facultySearch;
      public JspTable _tabBody;
      public JspTable _tabMenu;
      public JspTableCell _datatableDisciplinesTDHeader0;
      public JspTableCell _datatableDisciplinesTDHeader1;
      public JspTableCell _datatableDisciplinesTDHeader2;
      public JspTableCell _datatableDisciplinesTDHeader3;
      public JspTableCell _datatableDisciplinesTDHeader4;
      public JspTableCell _datatableDisciplinesTDHeader5;
      public JspTableCell _datatableDisciplinesTDHeader6;
      public JspTableCell _datatableDisciplinesTDHeader7;
      public JspTableCell _datatableDisciplinesTDRow0;
      public JspTableCell _datatableDisciplinesTDRow1;
      public JspTableCell _datatableDisciplinesTDRow2;
      public JspTableCell _datatableDisciplinesTDRow3;
      public JspTableCell _datatableDisciplinesTDRow4;
      public JspTableCell _datatableDisciplinesTDRow5;
      public JspTableCell _datatableDisciplinesTDRow6;
      public JspTableCell _datatableDisciplinesTDRow7;
      public JspTableCell _educationPlanTableTDRow0;
      public JspTableCell _educationPlanTableTDRow1;
      public JspTableCell _educationPlanTableTDRow2;
      public JspTableCell _educationPlanTableTDRow3;
      public JspTableCell _facultySearchTDRow0;
      public JspTableCell _facultySearchTDRow1;
      public JspTableCell _facultySearchTDRow2;
      public JspTableCell _facultySearchTDRow3;
      public JspTableCell _tabBodyTDRow0;
      public JspTableCell _tabBodyTDRow1;
      public JspTableCell _tabMenuTDRow0;
      public JspTableCell _tabMenuTDRow1;
      public JspTableCell _tabMenuTDRow2;
      public JspTableCell _tabMenuTDRow3;
      public JspTableRow _datatableDisciplinesTRHeader0;
      public JspTableRow _datatableDisciplinesTRRow0;
      public JspTableRow _educationPlanTableTRRow0;
      public JspTableRow _facultySearchTRRow0;
      public JspTableRow _facultySearchTRRow1;
      public JspTableRow _tabBodyTRRow0;
      public JspTableRow _tabBodyTRRow1;

//DataSources
      public com.itm.vuz.edplans.models.EdPlanDisciplinesQBEBuilder _edPlanDisciplinesQBE;
      public com.itm.vuz.edplans.models.HibernateDisciplinesModel _disciplinesModel;
      public com.itm.vuz.edplans.models.HibernateEducationPlansModel _educationPlanModel;
      public com.itm.vuz.examlist.models.HibernateFacultyModel _facultyModel;

    private String DELETE_FLAG = "DELETE_FLAG";




    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */

    public void initialize() {
        super.initialize();
        addPageListener(this);
        _sbtEducPlanEdit.addSubmitListener(this);
        _sbtEducPlan.addSubmitListener(this);
        retrieveSearchData();
        try{
        _hours.setExpression(_disciplinesModel, new hoursDSExpression());
        }catch (Exception e) {
            MessageLog.writeErrorMessage(e, this);
        }
        int count = _detailformdisplaybox1.getDataStore().getRowCount();
       if (count > 0) {
            try {
                EducationPlan plan = (EducationPlan) _detailformdisplaybox1.getDataStore().getDataStoreRow(0, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();
                _facultySearchSelect.setValue(plan.getSpeciality().getFaculty().getFacultyId().toString());
                _specialitySearchSelect.setValue(plan.getSpeciality().getSpecialityId().toString());
                _edPlanId.setValue(plan.getEducationPlanId().toString());
                //_edPlanDisciplinesSearch.doSearch();
            } catch (Exception e) {
                MessageLog.writeErrorMessage(e, this);
            }
        }

        //add a bucket to the model to track the delete check box
        _disciplinesModel.addBucket(DELETE_FLAG, DataStore.DATATYPE_INT);
        _deleteDiscipline.setColumn(_disciplinesModel, DELETE_FLAG);
        _deleteDiscipline.setFalseValue(null);

        _add.addSubmitListener(this);
        _save.addSubmitListener(this);

        _detailformdisplaybox1.getAddButton().addSubmitListener(this);

        _detailformdisplaybox1.getDeleteButton().insertSubmitListener(this);
        _detailformdisplaybox1.setOkToDeleteQuestion("Подтвердить удаление?");

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int startYear = currentYear-HISTORY_YEARS;
        for(int i=0; i<HISTORY_YEARS+ FUTURE_YEARS;i++){
            _year.addOption("" + (startYear + i), "" + (startYear + i));
        }
        _year.setSelectedIndex(HISTORY_YEARS);

        try{
            _number.setExpression(_disciplinesModel, new IncrementDSExpression(1));
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
            _number.setText("");
        }

    }

    /**
     * Process the given submit event
     *
     * @param e the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */

    private void changeView(com.salmonllc.html.HtmlSubmitButton changeBtn){
        this._tabMenuTDRow1.setStyle("MenuCell");
        this._tabMenuTDRow2.setStyle("MenuCell");

        if (changeBtn == this._sbtEducPlan) {
            this._tabMenuTDRow1.setStyle("SelectedMenuCell");
            _detailformdisplaybox1.setHeadingCaption("Просмотр учебного плана");
        }
        if (changeBtn == this._sbtEducPlanEdit) {
            this._tabMenuTDRow2.setStyle("SelectedMenuCell");
            _detailformdisplaybox1.setHeadingCaption("Редактирование учебного плана");
        }
    }


    public boolean submitPerformed(SubmitEvent e) throws Exception {
        if ((e.getComponent() == this._sbtEducPlanEdit) ||
               (e.getComponent() == this._sbtEducPlan)) {
               this.changeView((com.salmonllc.html.HtmlSubmitButton)e.getComponent()); }

        if (e.getComponent() == _save) {
            //remove deleted items and new rows that weren't modified
            int count = _disciplinesModel.getRowCount() - 1;
            for (int i = count; i >= 0; i--) {
                if (_disciplinesModel.getRowStatus() == DataStore.STATUS_NEW || _disciplinesModel.getInt(i, DELETE_FLAG) == 1)

                    _disciplinesModel.deleteRow(i);
            }

            //update the datastore
            _disciplinesModel.update();

            //scroll back to the first page in the table
            _datatableDisciplines.setPage(0);
        } else if (e.getComponent() == _add) {
            //add a new row, scroll to it and set focus to the first field
            int row = _disciplinesModel.insertRow();

            int count = _detailformdisplaybox1.getDataStore().getRowCount();
            if (count > 0) {
                try {
                    EducationPlan plan = (EducationPlan) _detailformdisplaybox1.getDataStore().getDataStoreRow(0, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();
                    ((Discipline) _disciplinesModel.getDataRow(row).getBean()).setEducationPlan(plan);
                } catch (Exception ex) {
                    MessageLog.writeErrorMessage(ex, this);
                }
            }
            _datatableDisciplines.setPage(_datatableDisciplines.getPage(row));
            //_firstName.setFocus(row);
            _year.setSelectedIndex(HISTORY_YEARS);
        } else if (e.getComponent() == _detailformdisplaybox1.getAddButton()) {
            reloadEducationPlan();
        } else

        //is delete button submitted?
            if (e.getSource() == _detailformdisplaybox1.getDeleteButton()) {
                try {
                    EducationPlan plan = (EducationPlan) _detailformdisplaybox1.getDataStore().getDataStoreRow(0, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();

                    MessageLog.writeDebugMessage("trying to delete education plan with id = [" +
                            plan.getEducationPlanId() + "]", this);
                    if (plan.getDisciplines() != null && plan.getDisciplines().size() > 0) {
                        try {
                            _detailformdisplaybox1.getValidator().addErrorMessage("Невозможно удалить учебный план. Есть связанные дисциплины, сперва удалите их.");
                            _detailformdisplaybox1.undoChanges();
                        } catch (Exception e2) {
                            MessageLog.writeErrorMessage(e2, this);
                        }
                        return false;
                    }
                } catch (DataStoreException e1) {
                    MessageLog.writeErrorMessage(e1, this);
                }
            }


        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);

        if (login != null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)) {
            _tabMenu.setVisible(true);
            if(_tabMenuTDRow2.getStyle().equals("SelectedMenuCell")){
                _add.setVisible(true);
                _save.setVisible(true);
                _detailformdisplaybox1.getAddButton().setVisible(true);
                _detailformdisplaybox1.getSaveButton().setVisible(true);
                _detailformdisplaybox1.getDeleteButton().setVisible(true);
                _detailformdisplaybox1.getCancelButton().setVisible(true);
                _detailformdisplaybox1.setEnabled(true);
                _educationPlanDisciplinesDisplaybox.setEnabled(true);
            }else {
                _add.setVisible(false);
                _save.setVisible(false);
                _detailformdisplaybox1.getAddButton().setVisible(false);
                _detailformdisplaybox1.getSaveButton().setVisible(false);
                _detailformdisplaybox1.getDeleteButton().setVisible(false);
                _detailformdisplaybox1.getCancelButton().setVisible(false);
                _detailformdisplaybox1.setEnabled(false);
                _educationPlanDisciplinesDisplaybox.setEnabled(false);
            }
        } else {
            _tabMenu.setVisible(false);
            _detailformdisplaybox1.setHeadingCaption("Просмотр учебного плана");
            _add.setVisible(false);
            _save.setVisible(false);
            _detailformdisplaybox1.getAddButton().setVisible(false);
            _detailformdisplaybox1.getSaveButton().setVisible(false);
            _detailformdisplaybox1.getDeleteButton().setVisible(false);
            _detailformdisplaybox1.getCancelButton().setVisible(false);
            _detailformdisplaybox1.setEnabled(false);
            _educationPlanDisciplinesDisplaybox.setEnabled(false);
        }

        try {
            retrieveSearchData();
            if (!isReferredByCurrentPage()) {
                reloadEducationPlan();
                boolean isAdminRole=(login != null && login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat);
                if((isAdminRole && _tabMenuTDRow1.getStyle().equals("SelectedMenuCell")) || !isAdminRole){
                _add.setVisible(false);
                _save.setVisible(false);
                } else{
                 _add.setVisible(true);
                 _save.setVisible(true);
                }
                //_edPlanDisciplinesSearch.doSearch();
            }
            reloadSummary();
        } catch (Throwable e) {
            MessageLog.writeErrorMessage(e, this);
        }
    }

    private void reloadSummary() {
        if(_disciplinesModel!=null){
            int count = _disciplinesModel.getRowCount();
            _disciplinesCount.setText("" + count);
            int totalHours = 0;
            if(count>0){
                try{
                    for(int i=0;i<count;i++){
                        Discipline discipline = (Discipline) _disciplinesModel.getDataStoreRow(i, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();
                        if(discipline!=null){
                            totalHours+=discipline.getHours();
                        }
                    }
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
            }
            _hoursCount.setText("" + totalHours);
        }
    }

    private void reloadEducationPlan() {
        int count = _detailformdisplaybox1.getDataStore().getRowCount();
        if (count > 0) {
            try {
                EducationPlan plan = (EducationPlan) _detailformdisplaybox1.getDataStore().getDataStoreRow(0, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();
                if (plan != null && plan.getEducationPlanId() != null) {
                    _edPlanId.setValue(plan.getEducationPlanId().toString());
                    _edPlanDisciplinesQBE.retrieve(_disciplinesModel);
                    _educationPlanDisciplinesDisplaybox.setVisible(true);
                } else {
                    _educationPlanDisciplinesDisplaybox.setVisible(false);
                }
            } catch (Exception e) {
                MessageLog.writeErrorMessage(e, this);
            }
        }
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) {
    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

    private void retrieveSearchData() {
        try {
            FacultyHelper helper = new FacultyHelper();
            Faculty faculty = helper.initializeFacultySearchSelect(_facultySearchSelect, _facultyModel);
            Speciality speciality = helper.initializeSpecialitySearchSelect(faculty, _specialitySearchSelect);

        } catch (Throwable e) {
            MessageLog.writeErrorMessage("Error in retrieveData", e, this);
        }
    }}
