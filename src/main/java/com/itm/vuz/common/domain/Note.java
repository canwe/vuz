package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Note implements Serializable {

    /** identifier field */
    private Long noteId;

    /** nullable persistent field */
    private Boolean freeAttendance;

    /** nullable persistent field */
    private String rebuke;

    /** nullable persistent field */
    private Date lastExamDate;

    /** nullable persistent field */
    private Date sessionProlongationDate;

    /** nullable persistent field */
    private Date sessionClosingDate;

    /** nullable persistent field */
    private String studentgrant;

    /** nullable persistent field */
    private String practice;

    /** persistent field */
    private StudentGroup studentGroup;

    /** persistent field */
    private Card card;

    /** persistent field */
    private Student student;

    /** full constructor */
    public Note(Boolean freeAttendance, String rebuke, Date lastExamDate, Date sessionProlongationDate, Date sessionClosingDate, String studentgrant, String practice, StudentGroup studentGroup, Card card, Student student) {
        this.freeAttendance = freeAttendance;
        this.rebuke = rebuke;
        this.lastExamDate = lastExamDate;
        this.sessionProlongationDate = sessionProlongationDate;
        this.sessionClosingDate = sessionClosingDate;
        this.studentgrant = studentgrant;
        this.practice = practice;
        this.studentGroup = studentGroup;
        this.card = card;
        this.student = student;
    }

    /** default constructor */
    public Note() {
    }

    /** minimal constructor */
    public Note(StudentGroup studentGroup, Card card, Student student) {
        this.studentGroup = studentGroup;
        this.card = card;
        this.student = student;
    }

    public Long getNoteId() {
        return this.noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Boolean getFreeAttendance() {
        return this.freeAttendance;
    }

    public void setFreeAttendance(Boolean freeAttendance) {
        this.freeAttendance = freeAttendance;
    }

    public String getRebuke() {
        return this.rebuke;
    }

    public void setRebuke(String rebuke) {
        this.rebuke = rebuke;
    }

    public Date getLastExamDate() {
        return this.lastExamDate;
    }

    public void setLastExamDate(Date lastExamDate) {
        this.lastExamDate = lastExamDate;
    }

    public Date getSessionProlongationDate() {
        return this.sessionProlongationDate;
    }

    public void setSessionProlongationDate(Date sessionProlongationDate) {
        this.sessionProlongationDate = sessionProlongationDate;
    }

    public Date getSessionClosingDate() {
        return this.sessionClosingDate;
    }

    public void setSessionClosingDate(Date sessionClosingDate) {
        this.sessionClosingDate = sessionClosingDate;
    }

    public String getStudentgrant() {
        return this.studentgrant;
    }

    public void setStudentgrant(String studentgrant) {
        this.studentgrant = studentgrant;
    }

    public String getPractice() {
        return this.practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public StudentGroup getStudentGroup() {
        return this.studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("noteId", getNoteId())
            .toString();
    }

}
