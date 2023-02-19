//package statement
package com.itm.vuz.common.controllers;

//Salmon import statements
import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;


/**
 * HomePage2Controller: a SOFIA generated controller
 */
public class HomePage2Controller extends JspController implements SubmitListener, PageListener {

//Visual Components
      public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
      public JspContainer _noCache;
      public JspForm _pageForm;
 
/**
 * Initialize the page. Set up listeners and perform other initialization activities.
 */
public void initialize(){
     addPageListener(this);
}
 
/**
 * Process the given submit event
 * @param event the submit event to be processed
 * @return true to continue processing events, false to stop processing events
 */
public boolean submitPerformed(SubmitEvent event) {
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
