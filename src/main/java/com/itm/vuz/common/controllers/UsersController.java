package com.itm.vuz.common.controllers;

import com.google.gson.Gson;
import com.itm.vuz.common.domain.User;
import com.itm.vuz.common.domain.UserRole;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.jsp.JspServlet;
import com.salmonllc.util.MessageLog;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JspServlet.setUpApplicationContext(getServletContext(),req);
        Gson gson = new Gson();
        String userJson = new String(req.getInputStream().readAllBytes());
        User user = gson.fromJson(userJson, User.class);
        MessageLog.writeInfoMessage("read user " + userJson, this);

        try {
            Session session = HibernateSessionFactory.getSession();
            UserRole userRole = user.getUserRole();
            if (userRole != null) {
                session.saveOrUpdate(userRole);
            }
            user.setUserRole(userRole);
            session.saveOrUpdate(user);
            session.flush();
        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error", e, this);
        }
    }
}
