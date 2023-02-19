package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 21.07.2006
 * Time: 8:37:22
 */
public class DisciplinesQBEBuilder extends QBEBuilder{
  public DisciplinesQBEBuilder(){
      try{
          addCriteria(HibernateDisciplineModel.NAME, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateDisciplineModel.NAME);
          //addCriteria(HibernateDisciplineModel.BASE_DISCIPLINE_ID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateBaseDisciplinesModel.BASE_DISCIPLINE_ID);
      }catch(Exception e){
          MessageLog.writeErrorMessage(e, this);
      }
  }
}

