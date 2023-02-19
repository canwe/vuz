/*
 * $Id$
 * Created on 22.05.2006 17:35:59
 * Last modification $Date$
 */

package com.itm.vuz.groupcard;

import com.salmonllc.html.*;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import com.salmonllc.properties.Props;
import com.itm.vuz.common.Application;
import com.itm.vuz.common.domain.Mark;

import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;

/**
 * GroupCard table extends HtmlDataTable functionality
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardTable extends HtmlDataTable{

    private int defaultCountryId;
    private int defaultFiredStatus;
    private String studentLinkURL;
    private String disciplineExamURL;

    public GroupCardTable(String name, DataStoreBuffer ds, HtmlPage p) {
        super(name, ds, p);
        defaultComponentAttributes = new HashMap();
        defaultCountryId = Props.getProps(Application.APP_NAME, null).getIntProperty("DefaultCountryId");
        defaultFiredStatus = Props.getProps(Application.APP_NAME, null).getIntProperty("DefaultFiredStatusId");
        studentLinkURL = Props.getProps(Application.APP_NAME, null).getProperty("studentLink");
        disciplineExamURL = Props.getProps(Application.APP_NAME, null).getProperty("disciplineExamLink");

    }

    protected String getBackgroundColorByRow(int rowIndex, String backgroundColor) {
        try{
            GroupCardStudent bean = (GroupCardStudent)_ds.getDataStoreRow(rowIndex, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();

            // fired status
            if(bean.getStudent().getStudentStatus().getStudentStatusId()!=null &&
                    bean.getStudent().getStudentStatus().getStudentStatusId().intValue() == defaultFiredStatus){
                backgroundColor = Props.getProps(Application.APP_NAME, null).getProperty("FiredStatusColor");
            }
            // session not closed
            else if(bean.getNotes() == null || bean.getNotes().getSessionClosingDate() == null){
                backgroundColor = Props.getProps(Application.APP_NAME, null).getProperty("NotClosedSessionColor").toString();
            }
            // session closed
            else {if(bean.getNotes().getSessionClosingDate() != null){
                backgroundColor = Props.getProps(Application.APP_NAME, null).getProperty("ClosedSessionColor");
            }
            }
        }catch(DataStoreException e){
            MessageLog.writeErrorMessage(e, this);
        }
        return backgroundColor;
    }


    protected void processComponentByRow(int startRow, HtmlComponent comp) {
        try{
            GroupCardStudent bean = (GroupCardStudent)_ds.getDataStoreRow(startRow, DataStoreBuffer.BUFFER_STANDARD).getDSDataRow().getBean();



            //set Student Link
            if(comp instanceof HtmlLink && comp.getName().equals("studentLink")){
                defaultComponentAttributes.put(comp, ((HtmlLink)comp).getHref());
                ((HtmlLink)comp).setHref(studentLinkURL + "?student_id=" + bean.getStudent().getStudentId());
            }

            //set DisciplineExam Link
            if(comp instanceof HtmlLink && comp.getName().equals("disciplineExamLink")){
                String linkExamListId = "#";
                String disciplineLink = ((HtmlLink)comp).getHref();
                defaultComponentAttributes.put(comp, disciplineLink);

                Enumeration e = ((HtmlLink)comp).getComponents();
                while(e.hasMoreElements()){
                    Object component = e.nextElement();
                    if(component instanceof HtmlText){
                        if(((HtmlText)component).getExpressionEvaluator().getDataStoreExpression() instanceof MarksExpression){
                          //discipline
                          Long disciplineId = ((MarksExpression)(((HtmlText)component)).getExpressionEvaluator().getDataStoreExpression()).getDesciplineId();
                          // marks
                          Mark mark = bean.getMarkObject(disciplineId);
                          if(mark!=null && mark.getExamList()!=null){
                            linkExamListId = disciplineExamURL + "?examListId=" + mark.getExamList().getExamListId();
                          }
                        }
                    }
                }
                ((HtmlLink)comp).setHref(linkExamListId);
            }

            // foreign students
            if(bean.getStudent().getCountry().getCountryId()!=null &&
                    bean.getStudent().getCountry().getCountryId().intValue() != defaultCountryId){
                if(comp instanceof HtmlText){
                    // save default font
                    defaultComponentAttributes.put(comp, ((HtmlText)comp).getFont());
                    ((HtmlText)comp).setFont("ForeignStudentFont");
                }
                if(comp instanceof HtmlLink){
                    // save default font
                    Enumeration e = ((HtmlLink)comp).getComponents();
                    while(e.hasMoreElements()){
                        Object component = e.nextElement();
                        if(component instanceof HtmlText){
                            defaultComponentAttributes.put(component, ((HtmlText)component).getFont());
                            ((HtmlText)component).setFont("ForeignStudentFont");
                        }
                    }
                }
            }
            else if(bean.getStudent().isContract()){
                if(comp instanceof HtmlText){
                    // save default font
                    defaultComponentAttributes.put(comp, ((HtmlText)comp).getFont());
                    ((HtmlText)comp).setFont("ContractStudentFont");
                }
                if(comp instanceof HtmlLink){
                    // save default font
                    Enumeration e = ((HtmlLink)comp).getComponents();
                    while(e.hasMoreElements()){
                        Object component = e.nextElement();
                        if(component instanceof HtmlText){
                            defaultComponentAttributes.put(component, ((HtmlText)component).getFont());
                            ((HtmlText)component).setFont("ContractStudentFont");
                        }
                    }
                }
            }
            else if(bean.getStudent().getCourseLeader()!=null && bean.getStudent().getCourseLeader().booleanValue()){
                if(comp instanceof HtmlText){
                    // save default font
                    defaultComponentAttributes.put(comp, ((HtmlText)comp).getFont());
                    ((HtmlText)comp).setFont("CourseLeaderFont");
                }
                if(comp instanceof HtmlLink){
                    // save default font
                    Enumeration e = ((HtmlLink)comp).getComponents();
                    while(e.hasMoreElements()){
                        Object component = e.nextElement();
                        if(component instanceof HtmlText){
                            defaultComponentAttributes.put(component, ((HtmlText)component).getFont());
                            ((HtmlText)component).setFont("CourseLeaderFont");
                        }
                    }
                }

            }

            //set DisciplineExam Link
            if(comp instanceof HtmlLink && comp.getName().equals("disciplineExamLink")){

                // save default font
                Enumeration e = ((HtmlLink)comp).getComponents();
                while(e.hasMoreElements()){
                    Object component = e.nextElement();
                    if(component instanceof HtmlText){
                        if(((HtmlText)component).getExpressionEvaluator().getDataStoreExpression() instanceof MarksExpression){
                            //discipline
                            Long disciplineId = ((MarksExpression)(((HtmlText)component)).getExpressionEvaluator().getDataStoreExpression()).getDesciplineId();
                            // marks
                            Mark mark = bean.getMarkObject(disciplineId);
                            if(mark!=null && mark.getExamList()!=null){
                                defaultComponentAttributes.put(component, ((HtmlText)component).getFont());
                                if(mark.getExamList().getType() == null || mark.getExamList().getType().byteValue() == (byte)0){
                                    //base
                                    ((HtmlText)component).setFont("BaseMarkFont");
                                }else{
                                    // additional
                                    ((HtmlText)component).setFont("AdditionalMarkFont");
                                }
                            }
                        }
                    }
                }
            }

        }catch(DataStoreException e){
            MessageLog.writeErrorMessage(e, this);
        }

    }

    /**
     * returns default font for component
     * @param startRow
     * @param comp
     */
    protected void postProcessComponentByRow(int startRow, HtmlComponent comp) {

        if(comp instanceof HtmlLink && (comp.getName().equals("studentLink") || comp.getName().equals("disciplineExamLink"))){
            if(defaultComponentAttributes.containsKey(comp)){
                ((HtmlLink)comp).setHref((String)defaultComponentAttributes.get(comp));
            }
        }

      if(comp instanceof HtmlText){
        if(defaultComponentAttributes.containsKey(comp)){
            ((HtmlText)comp).setFont((String)defaultComponentAttributes.get(comp));
        }
      }else if(comp instanceof HtmlLink){
          Enumeration e = ((HtmlLink)comp).getComponents();
          while(e.hasMoreElements()){
              Object component = e.nextElement();
              if(component instanceof HtmlText){
                  ((HtmlText)component).setFont((String)defaultComponentAttributes.get(component));
              }
          }
      }
    }

  private Map defaultComponentAttributes;

}
