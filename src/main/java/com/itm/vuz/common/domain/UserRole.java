package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UserRole implements Serializable {

    /** identifier field */
    private Long userRoleId;

    /** nullable persistent field */
    private String name;

    /** persistent field */
    private Set users;

    /** full constructor */
    public UserRole(String name, Set users) {
        this.name = name;
        this.users = users;
    }

    /** default constructor */
    public UserRole() {
    }

    /** minimal constructor */
    public UserRole(Set users) {
        this.users = users;
    }

    public Long getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userRoleId", getUserRoleId())
            .toString();
    }

}
