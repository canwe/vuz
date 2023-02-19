package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.hibernate.HibernateSessionFactory;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import org.hibernate.Session;
import org.hibernate.Query;
import com.itm.vuz.common.domain.*;


/**
 * This model extends the Hibernate DataStore. The HibernateDatastore generates the HQL and makes the
 * Hibernate API calls to load the data and persist any changes.
 * The SOFIA model adds value because it can do additional things, like scripted validations,
 * binding to GUI components, in memory sorting and filtering, and can leverage other SOFIA components
 * like the QBEBuilder and FormDisplayBox components.
 */
public class HibernateCountryModel extends HibernateDataStore {

    public static final String NAME = "Country.name";
    public static final String COUNTRYID = "Country.countryId";
    public static final String STUDENTS_EMPTY = "Country.students.empty";
    public static final String REGIONS_EMPTY = "Country.regions.empty";

    public HibernateCountryModel() {
     addBeanDefinition(Country.class, "Country");
          try {
            addRequiredRule(NAME, "Введите страну");
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

}