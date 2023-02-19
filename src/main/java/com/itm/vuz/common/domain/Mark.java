package com.itm.vuz.common.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Mark implements Serializable {

    /** identifier field */
    private Long markId;

    /** nullable persistent field */
    private Byte value;

    /** persistent field */
    private Discipline discipline;

    /** persistent field */
    private StudentGroup studentGroup;

    /** persistent field */
    private ExamList examList;

    /** persistent field */
    private Student student;

    /** full constructor */
    public Mark(Byte value, Discipline discipline, StudentGroup studentGroup, ExamList examList, Student student) {
        this.value = value;
        this.discipline = discipline;
        this.studentGroup = studentGroup;
        this.examList = examList;
        this.student = student;
    }

    /** default constructor */
    public Mark() {
    }

    /** minimal constructor */
    public Mark(Discipline discipline, StudentGroup studentGroup, ExamList examList, Student student) {
        this.discipline = discipline;
        this.studentGroup = studentGroup;
        this.examList = examList;
        this.student = student;
    }

    public Long getMarkId() {
        return this.markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Byte getValue() {
        return this.value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public Discipline getDiscipline() {
        return this.discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public StudentGroup getStudentGroup() {
        return this.studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public ExamList getExamList() {
        return this.examList;
    }

    public void setExamList(ExamList examList) {
        this.examList = examList;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("markId", getMarkId())
            .toString();
    }

}
