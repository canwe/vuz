package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Region implements Serializable {

    /** identifier field */
    private Long regionId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Country country;

    /** persistent field */
    private Set areas;

    /** full constructor */
    public Region(String name, Country country, Set areas) {
        this.name = name;
        this.country = country;
        this.areas = areas;
    }

    /** default constructor */
    public Region() {
    }

    public Long getRegionId() {
        return this.regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set getAreas() {
        return this.areas;
    }

    public void setAreas(Set areas) {
        this.areas = areas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("regionId", getRegionId())
            .toString();
    }

}
