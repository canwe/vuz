package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Hospice implements Serializable {

    /** identifier field */
    private Integer hospiceId;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String street;

    /** nullable persistent field */
    private Short house;

    /** nullable persistent field */
    private Short building;

    /** persistent field */
    private Set students;

    /** full constructor */
    public Hospice(String name, String street, Short house, Short building, Set students) {
        this.name = name;
        this.street = street;
        this.house = house;
        this.building = building;
        this.students = students;
    }

    /** default constructor */
    public Hospice() {
    }

    /** minimal constructor */
    public Hospice(Set students) {
        this.students = students;
    }

    public Integer getHospiceId() {
        return this.hospiceId;
    }

    public void setHospiceId(Integer hospiceId) {
        this.hospiceId = hospiceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getHouse() {
        return this.house;
    }

    public void setHouse(Short house) {
        this.house = house;
    }

    public Short getBuilding() {
        return this.building;
    }

    public void setBuilding(Short building) {
        this.building = building;
    }

    public Set getStudents() {
        return this.students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("hospiceId", getHospiceId())
            .toString();
    }

}
