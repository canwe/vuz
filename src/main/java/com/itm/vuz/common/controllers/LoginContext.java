package com.itm.vuz.common.controllers;

import com.itm.vuz.common.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 23.07.2006
 * Time: 13:47:28
 */

/**
 * holds user credentials
 */
public class LoginContext {

    public LoginContext(User user){
        login = user.getLogin();
        password = user.getPassword();
        userId = user.getUserId();

        if(user.getPersonal()!=null){
            name = user.getPersonal().getName();
            familyName = user.getPersonal().getFamilyName();
            patronymic = user.getPersonal().getPatronymic();
            personalId = user.getPersonal().getPersonalId();
        }

        roleName = user.getUserRole().getName();
        roleId = user.getUserRole().getUserRoleId();
    }

    public String getLogin() {
        return login;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    String getPassword(){
        return password;
    }


    // user
    private String login;
    private transient String password;
    private Integer userId;

    // personal
    private String name;
    private String familyName;
    private String patronymic;
    private Long personalId;

    //role
    private String roleName;
    private Long roleId;
}
