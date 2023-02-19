package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Country implements Serializable {

    /** identifier field */
    private Long countryId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Set students;

    /** persistent field */
    private Set regions;

    /** full constructor */
    public Country(String name, Set students, Set regions) {
        this.name = name;
        this.students = students;
        this.regions = regions;
    }

    /** default constructor */
    public Country() {
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getStudents() {
        return this.students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    public Set getRegions() {
        return this.regions;
    }

    public void setRegions(Set regions) {
        this.regions = regions;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("countryId", getCountryId())
            .toString();
    }

}
