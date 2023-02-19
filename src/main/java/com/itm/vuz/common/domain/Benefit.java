package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Benefit implements Serializable {

    /** identifier field */
    private Long benefitId;

    /** nullable persistent field */
    private String name;

    /** persistent field */
    private Set students;

    /** full constructor */
    public Benefit(String name, Set students) {
        this.name = name;
        this.students = students;
    }

    /** default constructor */
    public Benefit() {
    }

    /** minimal constructor */
    public Benefit(Set students) {
        this.students = students;
    }

    public Long getBenefitId() {
        return this.benefitId;
    }

    public void setBenefitId(Long benefitId) {
        this.benefitId = benefitId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getStudents() {
        return this.students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("benefitId", getBenefitId())
            .toString();
    }

}
