/*
 * Galantis Framework
 *
 * Copyright (C) 2005. All Rights Reserved.
 * 
 * $Id$
 * Created on 03.07.2006 16:15:15
 * Last modification $Date$
 */

package com.itm.vuz.edplans.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class BaseDisciplinesQBEBuilder extends QBEBuilder{
  public BaseDisciplinesQBEBuilder(){
      try{
          addCriteria(HibernateBaseDisciplinesModel.BASE_DISCIPLINE_NAME, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateBaseDisciplinesModel.BASE_DISCIPLINE_NAME);
          addCriteria(HibernateBaseDisciplinesModel.BASE_DISCIPLINE_ID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateBaseDisciplinesModel.BASE_DISCIPLINE_ID);
      }catch(Exception e){
          MessageLog.writeErrorMessage(e, this);
      }
  }

}
