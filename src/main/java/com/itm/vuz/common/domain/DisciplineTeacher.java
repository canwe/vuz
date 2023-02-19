package com.itm.vuz.common.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DisciplineTeacher implements Serializable {

    /** identifier field */
    private Long disciplineTeacherId;

    /** persistent field */
    private Discipline discipline;

    /** persistent field */
    private Teacher teacher;

    /** full constructor */
    public DisciplineTeacher(Discipline discipline, Teacher teacher) {
        this.discipline = discipline;
        this.teacher = teacher;
    }

    /** default constructor */
    public DisciplineTeacher() {
    }

    public Long getDisciplineTeacherId() {
        return this.disciplineTeacherId;
    }

    public void setDisciplineTeacherId(Long disciplineTeacherId) {
        this.disciplineTeacherId = disciplineTeacherId;
    }

    public Discipline getDiscipline() {
        return this.discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("disciplineTeacherId", getDisciplineTeacherId())
            .toString();
    }

}
