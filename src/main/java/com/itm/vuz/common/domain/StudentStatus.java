package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StudentStatus implements Serializable {

    /** identifier field */
    private Long studentStatusId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Set students;

    /** full constructor */
    public StudentStatus(String name, Set students) {
        this.name = name;
        this.students = students;
    }

    /** default constructor */
    public StudentStatus() {
    }

    public Long getStudentStatusId() {
        return this.studentStatusId;
    }

    public void setStudentStatusId(Long studentStatusId) {
        this.studentStatusId = studentStatusId;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("studentStatusId", getStudentStatusId())
            .toString();
    }

}
