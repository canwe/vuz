//package statement
package com.itm.vuz.groupcard.controllers;

//Salmon import statements
import com.salmonllc.jsp.*;
import com.salmonllc.html.events.*;
import com.itm.vuz.common.controllers.BaseVUZController;


/**
 * GroupCardController: a SOFIA generated controller
 */
public class GroupCardController extends BaseVUZController implements SubmitListener{

//Visual Components
      public com.itm.vuz.groupcard.GroupCardComponent _mainGroupCard;
      public com.itm.vuz.groupcard.GroupCardComponent _mainGroupCardEdit;
      public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
      public com.salmonllc.html.HtmlLookUpComponent _lookupComponentParam;
      public com.salmonllc.html.HtmlSubmitButton _sbtGroupCard;
      public com.salmonllc.html.HtmlSubmitButton _sbtGroupCardEdit;
      public com.salmonllc.html.HtmlText _teDesc1;
      public com.salmonllc.html.HtmlText _teDesc2;
      public JspContainer _ctGroupCard;
      public JspContainer _ctGroupCardEdit;
      public JspContainer _noCache;
      public JspForm _pageForm;
      public JspTable _tabBody;
      public JspTable _tabMenu;
      public JspTableCell _tabBodyTDRow0;
      public JspTableCell _tabBodyTDRow1;
      public JspTableCell _tabMenuTDRow0;
      public JspTableCell _tabMenuTDRow1;
      public JspTableCell _tabMenuTDRow2;
      public JspTableCell _tabMenuTDRow3;
      public JspTableRow _tabBodyTRRow0;
      public JspTableRow _tabMenuTRRow0;
      public JspTableRow _tabMenuTRRow1;
      public JspTableRow _tabMenuTRRow2;
      public JspTableRow _tabMenuTRRow3;
 
/**
 * Initialize the page. Set up listeners and perform other initialization activities.
 */
public void initialize(){
     super.initialize();
     addPageListener(this);
     _sbtGroupCardEdit.addSubmitListener(this);
     _sbtGroupCard.addSubmitListener(this);
}

    public void pageRequested(PageEvent p) throws Exception {
        super.pageRequested(p);
        if(login!=null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)){
            _sbtGroupCardEdit.setEnabled(true);
        }else{
            _sbtGroupCardEdit.setEnabled(false);
            _ctGroupCardEdit.setVisible(false);
            _ctGroupCard.setVisible(true);
            this._tabMenuTDRow1.setStyle("SelectedMenuCell");
        }

    }

    /**
 * Process the given submit event
 * @param event the submit event to be processed
 * @return true to continue processing events, false to stop processing events
 */
public boolean submitPerformed(SubmitEvent event) {
    if ((event.getComponent() == this._sbtGroupCardEdit) ||
        (event.getComponent() == this._sbtGroupCard)) {
        this.changeView((com.salmonllc.html.HtmlSubmitButton)event.getComponent());
    }

     return true;
}

    private void changeView(com.salmonllc.html.HtmlSubmitButton changeBtn){
        this._tabMenuTDRow1.setStyle("MenuCell");
        this._tabMenuTDRow2.setStyle("MenuCell");

        if (changeBtn == this._sbtGroupCard) {
            _ctGroupCardEdit.setVisible(false);
            _ctGroupCard.setVisible(true);
            this._tabMenuTDRow1.setStyle("SelectedMenuCell");
        }
        if (changeBtn == this._sbtGroupCardEdit) {
            _ctGroupCardEdit.setVisible(true);
            _ctGroupCard.setVisible(false);
            this._tabMenuTDRow2.setStyle("SelectedMenuCell");
        }
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
