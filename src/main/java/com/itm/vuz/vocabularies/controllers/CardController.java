//package statement
package com.itm.vuz.vocabularies.controllers;

//Salmon import statements

import com.salmonllc.html.events.*;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Card;
import com.itm.vuz.common.controllers.BaseVocabulariesController;
import com.itm.vuz.vocabularies.models.SemestrForCardExpression;

import java.util.Calendar;


/**
 * CardController: a SOFIA generated controller
 */
public class CardController extends BaseVocabulariesController implements SubmitListener, PageListener {

//Visual Components
    public com.salmonllc.html.HtmlDropDownList _semestr;
    public com.salmonllc.html.HtmlDropDownList _courseSelect;
    public com.salmonllc.html.HtmlDropDownList _yearSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlText _courseCAP;
    public com.salmonllc.html.HtmlText _courses;
    public com.salmonllc.html.HtmlText _coursesCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlText _semesterCAP;
    public com.salmonllc.html.HtmlText _semesters;
    public com.salmonllc.html.HtmlText _semestersCAP;
    public com.salmonllc.html.HtmlText _yearCAP;
    public com.salmonllc.html.HtmlText _years;
    public com.salmonllc.html.HtmlText _yearsCAP;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.CardQBEBuilder _datasource_qbe;
    public com.itm.vuz.vocabularies.models.HibernateCardModel _hibernateCardDatasource;

//DataSource Column Constants
    public static final String HIBERNATECARDDATASOURCE_CARD_YEAR = "Card.year";
    public static final String HIBERNATECARDDATASOURCE_CARD_SEMESTER = "Card.semester";
    public static final String HIBERNATECARDDATASOURCE_CARD_CARDID = "Card.cardId";
    public static final String HIBERNATECARDDATASOURCE_CARD_COURSE = "Card.course";
    public static final String HIBERNATECARDDATASOURCE_CARD_NOTES_EMPTY = "Card.notes.empty";

    public static final String DATASOURCE_QBE_CARD_YEAR = "AllColumns";

    public static final int HISTORY_YEARS = 10;
    public static final int FUTURE_YEARS = 20;
    /**
     * Initialize the page. Set up listeners and perform other initialization activities.
     */
    public void initialize() {
        super.initialize();

        _semestr.addOption("false","первый");
        _semestr.addOption("true","второй");

        try{
            _semesters.setExpression(_hibernateCardDatasource, new SemestrForCardExpression());
        }catch(Exception e){
            MessageLog.writeErrorMessage(e, this);
        }

        for(int i=1;i<=6;i++)
         _courseSelect.addOption("" +i,"" +i);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int startYear = currentYear - HISTORY_YEARS;

         for(int i=startYear; i<=currentYear + FUTURE_YEARS;i++){
                   _yearSelect.addOption("" + i, "" + i);
                }
    }

    /**
     * Process the given submit event
     *
     * @param event the submit event to be processed
     * @return true to continue processing events, false to stop processing events
     */
    public boolean submitPerformed(SubmitEvent event) {
        if (event.getSource() == _d.getAddButton()) {
            _courseSelect.setValue("");
            _yearSelect.setValue("");
        }

        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            //we delete _l.getRowToEdit() row
            int rowNo = _l.getRowToEdit();
            // card
            Card card = (Card) _hibernateCardDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete card with ID = [" +
                    card.getCardId().toString() + "]", this);


            if (card.getNotes() != null && card.getNotes().size() > 0) {
                // there are notes that contain card as Card,
                // delete cannot be performed, delete all notes first
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить карточку " + card.getCardId());
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