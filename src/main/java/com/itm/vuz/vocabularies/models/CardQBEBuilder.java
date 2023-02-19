package com.itm.vuz.vocabularies.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;

public class CardQBEBuilder extends QBEBuilder{
  public CardQBEBuilder(){
      try{
          addCriteria(HibernateCardModel.COURSE, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateCardModel.COURSE);
      }catch(Exception e){
          MessageLog.writeErrorMessage(e, this);
      }
  }
}


