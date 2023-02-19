package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Subfaculty implements Serializable {

    /** identifier field */
    private Long subfacultyId;

    /** persistent field */
    private String name;

    /** persistent field */
    private String subfacultyNumber;

    /** persistent field */
    private Faculty faculty;

    /** persistent field */
    private Set baseDisciplines;

    /** persistent field */
    private Set teachers;

    /** full constructor */
    public Subfaculty(String name, String subfacultyNumber, Faculty faculty, Set baseDisciplines, Set teachers) {
        this.name = name;
        this.subfacultyNumber = subfacultyNumber;
        this.faculty = faculty;
        this.baseDisciplines = baseDisciplines;
        this.teachers = teachers;
    }

    /** default constructor */
    public Subfaculty() {
    }

    public Long getSubfacultyId() {
        return this.subfacultyId;
    }

    public void setSubfacultyId(Long subfacultyId) {
        this.subfacultyId = subfacultyId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubfacultyNumber() {
        return this.subfacultyNumber;
    }

    public void setSubfacultyNumber(String subfacultyNumber) {
        this.subfacultyNumber = subfacultyNumber;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set getBaseDisciplines() {
        return this.baseDisciplines;
    }

    public void setBaseDisciplines(Set baseDisciplines) {
        this.baseDisciplines = baseDisciplines;
    }

    public Set getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("subfacultyId", getSubfacultyId())
            .toString();
    }

}
