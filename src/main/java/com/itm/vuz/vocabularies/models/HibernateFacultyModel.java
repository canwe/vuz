package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Faculty;

/**
 * User: Alexei
 * Date: 11.07.2006
 * Time: 7:47:24
 */
public class HibernateFacultyModel extends HibernateDataStore {

    public static final String NAME = "Faculty.name";
    public static final String SHORTNAME = "Faculty.shortName";
    public static final String FACULTYID = "Faculty.facultyId";
    public static final String FACULTYCODE = "Faculty.facultyCode";
    public static final String FACULTYGROUPCODES_EMPTY = "Faculty.facultyGroupCodes.empty";
    public static final String SUBFACULTIES_EMPTY = "Faculty.subfaculties.empty";
    public static final String SPECIALITIES_EMPTY = "Faculty.specialities.empty";


    public HibernateFacultyModel() {
        addBeanDefinition(Faculty.class, "Faculty");

        try {
            addRequiredRule("Faculty.name", "Введите название факультета");
            addRequiredRule("Faculty.shortName", "Введите краткое название факультета");
            //addRequiredRule("Faculty.facultyCode", "Введите код факультета");

            // todo check facultyCode for int
        } catch (DataStoreException e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }
}
