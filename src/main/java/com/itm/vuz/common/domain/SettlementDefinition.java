package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SettlementDefinition implements Serializable {

    /** identifier field */
    private Long settlementDefinitionId;

    /** persistent field */
    private String name;

    /** persistent field */
    private Set settlements;

    /** full constructor */
    public SettlementDefinition(String name, Set settlements) {
        this.name = name;
        this.settlements = settlements;
    }

    /** default constructor */
    public SettlementDefinition() {
    }

    public Long getSettlementDefinitionId() {
        return this.settlementDefinitionId;
    }

    public void setSettlementDefinitionId(Long settlementDefinitionId) {
        this.settlementDefinitionId = settlementDefinitionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getSettlements() {
        return this.settlements;
    }

    public void setSettlements(Set settlements) {
        this.settlements = settlements;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("settlementDefinitionId", getSettlementDefinitionId())
            .toString();
    }

}
