package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Discipline implements Serializable {

    /** identifier field */
    private Long disciplineId;

    /** persistent field */
    private short hours;

    /** persistent field */
    private short audiencehours;

    /** persistent field */
    private short independenthours;

    /** persistent field */
    private boolean semester;

    /** persistent field */
    private short course;

    /** persistent field */
    private short year;

    /** persistent field */
    private byte checkingForm;

    /** persistent field */
    private EducationPlan educationPlan;

    /** persistent field */
    private BaseDiscipline baseDiscipline;

    /** persistent field */
    private Set disciplineTeachers;

    /** persistent field */
    private Set examMarks;

    /** persistent field */
    private Set marks;

    /** persistent field */
    private Set teachers;

    /** full constructor */
    public Discipline(short hours, boolean semester, short course, short year, byte checkingForm, EducationPlan educationPlan, BaseDiscipline baseDiscipline, Set disciplineTeachers, Set examMarks, Set marks, Set teachers, short independenthours, short audiencehours) {
        this.hours = hours;
        this.independenthours=independenthours;
        this.audiencehours=audiencehours;
        this.semester = semester;
        this.course = course;
        this.year = year;
        this.checkingForm = checkingForm;
        this.educationPlan = educationPlan;
        this.baseDiscipline = baseDiscipline;
        this.disciplineTeachers = disciplineTeachers;
        this.examMarks = examMarks;
        this.marks = marks;
        this.teachers = teachers;
    }

    /** default constructor */
    public Discipline() {
    }

    public Long getDisciplineId() {
        return this.disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public short getHours() {
        return this.hours;
    }

    public void setHours(short hours) {
        this.hours = hours;
    }

    public short getAudiencehours() {
        return this.audiencehours;
    }

    public void setAudiencehours(short audiencehours) {
        this.audiencehours = audiencehours;
    }

    public short getIndependenthours() {
        return this.independenthours;
    }

    public void setIndependenthours(short independenthours) {
        this.independenthours = independenthours;
    }

    public boolean isSemester() {
        return this.semester;
    }

    public void setSemester(boolean semester) {
        this.semester = semester;
    }

    public short getCourse() {
        return this.course;
    }

    public void setCourse(short course) {
        this.course = course;
    }

    public short getYear() {
        return this.year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getCheckingForm() {
        return this.checkingForm;
    }

    public void setCheckingForm(byte checkingForm) {
        this.checkingForm = checkingForm;
    }

    public EducationPlan getEducationPlan() {
        return this.educationPlan;
    }

    public void setEducationPlan(EducationPlan educationPlan) {
        this.educationPlan = educationPlan;
    }

    public BaseDiscipline getBaseDiscipline() {
        return this.baseDiscipline;
    }

    public void setBaseDiscipline(BaseDiscipline baseDiscipline) {
        this.baseDiscipline = baseDiscipline;
    }

    public Set getDisciplineTeachers() {
        return this.disciplineTeachers;
    }

    public void setDisciplineTeachers(Set disciplineTeachers) {
        this.disciplineTeachers = disciplineTeachers;
    }

    public Set getExamMarks() {
        return this.examMarks;
    }

    public void setExamMarks(Set examMarks) {
        this.examMarks = examMarks;
    }

    public Set getMarks() {
        return this.marks;
    }

    public void setMarks(Set marks) {
        this.marks = marks;
    }

    public Set getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("disciplineId", getDisciplineId())
            .toString();
    }

}
