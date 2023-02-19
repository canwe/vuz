//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.domain.StudentGroup;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.itm.vuz.personalcard.models.StudentModel;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.html.HtmlDropDownList;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * StudentsToGroupsController: a SOFIA generated controller
 */
public class StudentsToGroupsController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public HtmlDropDownList _ddlLanguage;
    public HtmlDropDownList _ddlStudentGroups;
    public com.salmonllc.html.HtmlSubmitButton _sbtAddToGroup;
    public com.salmonllc.html.HtmlSubmitButton _sbtRemoveFromGroup;
    public com.salmonllc.html.HtmlSubmitButton _sbtSearchStudents;
    public com.salmonllc.html.HtmlSubmitButton _sbtShowGroupStudents;

//DataSources
    public StudentModel _dsFreeStudent;
    public StudentModel _dsStudentInGroup;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        this.addPageListener(this);
        this._sbtRemoveFromGroup.addSubmitListener(this);
        this._sbtSearchStudents.addSubmitListener(this);
        this._sbtAddToGroup.addSubmitListener(this);
        this._sbtShowGroupStudents.addSubmitListener(this);
    }

    /**
     * Вспомогательный метод для извлечения выбранного идентификатора
     * из списка выбора. Если выбранного значения нет, то возвращается null.
     * TODO: Возможно, следует создать класс-хелпер и переместить метод туда?
     *
     * @param list Список
     * @return Идентификатор выбранного элемента
     */
    private Long getSelectedID(HtmlDropDownList list) {
        if (list.getSelectedIndex() >= 0) {
            return new Long(list.getValue());
        }
        return null;
    }

    /**
     * Возвращает идентификатор языка, выбранный пользователем из списка
     *
     * @return Идентификатор языка
     */
    private Long getSelectedLanguageID() {
        return this.getSelectedID(this._ddlLanguage);
    }

    /**
     * Возвращает идентификатор группы студентов, выбранный пользователем из списка
     *
     * @return Идентификатор группы студентов
     */
    private Long getSelectedGroupID() {
        return this.getSelectedID(this._ddlStudentGroups);
    }

    /**
     * Загружает список студентов, говорящих на заданном языке и без группы
     *
     * @throws DataStoreException
     */
    private void findStudentsByLanguage() throws DataStoreException {
        String criteria =
            StudentModel.STUDENT_GROUP_ID + " is null and " +
            StudentModel.LANGUALE_ID + " = " + this.getSelectedLanguageID().toString();
        this._dsFreeStudent.retrieve(criteria);
    }

    /**
     * Загружает список студентов для выбранной группы
     *
     * @throws DataStoreException
     */
    private void loadStudentsForGroup() throws DataStoreException {
        String criteria =
            StudentModel.STUDENT_GROUP_ID + " = " +
            this.getSelectedGroupID().toString();
        this._dsStudentInGroup.retrieve(criteria);
    }

    /**
     * Добавляет студентов в группу
     *
     * @throws Exception
     */
    private void addSelectedStudentsToGroup() throws Exception {
        Long groupID = this.getSelectedGroupID();

        Session sess = HibernateSessionFactory.getSession();
        Transaction tran = sess.beginTransaction();
        try {
            StudentGroup group = (StudentGroup) sess.get(StudentGroup.class, groupID);
            for (int i = 0; i < this._dsFreeStudent.getRowCount(); i++) {
                if (this._dsFreeStudent.isChecked(i)) {
                    Student s = this._dsFreeStudent.getStudent(i);
                    s.setStudentGroup(group);
                    sess.saveOrUpdate(s);
                }
            }
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            throw e;
        }
    }

    /**
     * Удаляет студентов из группы
     *
     * @throws Exception
     */
    private void removeSelectedStudentsFromGroup() throws Exception {
        Session sess = HibernateSessionFactory.getSession();
        Transaction tran = sess.beginTransaction();
        try {
            for (int i = 0; i < this._dsStudentInGroup.getRowCount(); i++) {
                Student s = this._dsStudentInGroup.getStudent(i);
                s.setStudentGroup(null);
                sess.saveOrUpdate(s);
            }
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            throw e;
        }
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        try {
            //Диспетчеризация событий (+ мелкая логика типа обновления)
            if (event.getComponent() == this._sbtSearchStudents) {
                this.findStudentsByLanguage();
            }
            if (event.getComponent() == this._sbtShowGroupStudents) {
                this.loadStudentsForGroup();
            }
            if (event.getComponent() == this._sbtAddToGroup) {
                this.addSelectedStudentsToGroup();
                this.findStudentsByLanguage();
                this.loadStudentsForGroup();
            }
            if (event.getComponent() == this._sbtRemoveFromGroup) {
                this.removeSelectedStudentsFromGroup();
                this.findStudentsByLanguage();
                this.loadStudentsForGroup();
            }
        }
        catch (Exception e) {
            MessageLog.writeErrorMessage(e, this);
        }
        return true;
    }

    /**
     * Process the page requested event
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);
        //Проверка прав
        if (this.isGuest() || this.isDepartment()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }
    }
}
