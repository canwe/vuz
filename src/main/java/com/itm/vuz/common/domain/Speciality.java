package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Speciality implements Serializable {

    /** identifier field */
    private Long specialityId;

    /** persistent field */
    private String number;

    /** persistent field */
    private String name;

    /** persistent field */
    private String shortname;

    /** persistent field */
    private Faculty faculty;

    /** persistent field */
    private Set studentGroups;

    /** persistent field */
    private Set educationPlans;

    /** full constructor */
    public Speciality(String number, String name, String shortname, Faculty faculty, Set studentGroups, Set educationPlans) {
        this.number = number;
        this.name = name;
        this.shortname = shortname;
        this.faculty = faculty;
        this.studentGroups = studentGroups;
        this.educationPlans = educationPlans;
    }

    /** default constructor */
    public Speciality() {
    }

    public Long getSpecialityId() {
        return this.specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return this.shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set getStudentGroups() {
        return this.studentGroups;
    }

    public void setStudentGroups(Set studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set getEducationPlans() {
        return this.educationPlans;
    }

    public void setEducationPlans(Set educationPlans) {
        this.educationPlans = educationPlans;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("specialityId", getSpecialityId())
            .toString();
    }

}
