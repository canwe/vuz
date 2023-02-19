package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Address implements Serializable {

    /** identifier field */
    private Long addressId;

    /** nullable persistent field */
    private String address;

    /** persistent field */
    private Settlement settlement;

    /** persistent field */
    private Set students;

    /** full constructor */
    public Address(String address, Settlement settlement, Set students) {
        this.address = address;
        this.settlement = settlement;
        this.students = students;
    }

    /** default constructor */
    public Address() {
    }

    /** minimal constructor */
    public Address(Settlement settlement, Set students) {
        this.settlement = settlement;
        this.students = students;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Settlement getSettlement() {
        return this.settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public Set getStudents() {
        return this.students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("addressId", getAddressId())
            .toString();
    }

}
