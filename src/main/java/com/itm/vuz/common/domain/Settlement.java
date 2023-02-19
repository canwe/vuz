package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Settlement implements Serializable {

    /** identifier field */
    private Long settlementId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Area area;

    /** persistent field */
    private SettlementDefinition settlementDefinition;

    /** persistent field */
    private Set addresses;

    /** full constructor */
    public Settlement(String name, Area area, SettlementDefinition settlementDefinition, Set addresses) {
        this.name = name;
        this.area = area;
        this.settlementDefinition = settlementDefinition;
        this.addresses = addresses;
    }

    /** default constructor */
    public Settlement() {
    }

    public Long getSettlementId() {
        return this.settlementId;
    }

    public void setSettlementId(Long settlementId) {
        this.settlementId = settlementId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public SettlementDefinition getSettlementDefinition() {
        return this.settlementDefinition;
    }

    public void setSettlementDefinition(SettlementDefinition settlementDefinition) {
        this.settlementDefinition = settlementDefinition;
    }

    public Set getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set addresses) {
        this.addresses = addresses;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("settlementId", getSettlementId())
            .toString();
    }

}
