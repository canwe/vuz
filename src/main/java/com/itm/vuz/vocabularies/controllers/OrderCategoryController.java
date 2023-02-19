//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.OrderCategory;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * OrderCategoryController: a SOFIA generated controller
 */
public class OrderCategoryController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _orderCategoryCAP;
    public com.salmonllc.html.HtmlText _orderCategorys;
    public com.salmonllc.html.HtmlText _orderCategorysCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _orderCategory;
    public com.salmonllc.html.HtmlTextEdit _searchEdit;
    public com.salmonllc.jsp.JspBox _box1;
    public com.salmonllc.jsp.JspBox _box2;
    public com.salmonllc.jsp.JspBox _box3;
    public com.salmonllc.jsp.JspBox _box4;
    public com.salmonllc.jsp.JspContainer _noCache;
    public com.salmonllc.jsp.JspDataTable _datatable1;
    public com.salmonllc.jsp.JspForm _pageForm;
    public com.salmonllc.jsp.JspListFormDisplayBox _l;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateOrderCategoryModel _hibernateOrderCategoryDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";


    public static final String HIBERNATEORDERCATEGORYDATASOURCE_ORDERCATEGORY_ORDERCATEGORYID = "OrderCategory.orderCategoryId";
    public static final String HIBERNATEORDERCATEGORYDATASOURCE_ORDERCATEGORY_ORDERCATEGORY = "OrderCategory.orderCategory";
    public static final String HIBERNATEORDERCATEGORYDATASOURCE_ORDERCATEGORY_OFFICEORDERS_EMPTY = "OrderCategory.officeOrders.empty";


    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            int rowNo = _l.getRowToEdit();
            OrderCategory orderCategory = (OrderCategory) _hibernateOrderCategoryDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete orderCategory with name = [" +
                    orderCategory.getOrderCategory() + "]", this);


            if (orderCategory.getOfficeOrders() != null && orderCategory.getOfficeOrders().size() > 0) {
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить категорию приказа " + orderCategory.getOrderCategory());
                    _d.tryCancel();
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
                return false;

            }
        }
        return true;
    }

    /**
     * Process the page requested event
     *
     * @param event the page event to be processed
     */
    public void pageRequested(PageEvent event) {
        try {

            super.pageRequested(event);

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    /**
     * Process the page request end event
     *
     * @param event the page event to be processed
     */
    public void pageRequestEnd(PageEvent event) throws Exception {

    }

    /**
     * Process the page submit end event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitEnd(PageEvent event) {
        _box2.setVisible(true);
    }

    /**
     * Process the page submit event
     *
     * @param event the page event to be processed
     */
    public void pageSubmitted(PageEvent event) {
    }

}