package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Language implements Serializable {

    /** identifier field */
    private Long languageId;

    /** nullable persistent field */
    private String name;

    /** persistent field */
    private Set students;

    /** full constructor */
    public Language(String name, Set students) {
        this.name = name;
        this.students = students;
    }

    /** default constructor */
    public Language() {
    }

    /** minimal constructor */
    public Language(Set students) {
        this.students = students;
    }

    public Long getLanguageId() {
        return this.languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
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
            .append("languageId", getLanguageId())
            .toString();
    }

}
