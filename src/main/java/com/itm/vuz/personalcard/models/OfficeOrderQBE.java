package com.itm.vuz.personalcard.models;

/**
 * User: artem1
 * Date: 25.06.2006
 * Time: 21:07:00
 */
public class OfficeOrderQBE extends com.salmonllc.sql.QBEBuilder {

    public OfficeOrderQBE() {
        this.addCriteria("ExternalNumber", CRITERIA_TYPE_COMPLEX, OfficeOrderModel.EXTERANL_NUMBER);
        this.addCriteria("DateOrder", CRITERIA_TYPE_EQUALS, OfficeOrderModel.DATE_ORDER);
        this.addCriteria("IsSigned", CRITERIA_TYPE_EQUALS,  OfficeOrderModel.IS_SIGNED);
        this.addCriteria("OrderCategory", CRITERIA_TYPE_EQUALS, OfficeOrderModel.ORDER_CATEGORY);
    }
}
