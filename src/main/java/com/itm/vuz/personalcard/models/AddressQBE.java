package com.itm.vuz.personalcard.models;

/**
 * User: artem1
 * Date: 11.05.2006
 * Time: 23:26:24
 */
public class AddressQBE extends com.salmonllc.sql.QBEBuilder {
    public AddressQBE() {
        this.addCriteria("Settlement", CRITERIA_TYPE_EQUALS, "Address.settlement");
    }
}
