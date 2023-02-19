package com.itm.vuz.common.controllers;

import com.itm.vuz.common.domain.User;
import com.itm.vuz.common.models.HibernateUsersModel;
import com.itm.vuz.common.utils.SessionManager;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;

import java.sql.SQLException;


/**
 * LoginController: a SOFIA generated controller
 */
public class LoginController extends BaseController implements SubmitListener, PageListener {

    public static final String MODE_KEY = "mode";
    public static final String ENTER_LOGIN = "Введите имя пользователя";
    public static final String INVALID_LOGIN = "Неверный пароль или имя пользователя";

    //Visual Components
    public com.salmonllc.html.HtmlPasswordEdit _password;
    public com.salmonllc.html.HtmlSubmitButton _ok;
    public com.salmonllc.html.HtmlTextEdit _login;
    public com.salmonllc.jsp.JspForm _pageForm;

    public HibernateUsersModel _usersModel;
    public com.salmonllc.sql.QBEBuilder _loginQBE;

    public com.salmonllc.html.HtmlValidatorText _validator;

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        addPageListener(this);
        _ok.addSubmitListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        if (event.getSource() == _ok) {
            // try to sign in
            try {
                User user = null;
                // check login size
                if (_login.getValue() == null || _login.getValue().trim().length() == 0) {
                    _validator.setErrorMessage(ENTER_LOGIN, _login);
                    return true;
                }
                //check password size / accept empty password
                if (_password.getValue() == null) {
                    _password.setValue("");
                }

                if ((user = tryLogin()) != null && !_password.getValue().equals("")) {
                    putLogin2Session(user);
                    this.gotoSiteMapPage("HomePage", "");
                } else {
                    _validator.setErrorMessage(INVALID_LOGIN);
                    return true;
                }
            } catch (Exception e) {
                MessageLog.writeErrorMessage(e, this);
            }

        }
        return true;
    }

    private void putLogin2Session(User user) {
        getSessionManager().putValue(SessionManager.LOGIN_CONTEXT_KEY, new LoginContext(user));
    }

    private User tryLogin() throws SQLException, DataStoreException {

        User user = null;
        _loginQBE.retrieve(_usersModel);
        if (_usersModel.getRowCount() > 0) {
            user = (User) _usersModel.getBean(0);
        }
        return user;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) {
        if (getParameter(MODE_KEY) != null && getParameter(MODE_KEY).equals("logout")) {
            MessageLog.writeDebugMessage("logout", this);
            getSessionManager().removeValue(SessionManager.LOGIN_CONTEXT_KEY);
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

}