package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StudentGroup implements Serializable {

    /** identifier field */
    private Long studentGroupId;

    /** persistent field */
    private byte course;

    /** persistent field */
    private boolean educationDuration;

    /** nullable persistent field */
    private String number;

    /** persistent field */
    private Speciality speciality;

    /** persistent field */
    private Personal personal;

    /** persistent field */
    private Set examMarks;

    /** persistent field */
    private Set notes;

    /** persistent field */
    private Set marks;

    /** persistent field */
    private Set students;

    /** full constructor */
    public StudentGroup(byte course, boolean educationDuration, String number, Speciality speciality, Personal personal, Set examMarks, Set notes, Set marks, Set students) {
        this.course = course;
        this.educationDuration = educationDuration;
        this.number = number;
        this.speciality = speciality;
        this.personal = personal;
        this.examMarks = examMarks;
        this.notes = notes;
        this.marks = marks;
        this.students = students;
    }

    /** default constructor */
    public StudentGroup() {
    }

    /** minimal constructor */
    public StudentGroup(byte course, boolean educationDuration, Speciality speciality, Personal personal, Set examMarks, Set notes, Set marks, Set students) {
        this.course = course;
        this.educationDuration = educationDuration;
        this.speciality = speciality;
        this.personal = personal;
        this.examMarks = examMarks;
        this.notes = notes;
        this.marks = marks;
        this.students = students;
    }

    public Long getStudentGroupId() {
        return this.studentGroupId;
    }

    public void setStudentGroupId(Long studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public byte getCourse() {
        return this.course;
    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public boolean isEducationDuration() {
        return this.educationDuration;
    }

    public void setEducationDuration(boolean educationDuration) {
        this.educationDuration = educationDuration;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Personal getPersonal() {
        return this.personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Set getExamMarks() {
        return this.examMarks;
    }

    public void setExamMarks(Set examMarks) {
        this.examMarks = examMarks;
    }

    public Set getNotes() {
        return this.notes;
    }

    public void setNotes(Set notes) {
        this.notes = notes;
    }

    public Set getMarks() {
        return this.marks;
    }

    public void setMarks(Set marks) {
        this.marks = marks;
    }

    public Set getStudents() {
        return this.students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("studentGroupId", getStudentGroupId())
            .toString();
    }

}
