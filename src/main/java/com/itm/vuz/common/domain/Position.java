package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Position implements Serializable {

    /** identifier field */
    private Long positionId;

    /** nullable persistent field */
    private String name;

    /** persistent field */
    private Set personals;

    /** full constructor */
    public Position(String name, Set personals) {
        this.name = name;
        this.personals = personals;
    }

    /** default constructor */
    public Position() {
    }

    /** minimal constructor */
    public Position(Set personals) {
        this.personals = personals;
    }

    public Long getPositionId() {
        return this.positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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
            .append("positionId", getPositionId())
            .toString();
    }

}
