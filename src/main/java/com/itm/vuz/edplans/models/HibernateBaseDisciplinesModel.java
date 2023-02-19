/*
 * Galantis Framework
 *
 * Copyright (C) 2005. All Rights Reserved.
 * 
 * $Id$
 * Created on 03.07.2006 16:14:23
 * Last modification $Date$
 */

package com.itm.vuz.edplans.models;

import com.salmonllc.hibernate.HibernateDataStore;
import com.itm.vuz.common.domain.Discipline;
import com.itm.vuz.common.domain.BaseDiscipline;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class HibernateBaseDisciplinesModel extends HibernateDataStore{

  public static final String BASE_DISCIPLINE_NAME = "BaseDiscipline.name";
  public static final String BASE_DISCIPLINE_ID = "BaseDiscipline.baseDisciplineId";
  public HibernateBaseDisciplinesModel() {
      addBeanDefinition(BaseDiscipline.class, "BaseDiscipline");
  }

}
