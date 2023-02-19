package com.itm.vuz.edplans.models;

import com.salmonllc.sql.QBEBuilder;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.groupcard.models.HibernateGroupModel;
import com.itm.vuz.examlist.models.HibernateEducationPlanModel;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 25.06.2006
 * Time: 11:10:50
 */
public class EducationPlansQBEBuuilder extends QBEBuilder{

    public EducationPlansQBEBuuilder(){
        try{
            addCriteria(HibernateEducationPlansModel.SPECIALITY_SPECIALITYID, QBEBuilder.CRITERIA_TYPE_EQUALS, HibernateEducationPlansModel.SPECIALITY_SPECIALITYID);
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }
    }

}
