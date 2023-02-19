//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Personal;
import com.itm.vuz.common.domain.UserRole;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * UserController: a SOFIA generated controller
 */
public class UserController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _personalSelect;
    public com.salmonllc.html.HtmlDropDownList _userroleSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _loginCAP;
    public com.salmonllc.html.HtmlText _logins;
    public com.salmonllc.html.HtmlText _loginsCAP;
    public com.salmonllc.html.HtmlText _passwordCAP;
    public com.salmonllc.html.HtmlText _passwords;
    public com.salmonllc.html.HtmlText _passwordsCAP;
    public com.salmonllc.html.HtmlText _personalCAP;
    public com.salmonllc.html.HtmlText _personals1;
    public com.salmonllc.html.HtmlText _personals2;
    public com.salmonllc.html.HtmlText _personals;
    public com.salmonllc.html.HtmlText _personalsCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _userroleCAP;
    public com.salmonllc.html.HtmlText _userroles;
    public com.salmonllc.html.HtmlText _userrolesCAP;
    public com.salmonllc.html.HtmlTextEdit _loginText;
    //public com.salmonllc.html.HtmlTextEdit _passwordText;
    public com.salmonllc.html.HtmlPasswordEdit _passwordText;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow3;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernatePersonalModel _hibernatePersonalModel;
    public com.itm.vuz.vocabularies.models.HibernateUserModel _hibernateUserDatasource;
    public com.itm.vuz.vocabularies.models.HibernateUserRoleModel _hibernateUserRoleModel;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";
    public HtmlHiddenField _refreshFlag;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
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

            initializePersonalComboBox();
            initializeUserRoleComboBox();
            if (_refreshFlag.getValue().equals("true")) {
                try {
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
            }

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializePersonalComboBox() throws Exception {
        _personalSelect.resetOptions();
        _hibernatePersonalModel.reset();
        _hibernatePersonalModel.retrieve();
        _personalSelect.addOption("", "");
        for (int i = 0; i < _hibernatePersonalModel.getRowCount(); i++) {
            Personal personal = (Personal)
                    _hibernatePersonalModel.getDataRow(i).getBean();
            if (personal != null) {
                _personalSelect.addOption(personal.getPersonalId().toString(),
                        personal.getFamilyName() + " " + personal.getName() + " " + personal.getPatronymic());
            }
        }
    }

    private void initializeUserRoleComboBox() throws Exception {
        _userroleSelect.resetOptions();
        _hibernateUserRoleModel.reset();
        _hibernateUserRoleModel.retrieve();
        for (int i = 0; i < _hibernateUserRoleModel.getRowCount(); i++) {
            UserRole userrole = (UserRole)
                    _hibernateUserRoleModel.getDataRow(i).getBean();
            if (userrole != null) {
                _userroleSelect.addOption(userrole.getUserRoleId().toString(),
                        userrole.getName());
            }
        }
    }

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