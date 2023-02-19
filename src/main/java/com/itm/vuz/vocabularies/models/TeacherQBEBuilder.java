package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

public class TeacherQBEBuilder extends QBEBuilder{
  public TeacherQBEBuilder(){
      try{
          addCriteria(HibernateTeacherModel.FAMILY, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateTeacherModel.FAMILY);
          addCriteria(HibernateTeacherModel.NAME, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateTeacherModel.NAME);
          addCriteria(HibernateTeacherModel.PATRONYMIC, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateTeacherModel.PATRONYMIC);                      
      }catch(Exception e){
          MessageLog.writeErrorMessage(e, this);
      }
  }
}
