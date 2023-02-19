//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.*;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Country;
import com.itm.vuz.common.controllers.BaseVocabulariesController;


/**
 * CountryController: a SOFIA generated controller
 */
public class CountryController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _countries;
    public com.salmonllc.html.HtmlText _countriesCAP;
    public com.salmonllc.html.HtmlText _countryCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _country;
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
    public com.itm.vuz.vocabularies.models.HibernateCountryModel _hibernateCountryDatasource;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String HIBERNATECOUNTRYDATASOURCE_COUNTRY_NAME = "Country.name";
    public static final String HIBERNATECOUNTRYDATASOURCE_COUNTRY_COUNTRYID = "Country.countryId";
    public static final String HIBERNATECOUNTRYDATASOURCE_COUNTRY_STUDENTS_EMPTY = "Country.students.empty";
    public static final String HIBERNATECOUNTRYDATASOURCE_COUNTRY_REGIONS_EMPTY = "Country.regions.empty";

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";


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
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // country
            Country country = (Country) _hibernateCountryDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete country with name = [" +
                    country.getName() + "]", this);


            if ((country.getRegions() != null && country.getRegions().size() > 0) ||
                    (country.getStudents() != null && country.getStudents().size() > 0)
            ) {
                //there are regions that contain country as Country,
                //delete cannot be performed, delete all regions first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить страну " + country.getName() + ". Есть связанные регионы или студенты, сперва удалите их.");
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