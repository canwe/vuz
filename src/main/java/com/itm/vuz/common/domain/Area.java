package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Area implements Serializable {

    /** identifier field */
    private Long areaId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Region region;

    /** persistent field */
    private Set settlements;

    /** full constructor */
    public Area(String name, Region region, Set settlements) {
        this.name = name;
        this.region = region;
        this.settlements = settlements;
    }

    /** default constructor */
    public Area() {
    }

    public Long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set getSettlements() {
        return this.settlements;
    }

    public void setSettlements(Set settlements) {
        this.settlements = settlements;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("areaId", getAreaId())
            .toString();
    }

}
