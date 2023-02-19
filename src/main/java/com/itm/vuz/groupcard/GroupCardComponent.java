/*
 * $Id$
 * Created on 17.05.2006 10:00:45
 * Last modification $Date$
 */

package com.itm.vuz.groupcard;

import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.models.JasperBeanDataSource;
import com.itm.vuz.common.models.IncrementDSExpression;
import com.itm.vuz.common.reports.Reportable;
import com.itm.vuz.common.Application;
import com.itm.vuz.common.CalendarComponent;
import com.itm.vuz.groupcard.models.*;
import com.salmonllc.html.*;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.jsp.JspController;
import com.salmonllc.sql.DataStore;
import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;
import com.salmonllc.properties.Props;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Group Card component
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardComponent extends HtmlContainer implements PageListener, SubmitListener{

    public static final String GROUP_CARD_DATA_STORE = "GroupCardDataStore";
    public static final String ENTER_GROUP = "Введите номер группы";

    private int defaultFiredStatus;
    private String examColor;
    private String testColor;
    private String TableHeadingStartTag="<font face=\"Arial\" SIZE=\"4\" COLOR=\"black\"><B>";
    private String EndBoldTag="</B></font>";
    private String EndTag="</font>";

    HtmlPage groupCardPage;

    GroupCardBeanModel dataStore;

    GroupCard card;


    GroupCardTable cardTable;

    HtmlSubmitButton groupCardSearchButton;
    HtmlSubmitButton groupCardUpdateButton;
    QBEBuilder groupQBE;
    QBEBuilder disciplineQBE;


    public static final int HISTORY_YEARS = 10;
    public static final int FUTURE_YEARS = 20;

    HtmlDropDownList edYear;
    HtmlTextEdit edGroup;
    HtmlLookUpComponent groupLookup;
    HtmlDropDownList edSemestr;


    JspController controller;

    boolean editNotesMode;

    HtmlSubmitButton groupCardReportButton;

    // boxes
    HtmlDisplayBox searchBox;
    HtmlDisplayBox tableBox;
    HtmlDisplayBox summaryBox;

    public HtmlValidatorText _validator;



    public GroupCardComponent(String name, String _semester, String _course, String _year, String _groupId,
                              String _search, String _editNotes, HtmlPage page)
    {
        super(name, page);

        defaultFiredStatus = Props.getProps(Application.APP_NAME, null).getIntProperty("DefaultFiredStatusId");
        examColor = Props.getProps(Application.APP_NAME, null).getProperty("ExamColor");
        testColor = Props.getProps(Application.APP_NAME, null).getProperty("TestColor");

        controller = (JspController)page;

        editNotesMode = (_editNotes!=null && _editNotes.equalsIgnoreCase("true")) ? true : false;

        cardTable = new GroupCardTable(name, dataStore, page);
        Calendar calendar = Calendar.getInstance();
        groupCardPage = page;
        page.addPageListener(this);
        groupQBE = new GroupCardStudentGroupQBEBuilder();
        disciplineQBE = new GroupCardDisiplineQBEBuilder();

        HtmlText t2 = new HtmlText("Год", groupCardPage);
        edYear = new HtmlDropDownList("EditYear",groupCardPage);
        int currentYear = calendar.get(Calendar.YEAR);
        int startYear = currentYear - HISTORY_YEARS;
        for(int i=startYear; i<=currentYear + FUTURE_YEARS;i++){
            edYear.addOption("" + i, "" + i);
        }
        edYear.setColumn(disciplineQBE, HibernateDisciplinesModel.YEAR);

        HtmlText t3 = new HtmlText("Группа", groupCardPage);
        edGroup = new HtmlTextEdit("EdiGroup",groupCardPage);
        edGroup.setColumn(groupQBE, HibernateGroupModel.GROUP_NUMBER);
        // or
        groupLookup = new HtmlLookUpComponent("groupLookup", "StudentGroupLookup.jsp", "../images/Browse.gif", groupCardPage);
        groupLookup.setColumn(groupQBE, HibernateGroupModel.GROUP_NUMBER);
        groupLookup.setUseDiv(true);
        groupLookup.setUsePopup(true, false);
        groupLookup.setPopupPosition("relative");
        groupLookup.setPopupWidth(520);
        groupLookup.setPopupHeight(460);
        groupCardPage.add(groupLookup);

        // sofia hotfix
        // add lookup component to parent page controller
        controller.assignComponentToField(HtmlLookUpComponent.PARAM_LOOKUP_COMPONENT + "_" + name, groupLookup, false);

        HtmlText t = new HtmlText("Семестр", groupCardPage);
        edSemestr = new HtmlDropDownList("EditSemestr",groupCardPage);
        edSemestr.addOption("0", "первый");
        edSemestr.addOption("1", "второй");
        edSemestr.setColumn(disciplineQBE, HibernateDisciplinesModel.SEMESTER);


        //final HtmlText t4 = new HtmlText("Показывать: ", groupCardPage);


        groupCardSearchButton =
                new HtmlSubmitButton("groupCardSearchButton", "Поиск", groupCardPage);
        groupCardSearchButton.addSubmitListener(this);

        groupCardUpdateButton =
                new HtmlSubmitButton("groupCardUpdateButton", "Сохранить", groupCardPage);
        groupCardUpdateButton.addSubmitListener(this);

        groupCardReportButton =
                new HtmlSubmitButton("groupCardReportButton", "Отчет", groupCardPage);
        groupCardReportButton.addSubmitListener(this);
        groupCardReportButton.setVisible(false);

        _validator = new HtmlValidatorText("validator", groupCardPage);

        HtmlTable _tab = new HtmlTable("Table",groupCardPage);
        int ind = 0;

        _tab.setComponentAt(ind,0,t2);
        _tab.setComponentAt(ind,1,edYear);

//        _tab.setComponentAt(1,0,t);
//        _tab.setComponentAt(1,1,edSemestr);
        _tab.setComponentAt(ind,2,t);
        _tab.setComponentAt(ind,3,edSemestr);


//        _tab.setComponentAt(2,0,t3);
        //or
//        _tab.setComponentAt(2,1,groupLookup);

        _tab.setComponentAt(ind,4,t3);
        //or
        _tab.setComponentAt(ind,5,groupLookup);

        _tab.setComponentAt(ind, 6, _validator);

        ind++;

        _tab.setComponentAt(ind, 0, groupCardSearchButton);
        if(editNotesMode){
            _tab.setComponentAt(ind, 1, groupCardUpdateButton);
            groupCardUpdateButton.setVisible(false);
        }

        edYear.setValue((_year == null || _year.trim().length() == 0) ? "" + currentYear : _year);
        edGroup.setValue(_groupId);
        edSemestr.setValue(_semester);

        // set components
        // wrap search with box
        searchBox = new HtmlDisplayBox("SearchBox", page);

        searchBox.setFontStartTag(TableHeadingStartTag);
        searchBox.setHeadingCaption("Выберите номер группы и год");
        searchBox.setFontEndTag(EndBoldTag);

        searchBox.setBoxComponent(_tab);
        this.insertComponentAt(searchBox, 0);


        tableBox = new HtmlDisplayBox("TableBox", page);
        this.insertComponentAt(tableBox, 1);

        summaryBox = new HtmlDisplayBox("SummaryBox", page);

        summaryBox.setFontStartTag(TableHeadingStartTag);
        summaryBox.setHeadingCaption("Итог");
        summaryBox.setFontEndTag(EndBoldTag);
        this.insertComponentAt(summaryBox, 2);

        this.insertComponentAt(groupCardReportButton, 3);

        // set not visible by default
        _tab.setVisible(false);
        tableBox.setVisible(false);
        summaryBox.setVisible(false);


        // if all attributes are set then do search
        MessageLog.writeDebugMessage(_semester + ":" + _course + ":" + _year + ":" + _groupId, this);
        if(_search!=null && !Boolean.valueOf(_search).booleanValue()){
            try{
                groupCardSearchButton.executeEvent(HtmlComponent.EVENT_SUBMIT);
            }catch(Exception e){
                MessageLog.writeErrorMessage(e, this);
            }
        }else{
            _tab.setVisible(true);
        }


    }
    protected void populateComponent() throws Exception{
        card = buildGroupCard(null);

        dataStore = new GroupCardBeanModel();

        cardTable.setDataStore(dataStore);

        // load data for group card
        createGroupCardTable(card);
        populateData(card, dataStore);
    }

    /**
     * sort group card students by session closed/not closed and fired state
     * @param studentMarks
     */
    private List sortGroupCardStudents(List studentMarks) {
        setOpenSessionStudentSize(0);
        setClosedSessionStudentSize(0);
        setFiredStudentSize(0);
        List sortedList = new ArrayList(studentMarks.size());
        GroupCardStudent localMin = null;
        while(studentMarks.size()>0){
            // session not closed
            GroupCardStudent _st = null;
            _st = getLocalMin(studentMarks, 0);
            if(_st!=null){
                setOpenSessionStudentSize(getOpenSessionStudentSize()+1);
                sortedList.add(_st);
                studentMarks.remove(_st);

            }else{
                // session closed
                _st = getLocalMin(studentMarks, 1);
                if(_st!=null){
                    setClosedSessionStudentSize(getClosedSessionStudentSize()+1);
                    sortedList.add(_st);
                    studentMarks.remove(_st);
                }
                else{
                    // fired student
                    setFiredStudentSize(getFiredStudentSize()+1);
                    _st = getLocalMin(studentMarks, 2);
                    sortedList.add(_st);
                    studentMarks.remove(_st);
                }
            }
        }
        return sortedList;
    }

    private GroupCardStudent getLocalMin(List students, int i) {
        Iterator all = students.iterator();
        while(all.hasNext()){
            GroupCardStudent s = (GroupCardStudent)all.next();
            if(s.getNotes()==null &&
                    (i==0 || i ==1) && s.getStudent().getStudentStatus().getStudentStatusId().intValue() != defaultFiredStatus){
              return s;
            }
            if(i == 0 && s.getNotes()!=null &&
                    s.getNotes().getSessionClosingDate()==null &&
                    (s.getStudent().getStudentStatus().getStudentStatusId() == null
                    || s.getStudent().getStudentStatus().getStudentStatusId().intValue() != defaultFiredStatus)){
                return s;
            }
            if(i == 1 && s.getNotes()!=null &&
                    s.getNotes().getSessionClosingDate()!=null &&
                    (s.getStudent().getStudentStatus().getStudentStatusId() == null
                    || s.getStudent().getStudentStatus().getStudentStatusId().intValue() != defaultFiredStatus)){
                return s;
            }
            if(i == 2){
                return s;
            }
        }
        return null;
    }

    public void pageRequested(PageEvent p) throws Exception {
        // request
        MessageLog.writeInfoMessage("Page requested", this);

    }

    private GroupCardStudent containsStudent(List studentMarks, Student student) {
        Iterator all = studentMarks.iterator();
        while(all.hasNext()){
            GroupCardStudent groupStudent = (GroupCardStudent)all.next();
            if(groupStudent.getStudent().equals(student)){
                return groupStudent;
            }
        }
        return null;
    }

    private void populateData(GroupCard card, GroupCardBeanModel _dataStore) {
        _dataStore.insertRows(card.getGroupCardStudents());
    }

    private void createGroupCardTable(GroupCard card) {

        // init table
        cardTable.resetHeadingComponents();
        cardTable.resetRowComponents();
        cardTable.setCellSpacing(1);
        cardTable.setLinkFont("sans-serif");

        // create headers
        StringBuffer sb = new StringBuffer();
        sb.append("Куратор: ");
        if(card.getGroup().getPersonal()!=null){
          sb.append(card.getGroup().getPersonal().getName() );
          sb.append(" ");
          sb.append(card.getGroup().getPersonal().getPatronymic());
          sb.append(" ");
          sb.append(card.getGroup().getPersonal().getFamilyName());
        }
        tableBox.setFontStartTag(TableHeadingStartTag);
        tableBox.setHeadingCaption(sb.toString());
        tableBox.setFontEndTag(EndBoldTag);
        tableBox.setBoxComponent(cardTable);

        //groupCardHeader.add(new HtmlText("" + person.getFamilyName() + " " + person.getName() + " " + person.getPatronymic(), groupCardPage));
        HtmlText groupNumber = new HtmlText("" + card.getGroup().getNumber(), groupCardPage);


        //TODO: font and size

        int ind=0;
        ind++;

        groupNumber.setFontStartTag("<font face=\"Arial\" size=\"2\" color=\"Black\"><B>");
        groupNumber.setFontEndTag(EndBoldTag);

        HtmlTableCellProperties cellProps = new HtmlTableCellProperties();
        cellProps.setBackgroundColor("#b6cbeb");
        cellProps.setAlign(HtmlTable.ALIGN_CENTER);
        groupNumber.setCenter(true);

        cardTable.setHeadingComponentAt(ind++,groupNumber,cellProps);

        Iterator disciplines = card.getDisciplines().iterator();

        HtmlStyle disciplineStyle = new HtmlStyle("disciplineStyle", "", "writing-mode: tb-rl; color:black;" +
                " text-decoration:underline; font-family:Arial;", getPage());

        getPage().add(disciplineStyle);
        controller.add(disciplineStyle);

        while(disciplines.hasNext())
        {
            Discipline discipline = (Discipline)disciplines.next();
            //HtmlText disciplineText = new HtmlText(discipline.getBaseDiscipline().getName(), groupCardPage);
            //HtmlTable disciplineHtml = new HtmlTable("discipline" + ind, groupCardPage);
            HtmlText disciplineHtml = new HtmlText(null, groupCardPage);
            StringBuffer disciplineText = new StringBuffer();
            //disciplineText.append(GroupCard.checkingFormToString(discipline.getCheckingForm()));
            //disciplineText.append("\n");
            disciplineText.append(discipline.getBaseDiscipline().getName());
            disciplineText.append("\n");
            disciplineText.append(" (" + String.valueOf(discipline.getAudiencehours() +  discipline.getIndependenthours()) + " ч)");

/*
            disciplineHtml.setComponentAt(0,0, new HtmlText(GroupCard.checkingFormToString(discipline.getCheckingForm()), groupCardPage));
            disciplineHtml.setComponentAt(1,0, new HtmlText(discipline.getBaseDiscipline().getName(), groupCardPage));
            disciplineHtml.setComponentAt(2,0, new HtmlText("" + discipline.getHours(), groupCardPage));
*/
            //cardTable.setHeadingComponentAt(ind, disciplineHtml);
            //cardTable.setColumnWidth(ind, 1);

            HtmlTableCellProperties cellProperties = new HtmlTableCellProperties();
            cellProperties.setCellHeight(10, HtmlDataTable.SIZE_PIXELS);
            cellProperties.setWrap(false);
            cellProperties.setVertAlign(HtmlTable.VALIGN_MIDDLE);
            cellProperties.setAlign(HtmlTable.ALIGN_CENTER);

            disciplineHtml.setStyle(disciplineStyle);
            disciplineHtml.setCenter(true);

            // set background color by checking form
            if(discipline.getCheckingForm() == 0){
                // test
                cellProperties.setBackgroundColor(testColor);
            }else{
                // exam
                cellProperties.setBackgroundColor(examColor);
            }

            //disciplineHtml.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"black\">");
            //disciplineHtml.setFontEndTag("</font>");

            disciplineHtml.setText(disciplineText.toString());
            cardTable.setHeadingComponentAt(ind, disciplineHtml, cellProperties);

            ind++;
        }

        cellProps.setWrap(false);
        cellProps.setCellHeight(10, HtmlDataTable.SIZE_PIXELS);
        cellProps.setVertAlign(HtmlTable.VALIGN_MIDDLE);
        cellProps.setAlign(HtmlTable.ALIGN_CENTER);

        /*headColumn.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"black\">");
        headColumn.setFontEndTag("</font>");*/

        HtmlText headColumn = new HtmlText(GroupCardColumnHeaders.FREE_ATTENDANCE, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        headColumn = new HtmlText(GroupCardColumnHeaders.REBUKE, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        headColumn = new HtmlText(GroupCardColumnHeaders.LAST_EXAM_DATE, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        headColumn =  new HtmlText(GroupCardColumnHeaders.SESSION_PROLONGATION, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        headColumn =  new HtmlText(GroupCardColumnHeaders.SESSION_CLOSING_DATE, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        headColumn =  new HtmlText(GroupCardColumnHeaders.STUDENT_GRANT, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        // TODO: add practice column for 1-4 courses only
        headColumn =  new HtmlText(GroupCardColumnHeaders.PRACTICE, groupCardPage);
        headColumn.setStyle(disciplineStyle);
        cardTable.setHeadingComponentAt(ind++, headColumn,cellProps);

        // rows
        HtmlLink studenLink = new HtmlLink("studentLink", "#", groupCardPage);
        HtmlText student = new HtmlText("Student goes here", groupCardPage);
        student.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"black\">");
        student.setFontEndTag(EndTag);
        studenLink.add(student);
        try{
            student.setExpression(dataStore, "Student.Student.Name +' '+Student.Student.FamilyName");
            //student.setExpression(dataStore, new GroupCardStudentDSExpression());
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
        ind=0;

        HtmlText studentNumber = new HtmlText("studentNumber", groupCardPage);
        studentNumber.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"black\">");
        studentNumber.setFontEndTag(EndTag);
        cardTable.setRowComponentAt(ind++, studentNumber);
        try{
            studentNumber.setExpression(dataStore, new IncrementDSExpression(1));
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
            studentNumber.setText("");
        }

        cardTable.setRowComponentAt(ind++, studenLink);

        disciplines = card.getDisciplines().iterator();
        while(disciplines.hasNext()){
            Discipline discipline = (Discipline)disciplines.next();
            Long disciplineId = discipline.getDisciplineId();
            HtmlLink disciplineExamLink = new HtmlLink("disciplineExamLink", "disciplineId=" + disciplineId, groupCardPage);
            HtmlText mark = new HtmlText("Mark goes here", groupCardPage);
            mark.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"black\">");
            mark.setFontEndTag(EndTag);
            disciplineExamLink.add(mark);
            try{

                /*mark.setExpression(dataStore, "Student.Marks.substring(Student.Marks.indexOf('" + disciplineName + "') + " +
                        nameLength + ", Student.Marks.indexOf('" + disciplineName + "') + " + (nameLength + 1) + ")");
                 */
                mark.setExpression(dataStore, new MarksExpression(disciplineId));
            }catch(Exception e){
                MessageLog.writeErrorMessage(e, this);
            }
            cardTable.setRowComponentAt(ind, disciplineExamLink);
            ind++;
        }


        HtmlComponent noteFreeAttendance = new HtmlCheckBox("Free attendance goes here", groupCardPage, "1", "0");
        HtmlComponent noteRebuke = new HtmlText("Rebuke goes here", groupCardPage);
        HtmlComponent noteLastExam = new HtmlText("Last Exam goes here", groupCardPage);
        HtmlComponent noteProlongation = new HtmlText("Prolongation goes here", groupCardPage);
        HtmlComponent noteCloseDate = new HtmlText("Close Date goes here", groupCardPage);
        HtmlComponent noteGrant = new HtmlText("Grant Date goes here", groupCardPage);
        HtmlComponent notePractice = new HtmlText("Practice Date goes here", groupCardPage);

        if(editNotesMode){
            // notes as edit fields
            noteFreeAttendance = new HtmlCheckBox("Free attendance goes here", groupCardPage, "0", "1");
            noteRebuke = new HtmlTextEdit("Rebuke goes here", groupCardPage);
            //noteLastExam = new HtmlTextEdit("Last Exam goes here", groupCardPage);
            noteLastExam = new CalendarComponent("LastExamDate", groupCardPage);
            ((CalendarComponent)noteLastExam).set_ds(dataStore);
            ((CalendarComponent)noteLastExam).setColumn("Student.Notes.LastExamDate");

            //noteProlongation = new HtmlTextEdit("Prolongation goes here", groupCardPage);
            noteProlongation = new CalendarComponent("Prolongation", groupCardPage);
            ((CalendarComponent)noteProlongation).set_ds(dataStore);
            ((CalendarComponent)noteProlongation).setColumn("Student.Notes.SessionProlongationDate");

            //noteCloseDate = new HtmlTextEdit("Close Date goes here", groupCardPage);
            //noteCloseDate = new HtmlTextEdit("CloseDate", groupCardPage);
            noteCloseDate = new CalendarComponent("CloseDate", groupCardPage);
            ((CalendarComponent)noteCloseDate).set_ds(dataStore);
            ((CalendarComponent)noteCloseDate).setColumn("Student.Notes.SessionClosingDate");

            noteGrant = new HtmlTextEdit("Grant goes here", groupCardPage);
            notePractice = new HtmlTextEdit("Practice Date goes here", groupCardPage);
        }

        try{
            if(!editNotesMode){
                ((HtmlCheckBox)noteFreeAttendance).setColumn(dataStore, "Student.Notes.FreeAttendance");
                ((HtmlCheckBox)noteFreeAttendance).setEnabled(false);
                ((HtmlText)noteRebuke).setExpression(dataStore, "Student.Notes.Rebuke");
                ((HtmlText)noteLastExam).setExpression(dataStore, "Student.Notes.LastExamDate.toDate()");
                ((HtmlText)noteProlongation).setExpression(dataStore, "Student.Notes.SessionProlongationDate.toDate()");
                ((HtmlText)noteCloseDate).setExpression(dataStore, "Student.Notes.SessionClosingDate.toDate()");
                ((HtmlText)noteGrant).setExpression(dataStore, "Student.Notes.StudentGrant");
                ((HtmlText)notePractice).setExpression(dataStore, "Student.Notes.Practice");

            }else{
                ((HtmlCheckBox)noteFreeAttendance).setColumn(dataStore, "Student.Notes.FreeAttendance");
                ((HtmlCheckBox)noteFreeAttendance).setEnabled(true);
                ((HtmlTextEdit)noteRebuke).setColumn(dataStore, "Student.Notes.Rebuke");
                //((HtmlTextEdit)noteLastExam).setColumn(dataStore, "Student.Notes.LastExamDate");
                //((HtmlTextEdit)noteProlongation).setColumn(dataStore, "Student.Notes.SessionProlongationDate");
                //((HtmlTextEdit)noteCloseDate).setColumn(dataStore, "Student.Notes.SessionClosingDate");
                ((HtmlTextEdit)noteGrant).setColumn(dataStore, "Student.Notes.StudentGrant");
                ((HtmlTextEdit)notePractice).setColumn(dataStore, "Student.Notes.Practice");

            }
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
        cardTable.setRowComponentAt(ind++, noteFreeAttendance);
        cardTable.setRowComponentAt(ind++, noteRebuke);
        cardTable.setRowComponentAt(ind++, noteLastExam);
        cardTable.setRowComponentAt(ind++, noteProlongation);
        cardTable.setRowComponentAt(ind++, noteCloseDate);
        cardTable.setRowComponentAt(ind++, noteGrant);
        cardTable.setRowComponentAt(ind++, notePractice);

        // footer
        HtmlTable summaryContainer = new HtmlTable("SummaryContainer", getPage());

        HtmlText summaryHtml1 = new HtmlText("Всего студентов в группе: " + card.getGroupCardStudents().size(), getPage());
        HtmlText summaryHtml2 = new HtmlText("Из них не сдало сессию: " + getOpenSessionStudentSize(), getPage());
        HtmlText summaryHtml3 = new HtmlText("Из них сдало сессию: " + getClosedSessionStudentSize(), getPage());
        HtmlText summaryHtml4 = new HtmlText("Из них отчислено: " + getFiredStudentSize(), getPage());

        //set font for summary
        summaryHtml1.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"Black\">");
        summaryHtml1.setFontEndTag(EndTag);
        summaryHtml2.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"Black\">");
        summaryHtml2.setFontEndTag(EndTag);
        summaryHtml3.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"Black\">");
        summaryHtml3.setFontEndTag(EndTag);
        summaryHtml4.setFontStartTag("<font face=\"Arial\" size=\"3\" color=\"Black\">");
        summaryHtml4.setFontEndTag(EndTag);

        summaryContainer.setComponentAt(0, 0, summaryHtml1);
        summaryContainer.setComponentAt(1, 0, summaryHtml2);
        summaryContainer.setComponentAt(2, 0, summaryHtml3);
        summaryContainer.setComponentAt(3, 0, summaryHtml4);
        summaryBox.setBoxComponent(summaryContainer);

    }

    public void pageRequestEnd(PageEvent p) throws Exception {

    }

    public void pageSubmitEnd(PageEvent p) {

    }

    public void pageSubmitted(PageEvent p) {

    }

    public void generateHTML(PrintWriter p, int rowNo) throws Exception {
        super.generateHTML(p, rowNo);
    }

    public boolean submitPerformed(SubmitEvent e) throws Exception {
        if(e.getComponent() == groupCardSearchButton){
            if(edGroup.getValue()==null || edGroup.getValue().trim().length()==0){
              _validator.setErrorMessage(ENTER_GROUP);
            }else{
                try{
                    populateComponent();
                    // save group card to backup
                    tableBox.setVisible(true);
                    summaryBox.setVisible(true);
                    if(editNotesMode){
                        groupCardUpdateButton.setVisible(true);
                    }
                    groupCardReportButton.setVisible(true);
                }catch(Exception ex){
                    _validator.setErrorMessage(ex.getMessage());
                }
            }
        }
        if(e.getComponent() == groupCardUpdateButton){
            //dataStoreBackup = new GroupCardBeanModel();
            //dataStoreBackup.insertRows(dataStore.getRows());
            List changed = new ArrayList();
            int count = dataStore.getRowCount();
            for(int i=0; i< count; i++){
                GroupCardStudent student = (GroupCardStudent)dataStore.getDataRow(i).getBean();
                if(dataStore.getDataRow(i).getRowStatus() == DataStore.STATUS_MODIFIED){
                    changed.add(student.getStudent().getStudentId());
                }
            }

            MessageLog.writeDebugMessage("going to update notes", this);
            dataStore.update();
            updateNotes(dataStore, changed);
        }
        if(e.getComponent() == groupCardReportButton){

            // fill
            //Preparing parameters
            Map parameters = new HashMap();
            cardTable.reset();

            File tmpDesignFile = null;
            File tmpPrintFile = null;
            File tmpDestFile = null;
            try{

              GroupCard card0 = buildGroupCard(new Boolean(false));
              GroupCard card1 = buildGroupCard(new Boolean(true));
              JasperDesign jasperDesign = new GroupCardReport(card0, card1).designReport();

              tmpDesignFile = File.createTempFile("groupcard", "design");
              tmpPrintFile = new File(tmpDesignFile.getParent(), "groupcard.jrprint");

              MessageLog.writeInfoMessage("design file [" + tmpDesignFile.getAbsolutePath() + "]", this);

              JasperCompileManager.compileReportToFile(jasperDesign, tmpDesignFile.getAbsolutePath());

              parameters.put("ReportTitle", "Учебная карточка");

                GroupCardBeanModel dataStore0 = new GroupCardBeanModel();
                GroupCardBeanModel dataStore1 = new GroupCardBeanModel();
                populateData(card0, dataStore0);
                populateData(card1, dataStore1);
                // print report from first row
                dataStore0.gotoFirst();
                dataStore1.gotoFirst();

              JasperFillManager.fillReportToFile(tmpDesignFile.getAbsolutePath(), parameters,
                      new GroupCardJasperDataSource(dataStore0, dataStore1));

              JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(tmpPrintFile);

              tmpDestFile = File.createTempFile("groupcard", "xls");

              JRXlsExporter exporter = new JRXlsExporter();

              exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
              exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, tmpDestFile.toString());
              exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

              exporter.exportReport();

              controller.gotoSiteMapPage("JasperReportDownload", "?reportFileName=" + tmpDestFile.getAbsolutePath());

            }catch(Throwable e1){
              e1.printStackTrace();
            }finally{
              try{
                if(tmpDesignFile!=null){
                  tmpDesignFile.delete();
                }
                if(tmpPrintFile!=null){
                  tmpPrintFile.delete();
                }
                if(tmpDestFile!=null){
                  tmpDestFile.deleteOnExit();
                }
              }catch(Exception e2){
                MessageLog.writeErrorMessage(e2, this);
              }
            }



        }
        return true;
    }


    private void updateNotes(GroupCardBeanModel _card, List changedStudents) throws HibernateException{

        Session hibSession=
         com.salmonllc.hibernate.HibernateSessionFactory.getSession();

        int count = _card.getRowCount();
        for(int i=0; i< count; i++){
            GroupCardStudent student = (GroupCardStudent)_card.getDataRow(i).getBean();
            if(changedStudents.contains(student.getStudent().getStudentId())){
                Note note = student.getNotes();
                boolean isNew = false;
                if(note!=null){
                    if(note.getStudent() == null){
                        note.setStudent(student.getStudent());
                    }
                    if(note.getStudentGroup() == null){
                        note.setStudentGroup(student.getStudent().getStudentGroup());
                    }
                    if(note.getCard() == null){
                        Card card0 = new Card();
                        card0.setCourse(card.getGroup().getCourse());
                        card0.setSemester((edSemestr.getValue().equals("0")) ? false : true);
                        card0.setYear(Short.parseShort(edYear.getValue()));
                        isNew = true;
                        hibSession.save(card0);
                        note.setCard(card0);
                    }
                    MessageLog.writeDebugMessage("Changed [" + note.getFreeAttendance() + ", " +
                    note.getRebuke() + ", "
                    + note.getLastExamDate() + ", "
                    + note.getSessionProlongationDate() + ", "
                    + note.getSessionClosingDate() + ", "
                    + note.getStudentgrant() + ", "
                    + note.getPractice() + "]", this);
                    if(isNew){
                        hibSession.save(note);
                    }else{
                        hibSession.update(note);
                        MessageLog.writeDebugMessage("" + note.getNoteId(), this);
                    }
                    hibSession.flush();
                }
            }

        }
    }

    private GroupCard buildGroupCard(Boolean semester) throws Exception{

        HibernateGroupModel studentGroups = new HibernateGroupModel();
        String filter0 = groupQBE.generateSQLFilter(studentGroups);

        // retrieve group
        groupQBE.retrieve(studentGroups);
        MessageLog.writeDebugMessage("Fiter0 = [" + filter0 + "]", this);

        //1. group
        StudentGroup group = null;
        int size = studentGroups.getRowCount();
        if(size == 0){
            throw new Exception("Группа с номером [" + edGroup.getValue() + "] не найдена");
        }
        group = (StudentGroup)studentGroups.getDataRow(0).getBean();

        //2. speciality
        Speciality spec = group.getSpeciality();
        MessageLog.writeDebugMessage("Speciality id = [" + spec.getSpecialityId() + "]", this);

        //3. education plan
        Set plans = spec.getEducationPlans();

        if(plans.size()==0){
            throw new Exception("Не найден учебный план для специальности [" + spec.getShortname() + "]");
        }

        List disciplines = new ArrayList();
        Iterator allPlans = plans.iterator();

        while(allPlans.hasNext()){
            EducationPlan plan = (EducationPlan)allPlans.next();
            //4. search disciplines
            HibernateDisciplinesModel disciplineModel = new HibernateDisciplinesModel();

            disciplineQBE.setString(HibernateDisciplinesModel.EDUCATION_PLAN_ID, plan.getEducationPlanId().toString());
            if(semester!=null){
                disciplineQBE.setString(HibernateDisciplinesModel.SEMESTER, semester.booleanValue() ? "1" : "0");
            }
            //disciplineQBE.setOrderBy(HibernateDisciplinesModel.CHECKING_FORM);
            String filter = disciplineQBE.generateSQLFilter(disciplineModel);
            MessageLog.writeDebugMessage("Fiter = [" + filter + "]", this);
            disciplineQBE.retrieve(disciplineModel);

            for(int i=0; i<disciplineModel.getRowCount(); i++){
                Discipline discipline = (Discipline)disciplineModel.getDataRow(i).getBean();
                if(!disciplines.contains(discipline)){
                    disciplines.add(discipline);
                }
            }
        }

        Collections.sort(disciplines,
                new Comparator(){
                    public int compare(Object o, Object o1) {
                        if(o instanceof Discipline && o1 instanceof Discipline){
                            return ((Discipline)o).getCheckingForm() - ((Discipline)o1).getCheckingForm();
                        }
                        return 0;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                }
        );

        //5. search student and marks
        List studentMarks = new ArrayList();

        //5.a get students from group
        List students = new ArrayList(group.getStudents());
        // sort students by student id
        Collections.sort(students, new Comparator(){
            public int compare(Object o, Object o1) {
                if(o instanceof Student && o1 instanceof Student){
                    return (int)(((Student)o).getStudentId().longValue() - ((Student)o1).getStudentId().longValue());
                }
                return 0;
            }
        });

        StringBuffer studentsIdsForMarks = new StringBuffer();
        StringBuffer studentsIdsForNotes = new StringBuffer();
        if(students.size() > 0){

            // criterion for filter marks by group
            studentsIdsForMarks.append(HibernateStudentMarkModel.GROUP_ID);
            studentsIdsForMarks.append("=");
            studentsIdsForMarks.append(group.getStudentGroupId());

            // criterion for filter notes by group
            studentsIdsForNotes.append(HibernateStudentNoteModel.GROUP_ID);
            studentsIdsForNotes.append("=");
            studentsIdsForNotes.append(group.getStudentGroupId());

            // criterion for filter marks and notes by student
            studentsIdsForMarks.append(" AND ");
            studentsIdsForMarks.append("( ");

            studentsIdsForNotes.append(" AND ");
            studentsIdsForNotes.append("( ");

            Iterator allStudents = students.iterator();
                while(allStudents.hasNext()){
                    Student _student = (Student)allStudents.next();

                    studentsIdsForMarks.append(HibernateStudentMarkModel.STUDENT_ID);
                    studentsIdsForMarks.append("=");
                    studentsIdsForMarks.append(_student.getStudentId());

                    studentsIdsForNotes.append(HibernateStudentNoteModel.STUDENT_ID);
                    studentsIdsForNotes.append("=");
                    studentsIdsForNotes.append(_student.getStudentId());


                    if(allStudents.hasNext()){
                        studentsIdsForMarks.append(" OR ");
                        studentsIdsForNotes.append(" OR ");
                    }

                    // apend student
                    GroupCardStudent groupCardStudent = new GroupCardStudent();
                    groupCardStudent.setStudent(_student);
                    studentMarks.add(groupCardStudent);
                }
            studentsIdsForMarks.append(" )");
            studentsIdsForNotes.append(" )");

            HibernateStudentMarkModel studentMarksModel = new HibernateStudentMarkModel();
            MessageLog.writeDebugMessage("trying to retrieve marks by criteria [" + studentsIdsForMarks.toString() + "]", this);
            studentMarksModel.retrieve(studentsIdsForMarks.toString());


            for(int i=0; i<studentMarksModel.getRowCount(); i++){
                Mark studentMark = (Mark)studentMarksModel.getDataRow(i).getBean();
                Student _student = studentMark.getStudent();
                GroupCardStudent groupCardStudent = containsStudent(studentMarks, _student);
                if(groupCardStudent == null){
                    groupCardStudent = new GroupCardStudent();
                    groupCardStudent.setStudent(_student);
                    studentMarks.add(groupCardStudent);
                }
                groupCardStudent.setMarks(studentMark.getDiscipline().getDisciplineId(), studentMark);
            }

            // retrieve student notes
            //studentsIdsForNotes.append(" AND ");
            //studentsIdsForNotes.append(HibernateStudentNoteModel.CARD_COURSE);
            //studentsIdsForNotes.append(" = ");
            //studentsIdsForNotes.append(edCourse.getValue());
            studentsIdsForNotes.append(" AND ");
            studentsIdsForNotes.append(HibernateStudentNoteModel.CARD_SEMESTER);
            studentsIdsForNotes.append(" = ");
            if(semester!=null){
                studentsIdsForNotes.append(semester.booleanValue() ? "1" : "0");
            }else{
                studentsIdsForNotes.append(edSemestr.getValue());
            }
            studentsIdsForNotes.append(" AND ");
            studentsIdsForNotes.append(HibernateStudentNoteModel.CARD_YEAR);
            studentsIdsForNotes.append(" = ");
            studentsIdsForNotes.append(edYear.getValue());
            HibernateStudentNoteModel studentNotesModel = new HibernateStudentNoteModel();
            MessageLog.writeDebugMessage("trying to retrieve notes by criteria [" + studentsIdsForNotes.toString() + "]", this);
            studentNotesModel.retrieve(studentsIdsForNotes.toString());

            for(int i=0; i<studentNotesModel.getRowCount(); i++){
                Note studentNote = (Note)studentNotesModel.getDataRow(i).getBean();
                Student _student = studentNote.getStudent();

                GroupCardStudent groupCardStudent = containsStudent(studentMarks, _student);
                if(groupCardStudent == null){
                    groupCardStudent = new GroupCardStudent();
                    groupCardStudent.setStudent(_student);
                    studentMarks.add(groupCardStudent);
                }
                groupCardStudent.setNotes(studentNote);
                /*if(!selectAll.getValue().equals("0") && studentNote.getSessionClosingDate()!=null){
                    studentMarks.remove(groupCardStudent);
                }*/

            }
        }

        // sort group card students by session closed/not closed and fired state
        studentMarks = sortGroupCardStudents(studentMarks);
        GroupCard card0 = new GroupCard();
        card0.setDisciplines(disciplines);
        card0.setGroupCardStudents(studentMarks);
        card0.setGroup(group);
        return card0;
    }



    public int getClosedSessionStudentSize() {
        return closedSessionStudentSize;
    }

    public void setClosedSessionStudentSize(int closedSessionStudentSize) {
        this.closedSessionStudentSize = closedSessionStudentSize;
    }

    public int getOpenSessionStudentSize() {
        return openSessionStudentSize;
    }

    public void setOpenSessionStudentSize(int openSessionStudentSize) {
        this.openSessionStudentSize = openSessionStudentSize;
    }

    public int getFiredStudentSize() {
        return firedStudentSize;
    }

    public void setFiredStudentSize(int firedStudentSize) {
        this.firedStudentSize = firedStudentSize;
    }

    private int closedSessionStudentSize;
    private int openSessionStudentSize;
    private int firedStudentSize;

}
