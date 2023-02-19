//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.domain.StudentHistory;
import com.itm.vuz.common.domain.StudentEvent;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.itm.vuz.personalcard.models.StudentModel;
import com.itm.vuz.personalcard.models.StudentEventModel;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.salmonllc.hibernate.HibernateSessionFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;


/**
 * StudentBasketController: a SOFIA generated controller
 */
public class StudentBasketController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlSubmitButton _sbtAddEvent;
    public com.salmonllc.html.HtmlSubmitButton _sbtAddOrder;
    public com.salmonllc.html.HtmlSubmitButton _sbtClearList;
    public com.salmonllc.html.HtmlSubmitButton _sbtRemoveSelected;

    public com.itm.vuz.common.CalendarComponent _clndEventDate;
    public com.salmonllc.html.HtmlDropDownList _ddlStudentEvent;
    public com.salmonllc.html.HtmlTextEdit _teEventComment;

//DataSources
    public StudentModel _dsStudents;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        this.addPageListener(this);
        this._sbtClearList.addSubmitListener(this);
        this._sbtAddOrder.addSubmitListener(this);
        this._sbtAddEvent.addSubmitListener(this);
        this._sbtRemoveSelected.addSubmitListener(this);
    }

    /**
     * Process the given submit event
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) throws Exception {
        try {
            if (event.getComponent() == this._sbtClearList) {
                this.removeFromBasket(false);
            }
            if (event.getComponent() == this._sbtRemoveSelected) {
                this.removeFromBasket(true);
            }
            if (event.getComponent() == this._sbtAddOrder) {
                this.createOfficeOrder();
            }
            if (event.getComponent() == this._sbtAddEvent) {
                this.addEventToStudents();
            }
        } catch (Exception e) {
            MessageLog.writeErrorMessage(e, this);
            throw e;
        }
        return true;
    }

    /**
     * Перенаправление на страницу создания прикахов из текущего списка студентов
     */
    private void createOfficeOrder() throws IOException, ServletException {
        this.gotoSiteMapPage(SiteMapConstants.OFFICE_ORDER);
    }

    /**
     * Добавляет событие с заданными атрибутами все студентам из списка
     * @throws Exception
     */
    private void addEventToStudents() throws Exception {
        Session sess = HibernateSessionFactory.getSession();
        Transaction tran = sess.beginTransaction();
        try {
        StudentModel students = this.getSessionManager().getStudentBasket().getStudents();
            for(int i = 0; i < students.getRowCount(); i++) {
                StudentHistory newEvent = new StudentHistory();
                //Дата события
                newEvent.setDateEvent(
                    this._clndEventDate.getFormat().parse(this._clndEventDate.getValue()));
                //определяем тип события
                StudentEventModel eventModel = new StudentEventModel();
                eventModel.retrieve(StudentEventModel.EVENT_ID + "=" + this._ddlStudentEvent.getValue());
                eventModel.gotoFirst();
                StudentEvent eventType = eventModel.getStudentEvent();
                newEvent.setStudentEvent(eventType);
                //Комментарий и студент
                newEvent.setComment(this._teEventComment.getValue());
                newEvent.setStudent(students.getStudent(i));

                sess.save(newEvent);
            }
        } catch(Exception e) {
            tran.rollback();
            throw e;
        }
    }

    /**
     * Удаляет студентов из списка
     * @param onlySelected Удалять только помеченных или всех
     * @throws DataStoreException
     */
    private void removeFromBasket(boolean onlySelected) throws DataStoreException {
        StudentBasket basket = this.getSessionManager().getStudentBasket();
        if (!onlySelected) {
            basket.Clear();
        } else {
            for (int i = 0; i < this._dsStudents.getRowCount(); i++) {
                if (this._dsStudents.isChecked(i)) {
                    basket.RemoveStudent(this._dsStudents.getStudent(i));
                }
            }
        }

        this.fillStudentsFromBasket();
    }

    /**
     * Process the page requested event
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);

        //Проверка прав и настройка уровня доступа
        if (this.isGuest()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }

        this._sbtAddOrder.setEnabled(this.isAdmin() || this.isDecanat());
        this._sbtAddEvent.setEnabled(this.isAdmin() || this.isDecanat() || this.isDepartment());
        this._sbtClearList.setEnabled(this.isAdmin() || this.isDecanat() || this.isDepartment());
        this._sbtRemoveSelected.setEnabled(this.isAdmin() || this.isDecanat() || this.isDepartment());

        this.fillStudentsFromBasket();
    }

    /**
     * Загружает в локальный источник данных "виртуальный" список студентов для отображения
     */
    private void fillStudentsFromBasket() {
        this._dsStudents.reset();
        this._dsStudents.appendRows(
            this.getSessionManager().getStudentBasket().getStudents().getRows());
    }
}
