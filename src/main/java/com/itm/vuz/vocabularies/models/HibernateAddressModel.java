package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Address;

/**
 * User: avb
 * Date: 05.07.2006
 * Time: 11:56:39
 */
public class HibernateAddressModel extends HibernateDataStore {

	public static final String ADDRESS = "Address.address";
	public static final String ADDRESSID = "Address.addressId";
	public static final String SETTLEMENT_NAME = "Address.settlement.name";
	public static final String SETTLEMENT_SETTLEMENTID = "Address.settlement.settlementId";
	public static final String SETTLEMENT_AREA_NAME = "Address.settlement.area.name";
	public static final String SETTLEMENT_AREA_AREAID = "Address.settlement.area.areaId";
	public static final String SETTLEMENT_AREA_REGION_NAME = "Address.settlement.area.region.name";
	public static final String SETTLEMENT_AREA_REGION_COUNTRY_NAME = "Address.settlement.area.region.country.name";
	public static final String SETTLEMENT_AREA_REGION_COUNTRY_STUDENTS_EMPTY = "Address.settlement.area.region.country.students.empty";
	public static final String SETTLEMENT_AREA_REGION_COUNTRY_COUNTRYID = "Address.settlement.area.region.country.countryId";
	public static final String SETTLEMENT_AREA_REGION_COUNTRY_REGIONS_EMPTY = "Address.settlement.area.region.country.regions.empty";
	public static final String SETTLEMENT_AREA_REGION_REGIONID = "Address.settlement.area.region.regionId";
	public static final String SETTLEMENT_AREA_REGION_AREAS_EMPTY = "Address.settlement.area.region.areas.empty";
	public static final String SETTLEMENT_AREA_SETTLEMENTS_EMPTY = "Address.settlement.area.settlements.empty";
	public static final String SETTLEMENT_SETTLEMENTDEFINITION_NAME = "Address.settlement.settlementDefinition.name";
	public static final String SETTLEMENT_SETTLEMENTDEFINITION_SETTLEMENTS_EMPTY = "Address.settlement.settlementDefinition.settlements.empty";
	public static final String SETTLEMENT_SETTLEMENTDEFINITION_SETTLEMENTDEFINITIONID = "Address.settlement.settlementDefinition.settlementDefinitionId";
	public static final String SETTLEMENT_ADDRESSES_EMPTY = "Address.settlement.addresses.empty";
	public static final String STUDENTS_EMPTY = "Address.students.empty";

	public HibernateAddressModel() {
		addBeanDefinition(Address.class, "Address");
         try {
            addRequiredRule(ADDRESS, "Введите Адрес");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
	}
}
