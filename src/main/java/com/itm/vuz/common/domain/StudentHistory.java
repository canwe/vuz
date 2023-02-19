package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StudentHistory implements Serializable {

    /** identifier field */
    private Long studentHistoryId;

    /** nullable persistent field */
    private String comment;

    /** nullable persistent field */
    private Date dateEvent;

    /** persistent field */
    private OfficeOrder officeOrder;

    /** persistent field */
    private User user;

    /** persistent field */
    private StudentEvent studentEvent;

    /** persistent field */
    private Student student;

    /** full constructor */
    public StudentHistory(String comment, Date dateEvent, OfficeOrder officeOrder, User user, StudentEvent studentEvent, Student student) {
        this.comment = comment;
        this.dateEvent = dateEvent;
        this.officeOrder = officeOrder;
        this.user = user;
        this.studentEvent = studentEvent;
        this.student = student;
    }

    /** default constructor */
    public StudentHistory() {
    }

    /** minimal constructor */
    public StudentHistory(OfficeOrder officeOrder, User user, StudentEvent studentEvent, Student student) {
        this.officeOrder = officeOrder;
        this.user = user;
        this.studentEvent = studentEvent;
        this.student = student;
    }

    public Long getStudentHistoryId() {
        return this.studentHistoryId;
    }

    public void setStudentHistoryId(Long studentHistoryId) {
        this.studentHistoryId = studentHistoryId;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateEvent() {
        return this.dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public OfficeOrder getOfficeOrder() {
        return this.officeOrder;
    }

    public void setOfficeOrder(OfficeOrder officeOrder) {
        this.officeOrder = officeOrder;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StudentEvent getStudentEvent() {
        return this.studentEvent;
    }

    public void setStudentEvent(StudentEvent studentEvent) {
        this.studentEvent = studentEvent;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("studentHistoryId", getStudentHistoryId())
            .toString();
    }

}
