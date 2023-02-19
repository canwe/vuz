package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Card implements Serializable {

    /** identifier field */
    private Long cardId;

    /** persistent field */
    private short course;

    /** persistent field */
    private boolean semester;

    /** persistent field */
    private short year;

    /** persistent field */
    private Set notes;

    /** full constructor */
    public Card(short course, boolean semester, short year, Set notes) {
        this.course = course;
        this.semester = semester;
        this.year = year;
        this.notes = notes;
    }

    /** default constructor */
    public Card() {
    }

    public Long getCardId() {
        return this.cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public short getCourse() {
        return this.course;
    }

    public void setCourse(short course) {
        this.course = course;
    }

    public boolean isSemester() {
        return this.semester;
    }

    public void setSemester(boolean semester) {
        this.semester = semester;
    }

    public short getYear() {
        return this.year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Set getNotes() {
        return this.notes;
    }

    public void setNotes(Set notes) {
        this.notes = notes;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cardId", getCardId())
            .toString();
    }

}
