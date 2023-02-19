package com.itm.vuz.common.controllers;

import com.salmonllc.html.events.*;
import com.salmonllc.jsp.JspController;
import com.salmonllc.jsp.JspLink;
import com.salmonllc.util.MessageLog;
import com.salmonllc.sql.DBConnection;
import com.itm.vuz.common.utils.*;
//import com.salmonllc.sql.DBConnection;

import javax.servlet.http.Cookie;

/**
 This controller is used as the ancestor for the other controllers in the example app.
 Generally, every controller will extend from this one, but the examples all extend JspController in order to keep them encapsulated.
 This controller has more functionality than is requied by the sample application so it can be copied and used as a starting point for your application's base controller.
 It implements frequently used interfaces at the base level to avoid having to implement all the methods in each controller.
 Also, it provides some basic functionality such as checking if the session or page is required and redirecting the user to the appropriate page.
 */
public class BaseController extends JspController implements SubmitListener, PageListener {

    public com.salmonllc.jsp.JspContainer _noCache;

    private boolean _redirected = true;
    private boolean _checkSessionExpired = true;
    private boolean _checkPageExpired = false;
    private boolean _checkDB = true;

    /**
     *	This method tries to get the string parameter passed into this function from the URL.  It then checks to see if that "name" parameter is of boolean type.  The default is FALSE.
     *	@param name - name of the parameter that is being looked up
     */

    public boolean getBooleanParameter(String name) {
        String s = getParameter(name);
        return s != null && (s.equals("true") || s.equals("TRUE") || s.equals("1"));
    }


    /**
     * Return the text of the request referer, if available, else null.
     * @param key - name of the cookie
     * @return java.lang.String
     */
    public Cookie getCookie(String key) {
        Cookie ret = null;

        Cookie[] cookArr = getCurrentRequest().getCookies();
        if (cookArr != null) {
            for (int i = 0; i < cookArr.length; i++) {
                if (cookArr[i].getName().equals(key)) {
                    ret = cookArr[i];
                }
                break;
            }
        }
        return ret;
    }

    /**
     *	This method tries to get the integer value from the parameter in the URL called passed into this method.  If the value of the parameter is not an integer, a -1 value will be returned.
     *	@param name - name of the parameter that is being looked up
     * 	@return int - returns the value of the parameter as an int, if the value can NOT be cast to an int -1 is returned
     */

    public int getIntParameter(String name) {
        String s = getParameter(name);
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Gets the SessionManager for this application.
     */
    public SessionManager getSessionManager() {
        return new SessionManager(getSession(), getApplicationName());
    }

    /**
     * This method
     *  - creates a SessionManager a page Listener
     *  - adds a page Listener
     *  - adds a page Listener
     * @throws Exception if anything goes wrong
     */
    public void initialize() throws Exception {
        //make this controller listen for page events
        addPageListener(this);
        //check if there is a return to link in the page. If there is, set the url
        JspLink l = (JspLink) getComponent("retToHome");
        if (l != null)
            l.setHref("HomePage.jsp");
    }

    /**
     * 	This method/event will get fired each time a page is requested by the browser before Html is generated and sent back.
     *	@param p PageEvent
     *  @throws Exception if anything goes wrong
     */
    public void pageRequested(PageEvent p) throws Exception {
        checkPageRedirect();
        if (hasPageRedirected())
            p.setContinueProcessing(false);
    }

    /**
     * 	This method/event will get fired each time a page is requested by the browser after Html is generated and sent back.
     *	@param p PageEvent
     *  @throws Exception if anything goes wrong
     */
    public void pageRequestEnd(PageEvent p) throws Exception {


    }

    /**
     * 	This method occurs each time a page is submitted before submit values are copied to components.
     *	@param p PageEvent
     */
    public void pageSubmitted(PageEvent p) {
        checkPageRedirect();
        if (hasPageRedirected())
            p.setContinueProcessing(false);
    }

    /**
     * 	This method occurs each time a page is submitted after submit values are copied to components.
     *	@param p PageEvent
     */
    public void pageSubmitEnd(PageEvent p) {

    }


    /**
     *	This method is used to signal that a submit button has been pressed, and that the user has intended
     *  to submit the descendant class/page.
     *	@param e SubmitEvent
     *  @throws Exception if anything goes wrong
     *	@return true to continue processing events on the page or false to abort
     */

    public boolean submitPerformed(SubmitEvent e) throws Exception {
        return true;
    }

    /**
     * This method will check if the page is expired or the session is expired and redirect to the appropriate page
     */
    private void checkPageRedirect() {

        try {
            _redirected = false;
            if (_checkSessionExpired)
                if (isSessionExpired()) {
                    _redirected = true;
					gotoSiteMapPage(SiteMapConstants.SESSION_EXPIRED);
                    return;
                }

            if (_checkPageExpired)
                if (isExpired()) {
                    _redirected = true;
					gotoSiteMapPage(SiteMapConstants.PAGE_EXPIRED);
                    return;
                }

            if (_checkDB) {
                DBConnection conn = null;
                try {
                    conn = DBConnection.getConnection(getApplicationName());
                }
                catch (Exception e) {
                    MessageLog.writeErrorMessage("chckDB", e, this);
                    _redirected = true;
					gotoSiteMapPage(SiteMapConstants.DB_CONNECT_ERROR);
                }
                finally {
                    if (conn != null)
                        conn.freeConnection();
                }
            }

        } catch (Exception e) {
            MessageLog.writeErrorMessage("checkPageRedirect()", e, this);
        }
    }

    /**
     * Returns true if either the page or the session is expired and the page has been redirected to another page indicating that
     */
    public boolean hasPageRedirected() {
        return _redirected;
    }

    /**
     * Tells the page whether it should check whether the page is expired on each request
     */
    public void setCheckPageExpired(boolean check) {
           _checkPageExpired = check;
    }
    /**
     * Tells the page whether it should check whether the session is expired on each request
     */
    public void setCheckSessionExpired(boolean check) {
           _checkSessionExpired = check;
    }

    /**
     * If you want to have the browser cache this page instead of reloading it each time call this method with a true argument.
     */
    public void setNoCache(boolean noCache) {
        _noCache.setVisible(noCache);
    }

}
