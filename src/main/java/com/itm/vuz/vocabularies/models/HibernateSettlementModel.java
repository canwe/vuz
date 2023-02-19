package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Settlement;

/**
 * User: avb
 * Date: 05.07.2006
 * Time: 12:54:50
 */
public class HibernateSettlementModel extends HibernateDataStore {

    public static final String NAME = "Settlement.name";
    public static final String SETTLEMENTID = "Settlement.settlementId";
    public static final String AREA_NAME = "Settlement.area.name";
    public static final String AREA_AREAID = "Settlement.area.areaId";
    public static final String AREA_REGION_NAME = "Settlement.area.region.name";
    public static final String AREA_REGION_COUNTRY_NAME = "Settlement.area.region.country.name";
    public static final String AREA_REGION_COUNTRY_STUDENTS_EMPTY = "Settlement.area.region.country.students.empty";
    public static final String AREA_REGION_COUNTRY_COUNTRYID = "Settlement.area.region.country.countryId";
    public static final String AREA_REGION_COUNTRY_REGIONS_EMPTY = "Settlement.area.region.country.regions.empty";
    public static final String AREA_REGION_REGIONID = "Settlement.area.region.regionId";
    public static final String AREA_REGION_AREAS_EMPTY = "Settlement.area.region.areas.empty";
    public static final String AREA_SETTLEMENTS_EMPTY = "Settlement.area.settlements.empty";
    public static final String SETTLEMENTDEFINITION_NAME = "Settlement.settlementDefinition.name";
    public static final String SETTLEMENTDEFINITION_SETTLEMENTS_EMPTY = "Settlement.settlementDefinition.settlements.empty";
    public static final String SETTLEMENTDEFINITION_SETTLEMENTDEFINITIONID = "Settlement.settlementDefinition.settlementDefinitionId";
    public static final String ADDRESSES_EMPTY = "Settlement.addresses.empty";

    public HibernateSettlementModel() {
        addBeanDefinition(Settlement.class, "Settlement");
         try {
            addRequiredRule(NAME, "Введите название");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
