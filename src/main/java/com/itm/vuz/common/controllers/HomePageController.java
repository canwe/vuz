//package statement
package com.itm.vuz.common.controllers;

//Salmon import statements
import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;


/**
 * HomePageController: a SOFIA generated controller
 */
public class HomePageController extends BaseVUZController implements SubmitListener, PageListener {

    //Visual Components
      public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
      public com.salmonllc.html.HtmlText _userName;
      public com.salmonllc.html.HtmlText _userRole;
      public JspContainer _noCache;
      public JspForm _pageForm;
      public JspLink _homelink;
      public JspLink _logoutlink;
 
/**
 * Initialize the page. Set up listeners and perform other initialization activities.
 */
public void initialize(){
     super.initialize();
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
public void pageRequested(PageEvent event) throws Exception{
    super.pageRequested(event);
    _userName.setText(
            (login.getName()==null ? login.getLogin() : login.getName()) + " " +
            (login.getPatronymic()==null ? "" : login.getPatronymic() + " ") +  
            (login.getFamilyName()==null ? "" : login.getFamilyName()));
    _userRole.setText(login.getRoleName());
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
