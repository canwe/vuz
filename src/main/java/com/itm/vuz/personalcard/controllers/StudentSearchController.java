//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.domain.Student;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;


/**
 * StudentSearchController: a SOFIA generated controller
 */
public class StudentSearchController extends BaseVUZController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlSubmitButton _sbtAddToStudentList;

//DataSources
    public com.itm.vuz.personalcard.models.StudentModel _dsStudent;
    public com.itm.vuz.personalcard.models.StudentQBE _dsStudentQBE;


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        this.addPageListener(this);
        this._sbtAddToStudentList.addSubmitListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        if (event.getComponent() == this._sbtAddToStudentList) {
            try {
                this.addSelectedStudentsToBasket();
            } catch (DataStoreException e) {
                MessageLog.writeErrorMessage(e, this);
            }
        }
        return true;
    }

    private void addSelectedStudentsToBasket() throws DataStoreException {
        StudentBasket basket = this.getSessionManager().getStudentBasket();
        for (int i = 0; i < this._dsStudent.getRowCount(); i++) {
            if (this._dsStudent.getShort(i, "Check") == 1) {
                Student s = (Student) this._dsStudent.getBean(i);
                basket.AddStudent(s);
            }
        }
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);
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
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event){
}

}
