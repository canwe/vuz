package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class User implements Serializable {

    /** identifier field */
    private Integer userId;

    /** nullable persistent field */
    private String login;

    /** nullable persistent field */
    private String password;

    /** persistent field */
    private Personal personal;

    /** persistent field */
    private UserRole userRole;

    /** persistent field */
    private Set studentHistories;

    /** full constructor */
    public User(String login, String password, Personal personal, UserRole userRole, Set studentHistories) {
        this.login = login;
        this.password = password;
        this.personal = personal;
        this.userRole = userRole;
        this.studentHistories = studentHistories;
    }

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(Personal personal, UserRole userRole, Set studentHistories) {
        this.personal = personal;
        this.userRole = userRole;
        this.studentHistories = studentHistories;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Personal getPersonal() {
        return this.personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set getStudentHistories() {
        return this.studentHistories;
    }

    public void setStudentHistories(Set studentHistories) {
        this.studentHistories = studentHistories;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .toString();
    }

}
