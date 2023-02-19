package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EducationPlan implements Serializable {

    /** identifier field */
    private Long educationPlanId;

    /** nullable persistent field */
    private Date dataStart;

    /** nullable persistent field */
    private Date dataEnd;

    /** persistent field */
    private Speciality speciality;

    /** persistent field */
    private Set disciplines;

    /** full constructor */
    public EducationPlan(Date dataStart, Date dataEnd, Speciality speciality, Set disciplines) {
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.speciality = speciality;
        this.disciplines = disciplines;
    }

    /** default constructor */
    public EducationPlan() {
    }

    /** minimal constructor */
    public EducationPlan(Speciality speciality, Set disciplines) {
        this.speciality = speciality;
        this.disciplines = disciplines;
    }

    public Long getEducationPlanId() {
        return this.educationPlanId;
    }

    public void setEducationPlanId(Long educationPlanId) {
        this.educationPlanId = educationPlanId;
    }

    public Date getDataStart() {
        return this.dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataEnd() {
        return this.dataEnd;
    }

    public void setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Set getDisciplines() {
        return this.disciplines;
    }

    public void setDisciplines(Set disciplines) {
        this.disciplines = disciplines;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("educationPlanId", getEducationPlanId())
            .toString();
    }

}
