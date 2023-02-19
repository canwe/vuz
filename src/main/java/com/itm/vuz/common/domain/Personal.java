package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Personal implements Serializable {

    /** identifier field */
    private Long personalId;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String patronymic;

    /** nullable persistent field */
    private String familyName;

    /** persistent field */
    private Position position;

    /** persistent field */
    private Departament departament;

    /** persistent field */
    private Set studentGroups;

    /** persistent field */
    private Set teachers;

    /** persistent field */
    private Set users;

    /** full constructor */
    public Personal(String name, String patronymic, String familyName, Position position, Departament departament, Set studentGroups, Set teachers, Set users) {
        this.name = name;
        this.patronymic = patronymic;
        this.familyName = familyName;
        this.position = position;
        this.departament = departament;
        this.studentGroups = studentGroups;
        this.teachers = teachers;
        this.users = users;
    }

    /** default constructor */
    public Personal() {
    }

    /** minimal constructor */
    public Personal(Position position, Departament departament, Set studentGroups, Set teachers, Set users) {
        this.position = position;
        this.departament = departament;
        this.studentGroups = studentGroups;
        this.teachers = teachers;
        this.users = users;
    }

    public Long getPersonalId() {
        return this.personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Departament getDepartament() {
        return this.departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public Set getStudentGroups() {
        return this.studentGroups;
    }

    public void setStudentGroups(Set studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("personalId", getPersonalId())
            .toString();
    }

}
