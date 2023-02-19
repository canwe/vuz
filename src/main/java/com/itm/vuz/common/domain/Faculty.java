package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Faculty implements Serializable {

    /** identifier field */
    private Long facultyId;

    /** persistent field */
    private String name;

    /** persistent field */
    private String shortName;

    /** nullable persistent field */
    private Byte facultyCode;

    /** persistent field */
    private Set facultyGroupCodes;

    /** persistent field */
    private Set subfaculties;

    /** persistent field */
    private Set specialities;

    /** full constructor */
    public Faculty(String name, String shortName, Byte facultyCode, Set facultyGroupCodes, Set subfaculties, Set specialities) {
        this.name = name;
        this.shortName = shortName;
        this.facultyCode = facultyCode;
        this.facultyGroupCodes = facultyGroupCodes;
        this.subfaculties = subfaculties;
        this.specialities = specialities;
    }

    /** default constructor */
    public Faculty() {
    }

    /** minimal constructor */
    public Faculty(String name, String shortName, Set facultyGroupCodes, Set subfaculties, Set specialities) {
        this.name = name;
        this.shortName = shortName;
        this.facultyGroupCodes = facultyGroupCodes;
        this.subfaculties = subfaculties;
        this.specialities = specialities;
    }

    public Long getFacultyId() {
        return this.facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Byte getFacultyCode() {
        return this.facultyCode;
    }

    public void setFacultyCode(Byte facultyCode) {
        this.facultyCode = facultyCode;
    }

    public Set getFacultyGroupCodes() {
        return this.facultyGroupCodes;
    }

    public void setFacultyGroupCodes(Set facultyGroupCodes) {
        this.facultyGroupCodes = facultyGroupCodes;
    }

    public Set getSubfaculties() {
        return this.subfaculties;
    }

    public void setSubfaculties(Set subfaculties) {
        this.subfaculties = subfaculties;
    }

    public Set getSpecialities() {
        return this.specialities;
    }

    public void setSpecialities(Set specialities) {
        this.specialities = specialities;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("facultyId", getFacultyId())
            .toString();
    }

}
