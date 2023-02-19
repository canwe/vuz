package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class StudentEvent implements Serializable {

    /** identifier field */
    private Long eventId;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String comment;

    /** persistent field */
    private Set studentHistories;

    /** full constructor */
    public StudentEvent(String name, String comment, Set studentHistories) {
        this.name = name;
        this.comment = comment;
        this.studentHistories = studentHistories;
    }

    /** default constructor */
    public StudentEvent() {
    }

    /** minimal constructor */
    public StudentEvent(Set studentHistories) {
        this.studentHistories = studentHistories;
    }

    public Long getEventId() {
        return this.eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set getStudentHistories() {
        return this.studentHistories;
    }

    public void setStudentHistories(Set studentHistories) {
        this.studentHistories = studentHistories;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("eventId", getEventId())
            .toString();
    }

}
