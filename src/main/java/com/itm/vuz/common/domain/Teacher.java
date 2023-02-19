package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Teacher implements Serializable {

    /** identifier field */
    private Long teacherId;

    /** persistent field */
    private Personal personal;

    /** persistent field */
    private Subfaculty subfaculty;

    /** persistent field */
    private Set disciplineTeachers;

    /** persistent field */
    private Set disciplines;

    /** full constructor */
    public Teacher(Personal personal, Subfaculty subfaculty, Set disciplineTeachers, Set disciplines) {
        this.personal = personal;
        this.subfaculty = subfaculty;
        this.disciplineTeachers = disciplineTeachers;
        this.disciplines = disciplines;
    }

    /** default constructor */
    public Teacher() {
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Personal getPersonal() {
        return this.personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Subfaculty getSubfaculty() {
        return this.subfaculty;
    }

    public void setSubfaculty(Subfaculty subfaculty) {
        this.subfaculty = subfaculty;
    }

    public Set getDisciplineTeachers() {
        return this.disciplineTeachers;
    }

    public void setDisciplineTeachers(Set disciplineTeachers) {
        this.disciplineTeachers = disciplineTeachers;
    }

    public Set getDisciplines() {
        return this.disciplines;
    }

    public void setDisciplines(Set disciplines) {
        this.disciplines = disciplines;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("teacherId", getTeacherId())
            .toString();
    }

}
