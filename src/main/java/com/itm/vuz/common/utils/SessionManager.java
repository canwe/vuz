package com.itm.vuz.common.utils;

//The Salmon Open Framework for Internet Applications (SOFIA)
//Copyright (C) 1999 - 2002, Salmon LLC
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License version 2
//as published by the Free Software Foundation;
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//For more information please visit http://www.salmonllc.com


import com.itm.vuz.personalcard.controllers.StudentBasket;
import com.itm.vuz.common.controllers.LoginContext;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Used to manage values put on the session. This class mainly adds the APP_NAME to all keys added with it.
 * The class is not actually used in the application, but is here in case you want to use the sample application
 * as a starting point for your own application. Generally it isn't good practice to write to the session directly.
 * Instead implement get/set method in this class that do it for you. Then in your application always access the session
 * via the get and set methods in this class. It makes it much easier to trap errors related to storing items on the session.
 */
public class SessionManager {


    private HttpSession _sess;
    public  String _appName;


    public static String LOGIN_CONTEXT_KEY = "LoginContext";
    public static String LINKS_HISTORY = "LinksHistory";
    /**
     * SessionManager constructor comment.
     */
    public SessionManager(HttpSession sess, String appName) {
        _sess = sess;
        _appName = appName;
        init();
    }

    /**
     *	This method returns the current time with precision into milliseconds.
     *	@return java.sql.Timestamp current time
     */

    public java.sql.Timestamp getCurrentTime() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * Gets a value off the session. Automatically using the APP_NAME in the key value.
     * @return java.lang.Object
     * @param name java.lang.String
     */
    public Object getValue(String name) {
        return _sess.getAttribute("SessionManager_" + _appName + "_" + name);
    }

    private void init() {
        String init = _appName + "_isInit";
        if (getValue(init) == null) {
            putValue(init, new Boolean(true));
        }
        //If you want to load any values from the database or property files into the session,
        //this is the place to do it.
    }
    /**
     * Puts a value on the session. Automatically using the APP_NAME in the key value.
     * @param name java.lang.String
     * @param value java.lang.Object
     */
    public void putValue(String name, Object value) {
        _sess.setAttribute("SessionManager_" + _appName + "_" + name, value);
    }


    /**
     * Removes a value from the session. Automatically using the APP_NAME in the key value.
     */
    public void removeValue(String name) {
        _sess.removeAttribute("SessionManager_" + _appName + "_" + name);
    }

    /**
     * @return Возвращает экземпляр виртуального списка студенто
     * (для создания документов сразу для нескольких студентов)
     */
    public synchronized StudentBasket getStudentBasket() {
        StudentBasket basket = (StudentBasket)this.getValue("StudentBasket");
        if (basket == null) {
            basket = new StudentBasket();
            this.putValue("StudentBasket", basket);
        }
        return basket;
    }

    /**
     *  returns LoginContext
     */
    public synchronized LoginContext getLoginContext(){
        return (LoginContext)this.getValue(LOGIN_CONTEXT_KEY);
    }

    /**
     *  returns list of links history
     */
    public synchronized List getLinksHistory(){
        return (List)this.getValue(LINKS_HISTORY);
    }


}
