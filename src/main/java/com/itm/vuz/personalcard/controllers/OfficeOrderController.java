//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.*;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.itm.vuz.personalcard.models.StudentModel;
import com.itm.vuz.personalcard.models.StudentHistoryModel;
import com.itm.vuz.personalcard.models.OfficeOrderModel;
import com.itm.vuz.personalcard.OfficeOrderReportDataSource;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * OfficeOrderController: a SOFIA generated controller
 */
public class OfficeOrderController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _ddlEventType;
    public com.salmonllc.html.HtmlSubmitButton _sbtSave;
    public com.salmonllc.html.HtmlSubmitButton _sbtDelete;
    public com.salmonllc.html.HtmlSubmitButton _sbtRemoveSelectedStudents;
    public com.salmonllc.html.HtmlSubmitButton _sbtAddStudentsFromBasket;
    public com.salmonllc.html.HtmlSubmitButton _sbtPrintOfficeOrder;

//DataSources
    public OfficeOrderModel _dsOfficeOrder;
    public StudentHistoryModel _dsStudentHistory;

    private static final String OFFICE_ORDER_PARAM = "officeOrderId";

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
        
        addPageListener(this);
        this._sbtSave.addSubmitListener(this);
        this._sbtDelete.addSubmitListener(this);
        this._sbtRemoveSelectedStudents.addSubmitListener(this);
        this._sbtAddStudentsFromBasket.addSubmitListener(this);
        this._sbtPrintOfficeOrder.addSubmitListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) throws Exception {
        try {
            //Сохранение
            if (event.getComponent() == this._sbtSave) {
                OfficeOrder officeOrder = this.saveOrUpdateOfficeOrder();

                if (null != officeOrder) {
                    //Перезагружаем сохраненный приказ
                    this.loadOfficeOrder(officeOrder.getOfficeOrderId());
                    if (null == this.getOfficeOrderID()) {
                        //Перенаправляем на эту-же страницу с параметром - id приказа, т.е.
                        //переход в режим редактирования уже созданного приказа
                        this.gotoSiteMapPage(SiteMapConstants.OFFICE_ORDER,
                            "?" + OFFICE_ORDER_PARAM + "=" + officeOrder.getOfficeOrderId().toString());
                    }
                }
            }
            //Добавление дополнительных студентов из списка
            if (event.getComponent() == this._sbtAddStudentsFromBasket) {
                this.generateStudentHistories(this.getSessionManager().getStudentBasket().getStudents());
            }
            //Удаление студентов из списка
            if (event.getComponent() == this._sbtRemoveSelectedStudents) {
                deleteSelectedStudents();
            }
            //Удаление приказа и переход к странице поиска приказов
            if (event.getComponent() == this._sbtDelete) {
                deleteOfficeOrder();
            }
            //Печать приказа
            if (event.getComponent() == this._sbtPrintOfficeOrder) {
                this.printOfficeOrder(this._dsOfficeOrder.getOfficeOrder());
            }
        }
        catch (Exception e) {
            MessageLog.writeErrorMessage(e, this);
            //TODO: redirect to Error Page? reThrow?
            throw e;
        }
        return true;
    }

    /**
     * Печатает приказ в Excel
     * @param order Приказ для печати
     * @throws JRException
     * @throws IOException
     * @throws ServletException
     */
    private void printOfficeOrder(OfficeOrder order) throws JRException, IOException, ServletException, URISyntaxException {
        File tmpDestFile = null;

        tmpDestFile = File.createTempFile("OfficeOrderReport", "xls");
        URI uri = new URI(this.getClass().getClassLoader().getResource("OfficeOrderReport.jrxml").toString());

        MessageLog.writeDebugMessage("uri.getPath() = [" + uri.getPath() + "]", this);

        JasperReport report =
            JasperCompileManager.compileReport(uri.getPath());
        HashMap params = new HashMap();
        params.put("dateOrder", order.getDateOrder());
        params.put("externalNumber", this.whenNullReturnEmpty(order.getExternalNumber()));
        params.put("subject", this.whenNullReturnEmpty(order.getSubject()));
        params.put("reason", this.whenNullReturnEmpty(order.getReason()));
        params.put("signerPosition", this.whenNullReturnEmpty(order.getSignerPosition()));
        params.put("signerName", this.whenNullReturnEmpty(order.getSignerName()));
        params.put("makerPosition", this.whenNullReturnEmpty(order.getMakerPosition()));
        params.put("makerName", this.whenNullReturnEmpty(order.getMakerName()));
        params.put("coordinator1Position", this.whenNullReturnEmpty(order.getCoordinator1Position()));
        params.put("coordinator1Name", this.whenNullReturnEmpty(order.getCoordinator1Name()));
        params.put("coordinator2Position", this.whenNullReturnEmpty(order.getCoordinator2Position()));
        params.put("coordinator2Name", this.whenNullReturnEmpty(order.getCoordinator2Name()));
        params.put("coordinator3Position", this.whenNullReturnEmpty(order.getCoordinator3Position()));
        params.put("coordinator3Name", this.whenNullReturnEmpty(order.getCoordinator3Name()));

        this._dsStudentHistory.gotoFirst();
        OfficeOrderReportDataSource rds = new OfficeOrderReportDataSource(this._dsStudentHistory);

        JasperPrint print = JasperFillManager.fillReport(report, params, rds);
        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, tmpDestFile.toString());
        xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

        xlsExporter.exportReport();

        this.gotoSiteMapPage("JasperReportDownload", "?reportFileName=" + tmpDestFile.getAbsolutePath());
    }

    private final String EMPTY_STRING = "";

    /**
     * Если переданный параметр null, возвращает пустую строку
     * @param s Строка для анализа
     * @return Переданная строка либо пустая, если параметр == null
     */
    private String whenNullReturnEmpty(String s) {
        return s == null ? this.EMPTY_STRING : s;
    }

    /**
     * Удаляет приказ и записи о приказе из истории студентов
     * @throws Exception
     */
    private void deleteOfficeOrder() throws Exception {
        Session sess = HibernateSessionFactory.getSession();
        Transaction tran = sess.beginTransaction();
        try {
            //Сначала удаляем все записи в истории студентов для этого приказа,
            //т.к. в маппинге отключено каскадное удаление
            for (int i = 0; i < this._dsStudentHistory.getRowCount(); i++) {
                sess.delete(this._dsStudentHistory.getStudentHistory(i));
            }
            //потом уже сам приказ
            OfficeOrder order = this._dsOfficeOrder.getOfficeOrder();
            sess.delete(order);

            tran.commit();

            this.gotoSiteMapPage(SiteMapConstants.OFFICE_ORDER_SEARCH);
        }
        catch (Exception e) {
            tran.rollback();
            throw e;
        }
    }

    /**
     * Удаляет отмеченных студентов из приказа
     * @throws DataStoreException
     */
    private void deleteSelectedStudents() throws DataStoreException {
        ArrayList toDelete = new ArrayList();
        for (int i = 0; i < this._dsStudentHistory.getRowCount(); i++) {
            if (this._dsStudentHistory.isChecked(i)) {
                toDelete.add(new Integer(i));
            }
        }
        //Удаляем в обратном порядке, т.к. по мере удаления изменяются индексы строк
        for (int i = toDelete.size() - 1; i >= 0; i--) {
            int rowIndex = ((Integer) toDelete.get(i)).intValue();
            this._dsStudentHistory.deleteRow(rowIndex);
        }
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);
        /* Проверка прав и разграничение доступа */
        if (this.isGuest()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }
        this._sbtSave.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtDelete.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtAddStudentsFromBasket.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtRemoveSelectedStudents.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtPrintOfficeOrder.setEnabled(this.isAdmin() || this.isDecanat());

        Long officeOrderID = this.getOfficeOrderID();
        if (!this.isReferredByCurrentPage()) {
            //если это не постбек
            if (null == officeOrderID) {
                // Если не задан идентификатор редактируемого приказа,
                // переходим в режим создания нового приказа
                this.createNewOfficeOrder();
            }
            else {
                //иначе если это вход с заданным id приказа,
                // загружаем и отображаем приказ
                this.loadOfficeOrder(officeOrderID);
            }
        }
    }

    private StudentEvent getSelectedStudentEvent() throws HibernateException {
        if (this._ddlEventType.getValue() == null) {
            return null;
        }
        Session sess = HibernateSessionFactory.getSession();
        return (StudentEvent) sess.get(
            StudentEvent.class, new Long(this._ddlEventType.getValue()));
    }

    /**
     * @param officeOrderId Идентификатор приказа, данные которого загружаются
     */
    private void loadOfficeOrder(Long officeOrderId) throws Exception {
        //Загружаем сам приказ
        this._dsOfficeOrder.retrieve(OfficeOrderModel.OFFICE_ORDER_ID + "=" + officeOrderId.toString());
        //Если приказ с таким идентификатором не обнаружен, переходим в режим добавления приказа
        if (this._dsOfficeOrder.getRowCount() == 0) {
            this.gotoSiteMapPage(SiteMapConstants.OFFICE_ORDER);
        }
        this._dsOfficeOrder.gotoFirst();
        //Если же данный приказ существует, то загружаем для него записи из истории студентов
        this._dsStudentHistory.retrieve(
            StudentHistoryModel.OFFICE_ORDER_ID + " is not null and " +
            StudentHistoryModel.OFFICE_ORDER_ID + "=" + officeOrderId.toString());
        //Если есть записи в истории, то попервой из них устанавливаем правильный тип события,
        //т.к. ко все записям из истории применяется один и тот-же тип события
        //TODO: Уточнить требования, возможно тип события и не нужен
        if (this._dsStudentHistory.getRowCount() > 0) {
            this._ddlEventType.populateDropdownOptions();
            StudentHistory sh = this._dsStudentHistory.getStudentHistory(0);
            this._ddlEventType.setValue(sh.getStudentEvent().getEventId().toString());
        }
        //Сортировка списка студентов
        this.sortStudentHistory();
    }

    /**
     * Сохраняет приказ в БД и соответствующие ему записи в истории студентов
     * @return Созданный приказ
     */
    private OfficeOrder saveOrUpdateOfficeOrder() throws Exception {
        //Сохраняем изменения в одной транзакции
        Session sess = HibernateSessionFactory.getSession();
        //WORKAROUND: Не смог понять и исправить следующее поведение(bag?):
        //Если открывается приказ, в котором уже есть студенты, и добавляются новые
        //из виртуального списка, то потом при сохранении, когда дело доходит до сохранения
        //уже существующих студентов, вываливается ексепшен, что объект с таким Id уже есть
        //в сессии (дело видимо в том, что при сохранении самого приказа его студенты уже
        // подгружаются в сессию и из-за этого начинается дублирование).
        //WORKAROUND Update 07.08.2006
        //http://saloon.javaranch.com/cgi-bin/ubb/ultimatebb.cgi?ubb=get_topic&f=78&t=000475
        //Здесь чел утверждает, что в его случае проблема была в cascade="all" в маппинге. Хм.
        sess.clear();
        Transaction tran = sess.beginTransaction();
        try {
            this._dsOfficeOrder.update(false);
            //сохраняем и привязываем создаваемый приказ к записям в истории студентов
            OfficeOrder order = this._dsOfficeOrder.getOfficeOrder();
            StudentEvent eventType = this.getSelectedStudentEvent();
            User loggedUser = this.getLoggedUser();
            java.sql.Date orderDate = this._dsOfficeOrder.getDate(OfficeOrderModel.DATE_ORDER);
            for (int rowIndex = 0; rowIndex < this._dsStudentHistory.getRowCount(); rowIndex++) {
                this._dsStudentHistory.setLong(
                    rowIndex, StudentHistoryModel.OFFICE_ORDER_ID, order.getOfficeOrderId().longValue());
                this._dsStudentHistory.setDate(
                    rowIndex, StudentHistoryModel.DATE_EVENT, orderDate);
                this._dsStudentHistory.setLong(
                    rowIndex, StudentHistoryModel.STUDENT_EVENT_ID, eventType.getEventId().longValue());
                this._dsStudentHistory.setInt(
                    rowIndex, StudentHistoryModel.USER_ID, loggedUser.getUserId().intValue());
            }
            this._dsStudentHistory.update(false);
            tran.commit();
            return order;
        }
        catch (Exception e) {
            tran.rollback();
            throw e;
        }
    }

    /**
     * Подготавливает форму к созданию нового приказа
     * Список студентов для приказа берется из виртуального списка студентов
     */
    private void createNewOfficeOrder() throws Exception {
        //Сбрасываем DataSource-ы и переводим приказы в режим добавления
        this._dsOfficeOrder.reset();
        this._dsOfficeOrder.insertRow();

        //Создаем таблицу студентов
        this._dsStudentHistory.reset();
        this.generateStudentHistories(this.getSessionManager().getStudentBasket().getStudents());
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

    /**
     * @return Возвращает идетификатор приказа из параметров командной строки
     *         либо null, если страница находится в режиме создания нового приказа
     */
    private Long getOfficeOrderID() {
        if (-1 != this.getIntParameter(OFFICE_ORDER_PARAM)) {
            return new Long(this.getIntParameter(OFFICE_ORDER_PARAM));
        }
        return null;
    }

    /**
     * Задача метода - заполнить табличную часть приказа списком переданных студентов
     * Этот метод не очищает таблицу - это следует делать вызывающему коду (при необходимости)
     *
     * @param students Список студентов
     */
    private void generateStudentHistories(StudentModel students) throws Exception {
        //Получаем текущего пользователя
        User loggedUser = this.getLoggedUser();
        //Для каждого студента создаем запись в истории
        for (int i = 0; i < students.getRowCount(); i++) {
            Student currentStudent = students.getStudent(i);
            //Проверка на наличие группы (для заполнения приказа студент уже должен быть в группе)
            if (currentStudent.getStudentGroup() == null) {
                throw new Exception("Group number for student with Id " + currentStudent.getStudentId().toString() + " is missing");
            }

            StudentHistory sh = new StudentHistory();
            sh.setStudent(currentStudent);
            sh.setUser(loggedUser);

            this._dsStudentHistory.insertRow(sh, true);
        }
        //Сортируем список записей историй студентов по № группы и ФИО студента
        this.sortStudentHistory();
    }

    /**
     * Сортирует список записей историй студентов по № группы и ФИО студента
     */
    private void sortStudentHistory() throws DataStoreException {
        String[] sortColumns = new String[3];
        sortColumns[0] = "StudentHistory.student.studentGroup.number";
        sortColumns[1] = "StudentHistory.student.familyName";
        sortColumns[2] = "StudentHistory.student.name";
        int[] sortDirections = new int[3];
        sortDirections[0] = DataStoreBuffer.SORT_ASC;
        sortDirections[1] = DataStoreBuffer.SORT_ASC;
        sortDirections[2] = DataStoreBuffer.SORT_ASC;
        this._dsStudentHistory.sort(sortColumns, sortDirections);
    }
}
