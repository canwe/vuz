package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Area;

/**
 * User: Alexei
 * Date: 06.07.2006
 * Time: 16:54:06
 */
public class HibernateAreaModel extends HibernateDataStore {

    public static final String NAME = "Area.name";
    public static final String AREAID = "Area.areaId";
    public static final String REGION_NAME = "Area.region.name";
    public static final String REGION_COUNTRY_NAME = "Area.region.country.name";
    public static final String REGION_COUNTRY_STUDENTS_EMPTY = "Area.region.country.students.empty";
    public static final String REGION_COUNTRY_COUNTRYID = "Area.region.country.countryId";
    public static final String REGION_COUNTRY_REGIONS_EMPTY = "Area.region.country.regions.empty";
    public static final String REGION_REGIONID = "Area.region.regionId";
    public static final String REGION_AREAS_EMPTY = "Area.region.areas.empty";
    public static final String SETTLEMENTS_EMPTY = "Area.settlements.empty";

    public HibernateAreaModel() {
        addBeanDefinition(Area.class, "Area");
         try {
            addRequiredRule(NAME, "Введите Область");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
