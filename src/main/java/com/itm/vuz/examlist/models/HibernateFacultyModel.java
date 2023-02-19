package com.itm.vuz.examlist.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Faculty;

/**
 * Created by IntelliJ IDEA.
 * User: Alexei
 * Date: 28.05.2006
 * Time: 19:48:21
 * To change this template use File | Settings | File Templates.
 */
public class HibernateFacultyModel extends HibernateDataStore {

	public static final String NAME = "Faculty.name";
	public static final String FACULTYID = "Faculty.facultyId";
	public static final String SHORTNAME = "Faculty.shortName";
	public static final String FACULTYGROUPCODES_EMPTY = "Faculty.facultyGroupCodes.empty";
	public static final String SUBFACULTIES_EMPTY = "Faculty.subfaculties.empty";
	public static final String SPECIALITIES_EMPTY = "Faculty.specialities.empty";

	public HibernateFacultyModel() {
		addBeanDefinition(Faculty.class, "Faculty");
	}
}
