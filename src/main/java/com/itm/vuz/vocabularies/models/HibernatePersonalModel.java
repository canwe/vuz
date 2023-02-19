package com.itm.vuz.vocabularies.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Personal;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 8:24:36
 */
public class HibernatePersonalModel extends HibernateDataStore {

    public static final String NAME = "Personal.name";
    public static final String USERS_EMPTY = "Personal.users.empty";
    public static final String POSITION_NAME = "Personal.position.name";
    public static final String POSITION_POSITIONID = "Personal.position.positionId";
    public static final String POSITION_PERSONALS_EMPTY = "Personal.position.personals.empty";
    public static final String FAMILYNAME = "Personal.familyName";
    public static final String PERSONALID = "Personal.personalId";
    public static final String PATRONYMIC = "Personal.patronymic";
    public static final String DEPARTAMENT_NAME = "Personal.departament.name";
    public static final String DEPARTAMENT_PERSONALS_EMPTY = "Personal.departament.personals.empty";
    public static final String DEPARTAMENT_DEPARTMANENTID = "Personal.departament.departmanentId";
    public static final String STUDENTGROUPS_EMPTY = "Personal.studentGroups.empty";
    public static final String TEACHERS_EMPTY = "Personal.teachers.empty";

    public HibernatePersonalModel() {
        addBeanDefinition(Personal.class, "Personal");
    }
}
