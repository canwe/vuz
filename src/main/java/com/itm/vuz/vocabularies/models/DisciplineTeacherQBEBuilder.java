package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

public class DisciplineTeacherQBEBuilder extends QBEBuilder{
  public DisciplineTeacherQBEBuilder(){
      try{
          addCriteria(HibernateDisciplineTeacherModel.NAME, QBEBuilder.CRITERIA_TYPE_CONTAINS_IGNORE_CASE, HibernateDisciplineTeacherModel.NAME);
      }catch(Exception e){
          MessageLog.writeErrorMessage(e, this);
      }
  }
}
