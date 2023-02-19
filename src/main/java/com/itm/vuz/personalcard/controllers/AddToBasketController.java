//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreException;
import org.hibernate.HibernateException;


/**
 * AddToBasketController: a SOFIA generated controller
 */
public class AddToBasketController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _teErrorMsg;
    public com.salmonllc.html.HtmlText _teInfoMsg;
    public com.salmonllc.html.HtmlText _teCount;
    public com.salmonllc.html.HtmlText _teCountCaption;
    public com.salmonllc.jsp.JspContainer _noCache;
    public com.salmonllc.jsp.JspForm _pageForm;
    public com.salmonllc.jsp.JspLink _hlClose;
    public com.salmonllc.jsp.JspLink _hlShowBasket;

    private String _errorMsg;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
        this.addPageListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);

        //Проверка прав пользователя
        if (this.isGuest()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }

        //Добавление студента в список. Производится путем передачи id
        //студента в строке запроса
        int studentId = this.getIntParameter("studentId", -1);
        if (studentId != -1) {
            //Пробуем загрузить студента с заданным идентификатором
            Student s = null;
            try {
                s = (Student) HibernateSessionFactory.getSession().get(Student.class, new Long(studentId));
            }
            catch (HibernateException he) {
                //не удалось загрузить студента с заданным id
                s = null;
            }
            if (s != null) {
                //Студент с заданным id найден. Помещаем его в виртуальный список
                try {
                    this.getSessionManager().getStudentBasket().AddStudent(s);
                    this._errorMsg = null;
                }
                catch (DataStoreException dse) {
                    //this._errorMsg = "При добавлении студента в список произошла ошибка.";
                    throw dse;
                }
            } else {
                this._errorMsg = "Студент с идентификатором " + Integer.toString(studentId) + " не найден.";
            }
        } else {
            this._errorMsg = "Не задан идентификатор студента";
        }

        //Отображаем результаты работы
        ShowAddInfo();
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) {
    }

    private void ShowAddInfo() {
        //Устанавливаем видимость сообщений об успешности операции
        // или сообщение об ошибке
        this._teErrorMsg.setVisible(this._errorMsg != null);
        this._teInfoMsg.setVisible(this._errorMsg == null);
        if (this._errorMsg != null) {
            this._teErrorMsg.setText(this._errorMsg);
        }
        //Отображаем кол-во студентов в списке
        int studentsInBasket = this.getSessionManager().getStudentBasket().getCount();
        this._teCount.setText(Integer.toString(studentsInBasket));
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
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event){
}

}
