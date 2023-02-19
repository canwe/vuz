//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements
import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;


/**
 * untitled1: a SOFIA generated controller
 */
public class Untitled1 extends JspController implements SubmitListener, PageListener {

//Visual Components
      public com.salmonllc.html.HtmlDropDownList _select1;
      public com.salmonllc.html.HtmlSubmitButton _submit1;
      public com.salmonllc.html.HtmlText _teSelectedBenefitID;
      public JspForm _pageForm;

//DataSources
      public com.itm.vuz.personalcard.models.BenefitModel _dsBenefit;

//DataSource Column Constants
       public static final String DSBENEFIT_B_NAME="b.name";
       public static final String DSBENEFIT_B_BENEFITID="b.benefitId";
       public static final String DSBENEFIT_B_STUDENTS_EMPTY="b.students.empty";

 
/**
 * Initialize the page. Set up listeners and perform other initialization activities.
 */
public void initialize(){
     addPageListener(this);
     _submit1.addSubmitListener(this);
}
 
/**
 * Process the given submit event
 * @param event the submit event to be processed
 * @return true to continue processing events, false to stop processing events
 */
public boolean submitPerformed(SubmitEvent event) {
    this._teSelectedBenefitID.setText(this._select1.getValue());
     return true;
}
 
/**
 * Process the page requested event
 * @param event the page event to be processed
 */
public void pageRequested(PageEvent event) {
}
 
/**
 * Process the page request end event
 * @param event the page event to be processed
 */
public void pageRequestEnd(PageEvent event) {
}
 
/**
 * Process the page submit end event
 * @param event the page event to be processed
 */
public void pageSubmitEnd(PageEvent event) {
}
 
/**
 * Process the page submit event
 * @param event the page event to be processed
 */
public void pageSubmitted(PageEvent event){
}

}
