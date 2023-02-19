package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Region;

/**
 * User: Alexei
 * Date: 06.07.2006
 * Time: 17:37:08
 */
public class HibernateRegionModel extends HibernateDataStore {

    public static final String NAME = "Region.name";
    public static final String COUNTRY_NAME = "Region.country.name";
    public static final String COUNTRY_COUNTRYID = "Region.country.countryId";
    public static final String COUNTRY_STUDENTS_EMPTY = "Region.country.students.empty";
    public static final String COUNTRY_REGIONS_EMPTY = "Region.country.regions.empty";
    public static final String REGIONID = "Region.regionId";
    public static final String AREAS_EMPTY = "Region.areas.empty";

    public HibernateRegionModel() {
        addBeanDefinition(Region.class, "Region");
         try {
            addRequiredRule(NAME, "Введите Регион");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
