package com.itm.vuz.common.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FacultyGroupCode implements Serializable {

    /** identifier field */
    private Integer facultyGroupCodeId;

    /** nullable persistent field */
    private String comment;

    /** persistent field */
    private Faculty faculty;

    /** full constructor */
    public FacultyGroupCode(String comment, Faculty faculty) {
        this.comment = comment;
        this.faculty = faculty;
    }

    /** default constructor */
    public FacultyGroupCode() {
    }

    /** minimal constructor */
    public FacultyGroupCode(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getFacultyGroupCodeId() {
        return this.facultyGroupCodeId;
    }

    public void setFacultyGroupCodeId(Integer facultyGroupCodeId) {
        this.facultyGroupCodeId = facultyGroupCodeId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("facultyGroupCodeId", getFacultyGroupCodeId())
            .toString();
    }

}
