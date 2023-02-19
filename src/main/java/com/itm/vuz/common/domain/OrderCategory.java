package com.itm.vuz.common.domain;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrderCategory implements Serializable {

    /** identifier field */
    private Long orderCategoryId;

    /** nullable persistent field */
    private String orderCategory;

    /** persistent field */
    private Set officeOrders;

    /** full constructor */
    public OrderCategory(String orderCategory, Set officeOrders) {
        this.orderCategory = orderCategory;
        this.officeOrders = officeOrders;
    }

    /** default constructor */
    public OrderCategory() {
    }

    /** minimal constructor */
    public OrderCategory(Set officeOrders) {
        this.officeOrders = officeOrders;
    }

    public Long getOrderCategoryId() {
        return this.orderCategoryId;
    }

    public void setOrderCategoryId(Long orderCategoryId) {
        this.orderCategoryId = orderCategoryId;
    }

    public String getOrderCategory() {
        return this.orderCategory;
    }

    public void setOrderCategory(String orderCategory) {
        this.orderCategory = orderCategory;
    }

    public Set getOfficeOrders() {
        return this.officeOrders;
    }

    public void setOfficeOrders(Set officeOrders) {
        this.officeOrders = officeOrders;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("orderCategoryId", getOrderCategoryId())
            .toString();
    }

}
