package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Departament implements Serializable {

    /** identifier field */
    private Long departmanentId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Set personals;

    /** full constructor */
    public Departament(String name, Set personals) {
        this.name = name;
        this.personals = personals;
    }

    /** default constructor */
    public Departament() {
    }

    public Long getDepartmanentId() {
        return this.departmanentId;
    }

    public void setDepartmanentId(Long departmanentId) {
        this.departmanentId = departmanentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getPersonals() {
        return this.personals;
    }

    public void setPersonals(Set personals) {
        this.personals = personals;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("departmanentId", getDepartmanentId())
            .toString();
    }

}
