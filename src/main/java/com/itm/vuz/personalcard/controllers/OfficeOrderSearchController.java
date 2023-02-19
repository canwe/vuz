//package statement
package com.itm.vuz.personalcard.controllers;

//Salmon import statements

import com.itm.vuz.common.controllers.BaseVUZController;
import com.itm.vuz.common.utils.SiteMapConstants;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;


/**
 * OfficeOrderSearchController: a SOFIA generated controller
 */
public class OfficeOrderSearchController extends BaseVUZController implements SubmitListener, PageListener {

    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
        this.addPageListener(this);
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) throws Exception {
        super.pageRequested(event);

        //Проверка прав
        if (this.isGuest()) {
            this.gotoSiteMapPage(SiteMapConstants.HOME_PAGE);
        }
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) {
    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

}
