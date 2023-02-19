package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class BaseDiscipline implements Serializable {

    /** identifier field */
    private Long baseDisciplineId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Subfaculty subfaculty;

    /** persistent field */
    private Set disciplines;

    /** full constructor */
    public BaseDiscipline(String name, Subfaculty subfaculty, Set disciplines) {
        this.name = name;
        this.subfaculty = subfaculty;
        this.disciplines = disciplines;
    }

    /** default constructor */
    public BaseDiscipline() {
    }

    public Long getBaseDisciplineId() {
        return this.baseDisciplineId;
    }

    public void setBaseDisciplineId(Long baseDisciplineId) {
        this.baseDisciplineId = baseDisciplineId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subfaculty getSubfaculty() {
        return this.subfaculty;
    }

    public void setSubfaculty(Subfaculty subfaculty) {
        this.subfaculty = subfaculty;
    }

    public Set getDisciplines() {
        return this.disciplines;
    }

    public void setDisciplines(Set disciplines) {
        this.disciplines = disciplines;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("baseDisciplineId", getBaseDisciplineId())
            .toString();
    }

}
