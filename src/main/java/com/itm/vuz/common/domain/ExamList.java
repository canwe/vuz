package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ExamList implements Serializable {

    /** identifier field */
    private Long examListId;

    /** nullable persistent field */
    private String number;

    /** nullable persistent field */
    private Byte type;

    /** persistent field */
    private Date dateCreation;

    /** persistent field */
    private Set examMarks;

    /** persistent field */
    private Set marks;

    /** full constructor */
    public ExamList(String number, Byte type, Date dateCreation, Set examMarks, Set marks) {
        this.number = number;
        this.type = type;
        this.dateCreation = dateCreation;
        this.examMarks = examMarks;
        this.marks = marks;
    }

    /** default constructor */
    public ExamList() {
    }

    /** minimal constructor */
    public ExamList(Date dateCreation, Set examMarks, Set marks) {
        this.dateCreation = dateCreation;
        this.examMarks = examMarks;
        this.marks = marks;
    }

    public Long getExamListId() {
        return this.examListId;
    }

    public void setExamListId(Long examListId) {
        this.examListId = examListId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("examListId", getExamListId())
            .toString();
    }

}
