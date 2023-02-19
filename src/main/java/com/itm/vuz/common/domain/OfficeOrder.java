package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OfficeOrder implements Serializable {

    /** identifier field */
    private Long officeOrderId;

    /** nullable persistent field */
    private String pathToFile;

    /** nullable persistent field */
    private String externalNumber;

    /** nullable persistent field */
    private String reason;

    /** nullable persistent field */
    private String subject;

    /** nullable persistent field */
    private Date dateOrder;

    /** nullable persistent field */
    private Boolean issigned;

    /** nullable persistent field */
    private String signerPosition;

    /** nullable persistent field */
    private String signerName;

    /** nullable persistent field */
    private String makerPosition;

    /** nullable persistent field */
    private String makerName;

    /** nullable persistent field */
    private String coordinator1Position;

    /** nullable persistent field */
    private String coordinator1Name;

    /** nullable persistent field */
    private String coordinator2Position;

    /** nullable persistent field */
    private String coordinator2Name;

    /** nullable persistent field */
    private String coordinator3Position;

    /** nullable persistent field */
    private String coordinator3Name;

    /** nullable persistent field */
    private String coordinator4Position;

    /** nullable persistent field */
    private String coordinator4Name;

    /** nullable persistent field */
    private String coordinator5Position;

    /** nullable persistent field */
    private String coordinator5Name;

    /** nullable persistent field */
    private String coordinator6Position;

    /** nullable persistent field */
    private String coordinator6Name;

    /** persistent field */
    private OrderCategory orderCategory;

    /** persistent field */
    private Set studentHistories;

    /** full constructor */
    public OfficeOrder(String pathToFile, String externalNumber, String reason, String subject, Date dateOrder, Boolean issigned, String signerPosition, String signerName, String makerPosition, String makerName, String coordinator1Position, String coordinator1Name, String coordinator2Position, String coordinator2Name, String coordinator3Position, String coordinator3Name, String coordinator4Position, String coordinator4Name, String coordinator5Position, String coordinator5Name, String coordinator6Position, String coordinator6Name, OrderCategory orderCategory, Set studentHistories) {
        this.pathToFile = pathToFile;
        this.externalNumber = externalNumber;
        this.reason = reason;
        this.subject = subject;
        this.dateOrder = dateOrder;
        this.issigned = issigned;
        this.signerPosition = signerPosition;
        this.signerName = signerName;
        this.makerPosition = makerPosition;
        this.makerName = makerName;
        this.coordinator1Position = coordinator1Position;
        this.coordinator1Name = coordinator1Name;
        this.coordinator2Position = coordinator2Position;
        this.coordinator2Name = coordinator2Name;
        this.coordinator3Position = coordinator3Position;
        this.coordinator3Name = coordinator3Name;
        this.coordinator4Position = coordinator4Position;
        this.coordinator4Name = coordinator4Name;
        this.coordinator5Position = coordinator5Position;
        this.coordinator5Name = coordinator5Name;
        this.coordinator6Position = coordinator6Position;
        this.coordinator6Name = coordinator6Name;
        this.orderCategory = orderCategory;
        this.studentHistories = studentHistories;
    }

    /** default constructor */
    public OfficeOrder() {
    }

    /** minimal constructor */
    public OfficeOrder(OrderCategory orderCategory, Set studentHistories) {
        this.orderCategory = orderCategory;
        this.studentHistories = studentHistories;
    }

    public Long getOfficeOrderId() {
        return this.officeOrderId;
    }

    public void setOfficeOrderId(Long officeOrderId) {
        this.officeOrderId = officeOrderId;
    }

    public String getPathToFile() {
        return this.pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getExternalNumber() {
        return this.externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDateOrder() {
        return this.dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Boolean getIssigned() {
        return this.issigned;
    }

    public void setIssigned(Boolean issigned) {
        this.issigned = issigned;
    }

    public String getSignerPosition() {
        return this.signerPosition;
    }

    public void setSignerPosition(String signerPosition) {
        this.signerPosition = signerPosition;
    }

    public String getSignerName() {
        return this.signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getMakerPosition() {
        return this.makerPosition;
    }

    public void setMakerPosition(String makerPosition) {
        this.makerPosition = makerPosition;
    }

    public String getMakerName() {
        return this.makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getCoordinator1Position() {
        return this.coordinator1Position;
    }

    public void setCoordinator1Position(String coordinator1Position) {
        this.coordinator1Position = coordinator1Position;
    }

    public String getCoordinator1Name() {
        return this.coordinator1Name;
    }

    public void setCoordinator1Name(String coordinator1Name) {
        this.coordinator1Name = coordinator1Name;
    }

    public String getCoordinator2Position() {
        return this.coordinator2Position;
    }

    public void setCoordinator2Position(String coordinator2Position) {
        this.coordinator2Position = coordinator2Position;
    }

    public String getCoordinator2Name() {
        return this.coordinator2Name;
    }

    public void setCoordinator2Name(String coordinator2Name) {
        this.coordinator2Name = coordinator2Name;
    }

    public String getCoordinator3Position() {
        return this.coordinator3Position;
    }

    public void setCoordinator3Position(String coordinator3Position) {
        this.coordinator3Position = coordinator3Position;
    }

    public String getCoordinator3Name() {
        return this.coordinator3Name;
    }

    public void setCoordinator3Name(String coordinator3Name) {
        this.coordinator3Name = coordinator3Name;
    }

    public String getCoordinator4Position() {
        return this.coordinator4Position;
    }

    public void setCoordinator4Position(String coordinator4Position) {
        this.coordinator4Position = coordinator4Position;
    }

    public String getCoordinator4Name() {
        return this.coordinator4Name;
    }

    public void setCoordinator4Name(String coordinator4Name) {
        this.coordinator4Name = coordinator4Name;
    }

    public String getCoordinator5Position() {
        return this.coordinator5Position;
    }

    public void setCoordinator5Position(String coordinator5Position) {
        this.coordinator5Position = coordinator5Position;
    }

    public String getCoordinator5Name() {
        return this.coordinator5Name;
    }

    public void setCoordinator5Name(String coordinator5Name) {
        this.coordinator5Name = coordinator5Name;
    }

    public String getCoordinator6Position() {
        return this.coordinator6Position;
    }

    public void setCoordinator6Position(String coordinator6Position) {
        this.coordinator6Position = coordinator6Position;
    }

    public String getCoordinator6Name() {
        return this.coordinator6Name;
    }

    public void setCoordinator6Name(String coordinator6Name) {
        this.coordinator6Name = coordinator6Name;
    }

    public OrderCategory getOrderCategory() {
        return this.orderCategory;
    }

    public void setOrderCategory(OrderCategory orderCategory) {
        this.orderCategory = orderCategory;
    }

    public Set getStudentHistories() {
        return this.studentHistories;
    }

    public void setStudentHistories(Set studentHistories) {
        this.studentHistories = studentHistories;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("officeOrderId", getOfficeOrderId())
            .toString();
    }

}
