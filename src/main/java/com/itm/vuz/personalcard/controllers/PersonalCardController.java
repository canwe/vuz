//package statement
package com.itm.vuz.personalcard.controllers;

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.domain.StudentEvent;
import com.itm.vuz.common.domain.StudentHistory;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.itm.vuz.personalcard.models.StudentEventModel;
import com.itm.vuz.personalcard.models.StudentHistoryModel;
import com.itm.vuz.personalcard.models.StudentModel;

import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.util.MessageLog;

import org.hibernate.Session;

import java.util.Iterator;


/**
 * PersonalCardController: a SOFIA generated controller
 */
public class PersonalCardController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlSubmitButton _sbtHistory;
    public com.salmonllc.html.HtmlSubmitButton _sbtPersonalCardLink;

    public com.salmonllc.html.HtmlSubmitButton _sbtUpdate;
    public com.salmonllc.html.HtmlSubmitButton _sbtCancel;
    public com.salmonllc.html.HtmlSubmitButton _sbtDelete;

    public com.salmonllc.jsp.JspContainer _ctHistory;
    public com.salmonllc.jsp.JspContainer _ctPersonalCard;

    public com.salmonllc.jsp.JspForm _pageForm;

    public com.salmonllc.jsp.JspTableCell _tabMenuTDRow1;
    public com.salmonllc.jsp.JspTableCell _tabMenuTDRow2;


    //public com.salmonllc.html.HtmlLookUpComponent _luEventDate;
    public com.itm.vuz.common.CalendarComponent _clndEventDate;
    public com.salmonllc.html.HtmlDropDownList _ddlStudentEvent;
    public com.salmonllc.html.HtmlTextEdit _teEventComment;
    public com.salmonllc.html.HtmlSubmitButton _sbtAddSimpleEvent;

//DataSources
    public StudentModel _dsStudentModel;
    public StudentHistoryModel _dsStudentHistory;
    public StudentHistoryModel _dsStudentHistoryNotSigned;

    private final String STUDENT_ID_PARAM = "student_id";

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        addPageListener(this);
        //Кнопки смены вида
        this._sbtPersonalCardLink.addSubmitListener(this);
        this._sbtHistory.addSubmitListener(this);
        //Кнопки управления личным делом
        this._sbtUpdate.addSubmitListener(this);
        this._sbtCancel.addSubmitListener(this);
        this._sbtDelete.addSubmitListener(this);
        //Кнопка добавления событий
        this._sbtAddSimpleEvent.addSubmitListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) throws Exception {
        try {
            if ((event.getComponent() == this._sbtPersonalCardLink) ||
                (event.getComponent() == this._sbtHistory)) {
                //Переключаемся к одному из видов
                this.changeView((com.salmonllc.html.HtmlSubmitButton) event.getComponent());
            }

            if ((event.getComponent() == this._sbtUpdate) ||
                (event.getComponent() == this._sbtCancel) ||
                (event.getComponent() == this._sbtDelete)) {
                //Отрабатываем действие по изменению личного дела
                this.handlePersonalCardAction((com.salmonllc.html.HtmlSubmitButton) event.getComponent());
            }

            if (event.getComponent() == this._sbtAddSimpleEvent) {
                //Добавляем событие в список событий студента
                this.AddStudentEvent();
            }
        } catch(Exception e) {
            MessageLog.writeErrorMessage(e, this);
            throw e;
        }
        return true;
    }

    /**
     * Возвращает идентификатор студента из строки запроса (URL).
     * Если он не задан, возвращается -1.
     * @return Id студента либо -1, если не задано
     */
    private int getStudentIdParam() {
        return this.getIntParameter(STUDENT_ID_PARAM);
    }

    /**
     * Реакция на кнопки сохранения/отмены/удаления личного дела
     * @param actionBtn кнопка, определяющая вид действия
     * @throws Exception
     */
    private void handlePersonalCardAction(com.salmonllc.html.HtmlSubmitButton actionBtn) throws Exception {
        if (actionBtn == this._sbtUpdate) {
            //Сохранение изменений
            this._dsStudentModel.update();
            //Если это было новое личное дело, надо дописать в строку запроса
            //идентификатор созданного личного дела
            if (this.getStudentIdParam() == -1) {
                Long id = this._dsStudentModel.getStudent().getStudentId();
                this.gotoSiteMapPage(SiteMapConstants.PERSONAL_CARD, "?" + STUDENT_ID_PARAM + "=" + id.toString());
            }
        }
        if (actionBtn == this._sbtCancel) {
            //В случае отмены изменений в зависимости от наличия загруженных данных
            //перезагружаем их из БД либо возвращаемся в режим добавления
            this.loadPersonalCardData(this.getStudentIdParam());
        }
        if (actionBtn == this._sbtDelete) {
            //Удаляем личное дело студента
            if (this._dsStudentModel.getStudent() != null) {
                this._dsStudentModel.deleteRow();
                this._dsStudentModel.update();
            }
        }
    }

    /**
     * Переключение между различными закладками
     * @param changeBtn Конпка определяет, какую закладку необходимо сделать активной
     * @throws Exception
     */
    private void changeView(com.salmonllc.html.HtmlSubmitButton changeBtn) throws Exception {
        //Делаем все ячейки в меню не выделенными
        this._tabMenuTDRow1.setStyle("MenuCell");
        this._tabMenuTDRow2.setStyle("MenuCell");
        //Показываем личную карточку
        if (changeBtn == this._sbtPersonalCardLink) {
            this._ctPersonalCard.setVisible(true);
            this._ctHistory.setVisible(false);
            this._tabMenuTDRow1.setStyle("SelectedMenuCell");
            this.loadPersonalCardData(this.getStudentIdParam());
        }
        //Показываем список событий
        if (changeBtn == this._sbtHistory) {
            this._ctPersonalCard.setVisible(false);
            this._ctHistory.setVisible(true);
            this._tabMenuTDRow2.setStyle("SelectedMenuCell");
            this.loadStudentHistory(this.getStudentIdParam());
        }
    }

    /**
     * Загрузка личного дела
     * @param studentID Идентификатор студента. Если -1 (отсутствует),
     * то создается новое личное дело
     * @throws Exception
     */
    private void loadPersonalCardData(int studentID) throws Exception {
        if (studentID == -1) {
            //Если параметр нам не паредали, то переходим в режим добавления
            this._dsStudentModel.reset();
            this._dsStudentModel.insertRow();
        } else {
            //Параметр передали, пробуем загрузить личное дело
            this._dsStudentModel.retrieve(StudentModel.STUDENT_ID + " = " + Integer.toString(studentID));
            if (this._dsStudentModel.getRowCount() == 0) {
                this._dsStudentModel.reset();
                this._dsStudentModel.insertRow();
            } else {
                this._dsStudentModel.gotoFirst();
            }
        }
    }

    /**
     * Загружает список событий студента и делит его на 2 списка
     * @param studentID Идентификатор студента
     * @throws Exception
     */
    private void loadStudentHistory(int studentID) throws Exception {
        //Очищаем датасеты
        this._dsStudentHistory.reset();
        this._dsStudentHistoryNotSigned.reset();

        if (studentID != -1) {
            //Если идентификатор студента задан и в его модели есть текущая строка,
            //то берем данные из объекта Student
            Session sess = HibernateSessionFactory.getSession();
            Student s = (Student) sess.get(Student.class, new Long(studentID));

            //Берем список событий студента и делим его на 2 части
            Iterator iter = s.getStudentHistories().iterator();

            while (iter.hasNext()) {
                StudentHistory history = (StudentHistory) iter.next();
                //Если у записи в истории есть связанный приказ и он не зарегистрирован
                // (проект), то эта запись показывается отдельно от всех остальных
                if ((history.getOfficeOrder() != null) &&
                    (!history.getOfficeOrder().getIssigned().booleanValue())) {
                    this._dsStudentHistoryNotSigned.insertRow(history);
                } else {
                    //Иначе запись показывается в общем порядке
                    this._dsStudentHistory.insertRow(history);
                }
            }

            //Сортируем записи в истории событий в порядке "последние сверху"
            this._dsStudentHistory.sort(StudentHistoryModel.DATE_EVENT, DataStoreBuffer.SORT_DES);
            this._dsStudentHistoryNotSigned.sort(StudentHistoryModel.DATE_EVENT, DataStoreBuffer.SORT_DES);
        }
    }

    /**
     * Добавляет новое событие
     * @throws Exception
     */
    private void AddStudentEvent() throws Exception {
        //Создаем новую запись в истории студента
        StudentHistory newEvent = new StudentHistory();

        //парсим дату
        newEvent.setDateEvent(
            this._clndEventDate.getFormat().parse(this._clndEventDate.getValue()));

        //определяем тип события
        StudentEventModel eventModel = new StudentEventModel();
        eventModel.retrieve(StudentEventModel.EVENT_ID + "=" + this._ddlStudentEvent.getValue());
        eventModel.gotoFirst();
        StudentEvent eventType = eventModel.getStudentEvent();
        newEvent.setStudentEvent(eventType);

        newEvent.setComment(this._teEventComment.getValue());
        newEvent.setStudent(this._dsStudentModel.getStudent());

        //Сохраняем новое событие и обновляем списки событий
        HibernateSessionFactory.getSession().save(newEvent);
        this.loadStudentHistory(
            this._dsStudentModel.getStudent().getStudentId().intValue());
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);

        /* Проверка прав и настройка доступа */
        if (this.isGuest()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }

        this._sbtUpdate.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtDelete.setEnabled(this.isAdmin() || this.isDecanat());
        //Эта строка для наглядности, что-бы никакие основные кнопки не были упущены
        this._sbtAddSimpleEvent.setEnabled(this.isAdmin() || this.isDecanat() || isDepartment());

        if (!this.isReferredByCurrentPage()) {
            this.changeView(this._sbtPersonalCardLink);
            this.loadPersonalCardData(this.getIntParameter(STUDENT_ID_PARAM));
        }
    }
}
