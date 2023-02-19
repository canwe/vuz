package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Student implements Serializable {

    /** identifier field */
    private Long studentId;

    /** persistent field */
    private String name;

    /** persistent field */
    private String patronymic;

    /** persistent field */
    private String familyName;

    /** nullable persistent field */
    private String studentNumber;

    /** nullable persistent field */
    private String registration;

    /** persistent field */
    private String passportData;

    /** nullable persistent field */
    private String passportNumber;

    /** nullable persistent field */
    private String passportSeries;

    /** nullable persistent field */
    private String passportDelivery;

    /** persistent field */
    private boolean hospiceNeeded;

    /** persistent field */
    private boolean sex;

    /** persistent field */
    private boolean contract;

    /** nullable persistent field */
    private Boolean courseLeader;

    /** nullable persistent field */
    private Boolean profLeader;

    /** nullable persistent field */
    private String lastEducationPlace;

    /** persistent field */
    private Date yearStarting;

    /** persistent field */
    private Address address;

    /** persistent field */
    private Language language;

    /** persistent field */
    private StudentStatus studentStatus;

    /** persistent field */
    private StudentGroup studentGroup;

    /** persistent field */
    private Country country;

    /** persistent field */
    private Benefit benefit;

    /** persistent field */
    private Hospice hospice;

    /** persistent field */
    private Set examMarks;

    /** persistent field */
    private Set notes;

    /** persistent field */
    private Set marks;

    /** persistent field */
    private Set studentHistories;

    /** full constructor */
    public Student(String name, String patronymic, String familyName, String studentNumber, String registration, String passportData, String passportNumber, String passportSeries, String passportDelivery, boolean hospiceNeeded, boolean sex, boolean contract, Boolean courseLeader, Boolean profLeader, String lastEducationPlace, Date yearStarting, Address address, Language language, StudentStatus studentStatus, StudentGroup studentGroup, Country country, Benefit benefit, Hospice hospice, Set examMarks, Set notes, Set marks, Set studentHistories) {
        this.name = name;
        this.patronymic = patronymic;
        this.familyName = familyName;
        this.studentNumber = studentNumber;
        this.registration = registration;
        this.passportData = passportData;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
        this.passportDelivery = passportDelivery;
        this.hospiceNeeded = hospiceNeeded;
        this.sex = sex;
        this.contract = contract;
        this.courseLeader = courseLeader;
        this.profLeader = profLeader;
        this.lastEducationPlace = lastEducationPlace;
        this.yearStarting = yearStarting;
        this.address = address;
        this.language = language;
        this.studentStatus = studentStatus;
        this.studentGroup = studentGroup;
        this.country = country;
        this.benefit = benefit;
        this.hospice = hospice;
        this.examMarks = examMarks;
        this.notes = notes;
        this.marks = marks;
        this.studentHistories = studentHistories;
    }

    /** default constructor */
    public Student() {
    }

    /** minimal constructor */
    public Student(String name, String patronymic, String familyName, String passportData, boolean hospiceNeeded, boolean sex, boolean contract, Date yearStarting, Address address, Language language, StudentStatus studentStatus, StudentGroup studentGroup, Country country, Benefit benefit, Hospice hospice, Set examMarks, Set notes, Set marks, Set studentHistories) {
        this.name = name;
        this.patronymic = patronymic;
        this.familyName = familyName;
        this.passportData = passportData;
        this.hospiceNeeded = hospiceNeeded;
        this.sex = sex;
        this.contract = contract;
        this.yearStarting = yearStarting;
        this.address = address;
        this.language = language;
        this.studentStatus = studentStatus;
        this.studentGroup = studentGroup;
        this.country = country;
        this.benefit = benefit;
        this.hospice = hospice;
        this.examMarks = examMarks;
        this.notes = notes;
        this.marks = marks;
        this.studentHistories = studentHistories;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getRegistration() {
        return this.registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPassportData() {
        return this.passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getPassportNumber() {
        return this.passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSeries() {
        return this.passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportDelivery() {
        return this.passportDelivery;
    }

    public void setPassportDelivery(String passportDelivery) {
        this.passportDelivery = passportDelivery;
    }

    public boolean isHospiceNeeded() {
        return this.hospiceNeeded;
    }

    public void setHospiceNeeded(boolean hospiceNeeded) {
        this.hospiceNeeded = hospiceNeeded;
    }

    public boolean isSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isContract() {
        return this.contract;
    }

    public void setContract(boolean contract) {
        this.contract = contract;
    }

    public Boolean getCourseLeader() {
        return this.courseLeader;
    }

    public void setCourseLeader(Boolean courseLeader) {
        this.courseLeader = courseLeader;
    }

    public Boolean getProfLeader() {
        return this.profLeader;
    }

    public void setProfLeader(Boolean profLeader) {
        this.profLeader = profLeader;
    }

    public String getLastEducationPlace() {
        return this.lastEducationPlace;
    }

    public void setLastEducationPlace(String lastEducationPlace) {
        this.lastEducationPlace = lastEducationPlace;
    }

    public Date getYearStarting() {
        return this.yearStarting;
    }

    public void setYearStarting(Date yearStarting) {
        this.yearStarting = yearStarting;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public StudentStatus getStudentStatus() {
        return this.studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public StudentGroup getStudentGroup() {
        return this.studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Benefit getBenefit() {
        return this.benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public Hospice getHospice() {
        return this.hospice;
    }

    public void setHospice(Hospice hospice) {
        this.hospice = hospice;
    }

    public Set getExamMarks() {
        return this.examMarks;
    }

    public void setExamMarks(Set examMarks) {
        this.examMarks = examMarks;
    }

    public Set getNotes() {
        return this.notes;
    }

    public void setNotes(Set notes) {
        this.notes = notes;
    }

    public Set getMarks() {
        return this.marks;
    }

    public void setMarks(Set marks) {
        this.marks = marks;
    }

    public Set getStudentHistories() {
        return this.studentHistories;
    }

    public void setStudentHistories(Set studentHistories) {
        this.studentHistories = studentHistories;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("studentId", getStudentId())
            .toString();
    }

}
