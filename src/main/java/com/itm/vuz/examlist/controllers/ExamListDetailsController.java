package com.itm.vuz.examlist.controllers;

import com.salmonllc.jsp.JspController;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlText;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStore;
import com.salmonllc.util.MessageLog;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.itm.vuz.examlist.models.*;
import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.CalendarComponent;
import com.itm.vuz.common.models.JasperBeanDataSource;
import com.itm.vuz.groupcard.GroupCard;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

import org.hibernate.Session;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.ServletException;

/**
 * User: Alexei
 * Date: 29.05.2006
 * Time: 7:21:36
 */
public class ExamListDetailsController extends BaseVUZController
        implements SubmitListener, PageListener {

    //Visual Components
    public com.salmonllc.html.HtmlHiddenField _disciplineIdHiddenField;
    public com.salmonllc.html.HtmlHiddenField _baseDisciplineIdHiddenField;
    public com.salmonllc.html.HtmlHiddenField _educationPlanIdHiddenField;
    public com.salmonllc.html.HtmlHiddenField _examListIdHiddenField;
    public com.salmonllc.html.HtmlHiddenField _studentGroupIdHiddenField;
    public com.salmonllc.html.HtmlHiddenField _actionType;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlSubmitButton _save;
    public com.salmonllc.html.HtmlSubmitButton _reportButton;
    public com.salmonllc.html.HtmlButton _delete;

    public HtmlText _bookNumber;
    public HtmlText _bookNumberCap;
    public HtmlText _checkingFormLabel;
    public HtmlText _checkingFormLabelValue;
    public HtmlText _courseLabel;
    public HtmlText _courseLabelValue;
    public HtmlText _dateCreationLabel;
    public HtmlText _disciplineLabel;
    public HtmlText _disciplineLabelValue;
    public HtmlText _fioLabel;
    public HtmlText _fioLabelValue;
    public HtmlText _mark;
    public HtmlText _markCap;
    public HtmlText _numberLabel;
    public HtmlText _semestrLabel;
    public HtmlText _semestrLabelValue;
    public HtmlText _specialityLabel;
    public HtmlText _specialityLabelValue;
    public HtmlText _studentFIO;
    public HtmlText _studentFIOCap;
    public HtmlText _studentGroup;
    public HtmlText _studentGroupCap;
    public HtmlText _studentGroupLabel;
    public HtmlText _studentGroupLabelValue;
    public com.salmonllc.html.HtmlCheckBox _deleteCheckBox;
    //public com.salmonllc.html.HtmlText _studentNumber;
    public HtmlText _studentNumberCap;
    public HtmlText _underlineCheckingFormLabel;
    public HtmlText _yearLabel;
    public HtmlText _yearLabelValue;

    public CalendarComponent _dateCreationTextEdit;

    public com.salmonllc.html.HtmlTextEdit _numberTextEdit;
    public com.salmonllc.html.HtmlDropDownList _markTextEdit;
    public HtmlText _typeExamLabel;
    public com.salmonllc.html.HtmlDropDownList _typeExamSelect;

    public com.salmonllc.jsp.JspBox _examListBox;
    public com.salmonllc.jsp.JspBox _headerBox;
    public com.salmonllc.jsp.JspDataTable _datatableExamList;
    public com.salmonllc.jsp.JspForm _pageForm;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDHeader0;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDHeader1;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDHeader2;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDHeader3;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDHeader4;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDRow0;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDRow1;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDRow2;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDRow3;
    public com.salmonllc.jsp.JspTableCell _datatableExamListTDRow4;
    public com.salmonllc.jsp.JspTableRow _datatableExamListTRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatableExamListTRRow0;

//DataSources
    //public com.itm.vuz.examlist.models.HibernateExamListModel _examListModel;
    public HibernateExamMarkModel _examMarkModel;
    public HibernateStudentModel _studentModel;
    public HibernateDisciplineModel _disciplineModel;
    public HibernateStudentGroupModel _studentGroupModel;

    private String DELETE_FLAG = "DELETE_FLAG";

    String isAdditional;
    public com.salmonllc.html.HtmlButton _editStudentList;
    public com.salmonllc.html.HtmlSubmitButton _insertStudentList;
    public com.salmonllc.html.HtmlSubmitButton _exit;

    public static final String FIRST_SEMESTR = "первый";
    public static final String SECOND_SEMESTR = "второй";

    // report parameters
    String pReportTitle;
    String pExamListNumber;
    String pCheckType;
    String pFaculty;
    String pSubfaculty;
    String pDate;

    Map reportParameters = new HashMap();
    ExamListJasperBeanDataSource reportDataSource;
    String reportName;

    public HtmlText _signCap;
    public HtmlText _sign;



    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
        addPageListener(this);
        _numberLabel.setText("Зачетно-экзаменационная ведомость №");
        _semestrLabel.setText("Семестр");
        _yearLabel.setText("учебного года");
        _checkingFormLabel.setText("Форма контроля");
        //_underlineCheckingFormLabel.setText("(подчеркнуть)");
        _underlineCheckingFormLabel.setVisible(false);
        _specialityLabel.setText("Специальность");
        _studentGroupLabel.setText("Группа");
        _courseLabel.setText("Курс");
        _disciplineLabel.setText("Дисциплина");
        _fioLabel.setText("Фамилия, имя отчество преподавателей");
        _fioLabelValue.setText("");
        _dateCreationLabel.setText("Дата проведения зачета, экзамена");
        _studentNumberCap.setText("№№");
        _studentFIOCap.setText("Фамилия и инициалы студента");
        _bookNumberCap.setText("№ зач. книжки");
        _markCap.setText("Оценка");
        _signCap.setText("Прописью");
        _save.setDisplayName("Сохранить");
        _delete.setDisplayName("Удалить из ведомости");
        _checkingFormLabelValue.setText("Зачет/Экзамен");

        _typeExamLabel.setText("Тип ведомости");
        _typeExamSelect.addOption("0", "Основная ведомость");
        _typeExamSelect.addOption("1", "Контрольный лист");
        _typeExamSelect.addOption("2", "Дополнительная ведомость");

        _reportButton.setDisplayName("Отчет");

        _save.addSubmitListener(this);
        _reportButton.addSubmitListener(this);

        _examMarkModel.addBucket(DELETE_FLAG, DataStore.DATATYPE_INT);
        _deleteCheckBox.setColumn(_examMarkModel, DELETE_FLAG);
        _deleteCheckBox.setFalseValue(null);

        _editStudentList.setDisplayName("Изменить список студентов");
        _insertStudentList.setDisplayName("Добавить студентов из списка ");

        _insertStudentList.addSubmitListener(this);

        _exit.setDisplayName("Выход");
        _exit.addSubmitListener(this);

        _studentGroupCap.setText("Группа");
        reportParameters.put("ReportTitle", "Экзаменационная ведомость");
        try{
            _sign.setExpression(_examMarkModel, new SignExpression());
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
            _sign.setText("");
        }


    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        if (event.getComponent() == _save) {
            return savePerformed();
        } else if (event.getComponent() == _delete) {
            return deletePerformed();
        } else if (event.getComponent() == _reportButton) {
            try {
                printReport();
            } catch (Throwable e) {
                MessageLog.writeErrorMessage("Error submitPerformed", e, this);
            }
        } else if (event.getComponent() == _insertStudentList) {
            try {
                _studentModel.reset();
                _studentModel.appendRows(getSessionManager().getStudentBasket().getStudents().getRows());
                initializeExamMarkModel();
                setupPropertiesOnPage();

            } catch (Exception e) {
                MessageLog.writeErrorMessage("unable to retrieve students from student basket", e, this);
            }
        } else if(event.getComponent() == _exit){
            try{
                gotoSiteMapPage("ExamList"
                        , "?isAdditional=" + isAdditional);
            }catch(Exception e){
                MessageLog.writeErrorMessage(e, this);
            }
        }
        return true;
    }

    private void printReport() throws IOException, JRException, ServletException, URISyntaxException {

        // fill
        //Preparing parameters

        URI uri = new URI(this.getClass().getClassLoader().getResource(reportName + ".jrxml").toString());

        MessageLog.writeDebugMessage("uri.getPath() = [" + uri.getPath() + "]", this);
        File examListDesignFile = new File(uri.getPath());

        File examListJasperFile = null;
        File examListPrintFile = null;
        try{
            examListJasperFile = new File(reportName + ".jasper");
            examListPrintFile = new File(reportName + ".jrprint");
            JasperCompileManager.compileReportToFile(examListDesignFile.getAbsolutePath(), examListJasperFile.getAbsolutePath());

            reportDataSource.reset();

            JasperFillManager.fillReportToFile(examListJasperFile.getAbsolutePath(), reportParameters,
                    reportDataSource);

            //JasperPrintManager.printReport(examListJasperFile.getAbsolutePath(), true);

            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(examListPrintFile);

            File tmpDestFile = File.createTempFile(reportName, "xls");
            tmpDestFile.deleteOnExit();

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, tmpDestFile.toString());
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

            exporter.exportReport();

            gotoSiteMapPage("JasperReportDownload", "?reportFileName=" + tmpDestFile.getAbsolutePath());
        }finally{
            if(examListJasperFile!=null && examListJasperFile.canRead()){
                examListJasperFile.delete();
            }
            if(examListPrintFile!=null && examListPrintFile.canRead()){
                examListPrintFile.delete();
            }
        }

    }

    private boolean deletePerformed() {
        try {
            int count = _examMarkModel.getRowCount() - 1;
            for (int i = count; i >= 0; i--) {
                if (_examMarkModel.getRowStatus() == DataStore.STATUS_NEW
                        || _examMarkModel.getInt(i, DELETE_FLAG) == 1) {
                    ExamMark examMark = (ExamMark) _examMarkModel.getDataRow(i).getBean();
                    if (examMark != null) {
                        if (examMark.getExamMarkId() != null) {
                            _examMarkModel.deleteRow(i);
                        } else {
                            _examMarkModel.removeRow(i);
                        }
                    }
                }
            }
            _examMarkModel.update(true);
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in deletePerformed", e, this);
        }
        return false;
    }

    private boolean savePerformed() {
        try {
            if (_examMarkModel.getRowCount() == 0) {
                return true;
            }
            Session session = HibernateSessionFactory.getSession();

            ExamMark examMark = (ExamMark) _examMarkModel.getDataRow(0).getBean();
            ExamList examList = examMark.getExamList();
            examList.setNumber(_numberTextEdit.getValue());
            examList.setDateCreation(_dateCreationTextEdit.getFormat().parse(_dateCreationTextEdit.getValue()));
            examList.setType(new Byte(_typeExamSelect.getValue()));
            session.saveOrUpdate(examList);

            if (_examListIdHiddenField.getValue() == null) {
                for (int i = 0; i < _examMarkModel.getRowCount(); i++) {
                    examMark = (ExamMark) _examMarkModel.getDataRow(i).getBean();
                    if (examMark != null) {
                        examMark.setExamList(examList);
                        session.saveOrUpdate(examMark);
                    }
                }
            }
            _examMarkModel.update(true);
            gotoSiteMapPage("ExamList"
                    , "?isAdditional=" + isAdditional);
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error submitPerformed", e, this);
        }
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception{
        super.pageRequested(event);
        if(login!=null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)){
            _save.setEnabled(true);
            _delete.setEnabled(true);
            _editStudentList.setEnabled(true);
            _insertStudentList.setEnabled(true);
            _datatableExamList.setEnabled(true);
            _headerBox.setEnabled(true);
        }else{
            _save.setEnabled(false);
            _delete.setEnabled(false);
            _editStudentList.setEnabled(false);
            _insertStudentList.setEnabled(false);
            _datatableExamList.setEnabled(false);
            _headerBox.setEnabled(false);
        }

        //_disciplineIdHiddenField.setValue(getParameter("disciplineId"));
        _studentGroupIdHiddenField.setValue(getParameter("studentGroupId"));
        _examListIdHiddenField.setValue(getParameter("examListId"));
        _educationPlanIdHiddenField.setValue(getParameter("educationPlanId"));
        _baseDisciplineIdHiddenField.setValue(getParameter("baseDisciplineId"));

        isAdditional = getParameter("isAdditional");
        _typeExamSelect.resetOptions();
        _typeExamSelect.addOption("0", "Основная ведомость");
        _typeExamSelect.addOption("1", "Контрольный лист");
        _typeExamSelect.addOption("2", "Дополнительная ведомость");

        if (_actionType.getValue().equals("delete")) {
            deletePerformed();
        } else {
            try {
                initializeExamMarkModel();
                setupPropertiesOnPage();
            } catch (DataStoreException e) {
                MessageLog.writeErrorMessage("Error in forwarding ExamListDetails", e, this);
            }
        }
    }

    private void setupPropertiesOnPage() {
        Discipline discipline = null;
        if (_examMarkModel.getRowCount() > 0) {
            ExamMark examMark = (ExamMark) _examMarkModel.getDataRow(0).getBean();
            discipline = examMark.getDiscipline();
            if (examMark != null) {
                boolean isAdditionalType = (isAdditional!=null && isAdditional.equals("true")) ? true :
                        (examMark.getExamList().getType().intValue() == 2);
                isAdditional = (isAdditionalType) ? "true" : "false";

                if(examMark.getExamList().getNumber() == null){
                    _numberTextEdit.setValue(_numberTextEdit.getValue());
                }else{
                    _numberTextEdit.setValue(examMark.getExamList().getNumber());
                }

                _semestrLabelValue.setText(String.valueOf(!examMark.getDiscipline().isSemester() ? FIRST_SEMESTR : SECOND_SEMESTR));
                _yearLabelValue.setText(String.valueOf(examMark.getDiscipline().getYear()));
                _checkingFormLabelValue.setText(String.valueOf(
                        examMark.getDiscipline().getCheckingForm() == GroupCard.TEST ? GroupCard.STR_TEST : GroupCard.STR_EXAM));
                setExamSigns(examMark.getDiscipline().getCheckingForm());
                _specialityLabelValue.setText(examMark.getDiscipline().getEducationPlan().getSpeciality().getShortname());

                if(!isAdditionalType){
                    _studentGroupLabel.setVisible(true);
                    _studentGroupLabelValue.setVisible(true);
                    _studentGroupLabelValue.setText(examMark.getStudentGroup().getNumber());
                }else{
                    _studentGroupLabel.setVisible(false);
                    _studentGroupLabelValue.setVisible(false);
                }
                _courseLabelValue.setText(String.valueOf(examMark.getDiscipline().getCourse()));
                _disciplineLabelValue.setText(examMark.getDiscipline().getBaseDiscipline().getName());
                if (examMark.getExamList().getDateCreation() != null) {
                    _dateCreationTextEdit.setValue(_dateCreationTextEdit.getFormat().format(examMark.getExamList().getDateCreation()));
                } else {
                    _dateCreationTextEdit.setValue(_dateCreationTextEdit.getValue());
                }
                setControlsByType(isAdditionalType);
                int value = examMark.getExamList().getType().intValue();
                _typeExamSelect.setSelectedIndex(value);
            }

        } else {
            try {
                discipline = getDiscipline();
                if (discipline!=null) {
                    setControlsByType(isAdditional.equals("true"));
                    _semestrLabelValue.setText(String.valueOf(!discipline.isSemester() ? FIRST_SEMESTR : SECOND_SEMESTR));
                    _yearLabelValue.setText(String.valueOf(discipline.getYear()));
                    _checkingFormLabelValue.setText(String.valueOf(
                            discipline.getCheckingForm() == GroupCard.TEST ? GroupCard.STR_TEST : GroupCard.STR_EXAM));
                    setExamSigns(discipline.getCheckingForm());
                    Speciality speciality = discipline.getEducationPlan().getSpeciality();
                    _specialityLabelValue.setText(speciality.getShortname());

                    if(isAdditional.equals("true")){
                        _studentGroupLabel.setVisible(false);
                        _studentGroupLabelValue.setVisible(false);
                    }else{
                        _studentGroupModel.retrieve("StudentGroup.studentGroupId=" + _studentGroupIdHiddenField.getValue());
                        if(_studentGroupModel.getRowCount()>0){
                            StudentGroup _sg = (StudentGroup)_studentGroupModel.getDataRow(0).getBean();
                            _studentGroupLabelValue.setText(_sg.getNumber());
                            _studentGroupLabel.setVisible(true);
                            _studentGroupLabelValue.setVisible(true);
                        }
                    }
                    _courseLabelValue.setText(String.valueOf(discipline.getCourse()));
                    _disciplineLabelValue.setText(discipline.getBaseDiscipline().getName());
                }
            } catch (DataStoreException e) {
                MessageLog.writeErrorMessage(e, this);
            }
        }
        try{
            Faculty faculty = discipline.getEducationPlan().getSpeciality().getFaculty();
            reportParameters.put("ExamListNumber", _numberTextEdit.getValue());
            reportParameters.put("Faculty", faculty.getName());
            reportParameters.put("CheckForm", discipline.getCheckingForm() == GroupCard.TEST ? GroupCard.STR_TEST : GroupCard.STR_EXAM);
            reportParameters.put("Subfaculty", discipline.getBaseDiscipline().getSubfaculty().getName());
            reportParameters.put("Date", _dateCreationTextEdit.getValue());
            reportParameters.put("Semester", _semestrLabelValue.getText());
            reportParameters.put("Speciality", _specialityLabelValue.getText());
            reportParameters.put("GroupNumber", _studentGroupLabelValue.getText());
            reportParameters.put("Course", _courseLabelValue.getText());
            reportParameters.put("Year", _yearLabelValue.getText());
            reportDataSource = new ExamListJasperBeanDataSource(_examMarkModel);
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }

    }

    private void setExamSigns(byte checkingForm) {
        _markTextEdit.resetOptions();
        ExamMarksHelper helper = new ExamMarksHelper();
        if(checkingForm == GroupCard.EXAM){
            List examMarks = helper.getExamKeys();
            for(int i=0; i<examMarks.size();i++){
                if(i==0){
                    _markTextEdit.addOption("" + i, (String)examMarks.get(i));
                }else{
                    _markTextEdit.addOption("" + (i+1), (String)examMarks.get(i));
                }

            }
        }
        else{
            List testMarks = helper.getTestKeys();
            for(int i=0; i<testMarks.size();i++){
                _markTextEdit.addOption("" + i, (String)testMarks.get(i));
            }
        }
    }

    private void initializeExamMarkModel() throws DataStoreException {
        if (_examListIdHiddenField.getValue() != null) {
            _examMarkModel.retrieve(getExamMarkCriteria());
        } else {
            _examMarkModel.reset();
            ExamList examList = new ExamList();
            examList.setDateCreation(new Date());
            examList.setType(new Byte("0"));
            Discipline discipline = getDiscipline();
            if (discipline != null) {
                if (!isAdditional.equals("true")) {
                    _studentModel.retrieve("Student.studentGroup.studentGroupId="
                            + _studentGroupIdHiddenField.getValue());
                } else {
//?
                }
                for (int i = 0; i < _studentModel.getRowCount(); i++) {
                    Student student = (Student) _studentModel.getDataRow(i).getBean();
                    if (student != null) {
                        ExamMark examMark = new ExamMark();
                        examMark.setStudent(student);
                        examMark.setDiscipline(discipline);
                        examMark.setExamList(examList);
                        examMark.setStudentGroup(student.getStudentGroup());
                        examMark.setValue(new Byte("0"));

                        _examMarkModel.insertRow(examMark);
                    }
                }
            }
        }
    }

    private Discipline getDiscipline() throws DataStoreException {
        _disciplineModel.retrieve(HibernateDisciplineModel.BASEDISCIPLINE_BASE_DISCIPLINE_ID
                + "=" + _baseDisciplineIdHiddenField.getValue()
                + " AND "
                + HibernateDisciplineModel.EDUCATIONPLAN_EDUCATION_PLAN_ID
                + "=" + _educationPlanIdHiddenField.getValue());
        if (_disciplineModel.getRowCount() > 0) {
            return (Discipline) _disciplineModel.getDataRow(0).getBean();
        } else {
            return null;
        }
    }

    private String getExamMarkCriteria() {
        StringBuffer query = new StringBuffer();
        query.append(HibernateExamMarkModel.EXAMLIST_EXAM_LIST_ID);
        query.append("=");
        query.append(_examListIdHiddenField.getValue());
        return query.toString();
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

    private void setControlsByType(boolean isAdditionalType){
        if (isAdditionalType) {
            _editStudentList.setVisible(true);
            _insertStudentList.setVisible(true);
            _typeExamSelect.removeOption(0);
            _typeExamSelect.removeOption(0);
            _datatableExamListTDHeader1.setVisible(true);
            _datatableExamListTDRow1.setVisible(true);
            reportName = "examListAdd";

        } else {
            _editStudentList.setVisible(false);
            _insertStudentList.setVisible(false);
            _typeExamSelect.removeOption(2);
            _datatableExamListTDHeader1.setVisible(false);
            _datatableExamListTDRow1.setVisible(false);
            reportName = "examList";
        }

    }
}
