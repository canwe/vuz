package com.itm.vuz.vocabularies.controllers;

import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.HtmlHiddenField;
import com.salmonllc.util.MessageLog;
import com.itm.vuz.common.domain.Position;
import com.itm.vuz.common.domain.Departament;
import com.itm.vuz.common.domain.Personal;
import com.itm.vuz.common.controllers.BaseVocabulariesController;

/**
 * User: Alexei
 * Date: 12.07.2006
 * Time: 8:30:31
 */
public class PersonalController extends BaseVocabulariesController
        implements SubmitListener, PageListener {

    //Visual Components
    public com.salmonllc.html.HtmlDropDownList _departamentSelect;
    public com.salmonllc.html.HtmlDropDownList _positionSelect;
    public com.salmonllc.html.HtmlImage _sourceForgeLogoImage;
    public com.salmonllc.html.HtmlLink _personals;
    public com.salmonllc.html.HtmlText _departamentCAP;
    public com.salmonllc.html.HtmlText _departaments;
    public com.salmonllc.html.HtmlText _departamentsCAP;
    public com.salmonllc.html.HtmlText _familyNameCAP;
    public com.salmonllc.html.HtmlText _familyNamesCAP;
    public com.salmonllc.html.HtmlText _familyNames;
    public com.salmonllc.html.HtmlText _patronymicCAP;
    public com.salmonllc.html.HtmlText _patronymics;
    public com.salmonllc.html.HtmlText _patronymicsCAP;
    public com.salmonllc.html.HtmlText _personalCAP;
    public com.salmonllc.html.HtmlText _personalsCAP;
    public com.salmonllc.html.HtmlText _positionCAP;
    public com.salmonllc.html.HtmlText _positions;
    public com.salmonllc.html.HtmlText _positionsCAP;
    public com.salmonllc.html.HtmlText _searchEditCAP;
    public com.salmonllc.html.HtmlTextEdit _patronymic;
    public com.salmonllc.html.HtmlTextEdit _personal;
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
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDHeader4;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow0;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow1;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow2;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow3;
    public com.salmonllc.jsp.JspTableCell _datatable1TDRow4;
    public com.salmonllc.jsp.JspTableRow _datatable1TRHeader0;
    public com.salmonllc.jsp.JspTableRow _datatable1TRRow0;

//DataSources
    public com.itm.vuz.vocabularies.models.HibernateDepartamentModel _hibernateDepartamentModel;
    public com.itm.vuz.vocabularies.models.HibernatePersonalModel _hibernatePersonalDatasource;
    public com.itm.vuz.vocabularies.models.HibernatePositionModel _hibernatePositionModel;
    public com.salmonllc.sql.QBEBuilder _datasource_qbe;

//DataSource Column Constants
    public static final String HIBERNATEPOSITIONMODEL_POSITION_NAME = "Position.name";
    public static final String HIBERNATEPOSITIONMODEL_POSITION_POSITIONID = "Position.positionId";
    public static final String HIBERNATEPOSITIONMODEL_POSITION_PERSONALS_EMPTY = "Position.personals.empty";

    public static final String DATASOURCE_QBE_ALLCOLUMNS = "AllColumns";
    public HtmlHiddenField _refreshFlag;

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
        if (event.getSource() == _d.getSaveButton()) {
            _refreshFlag.setValue("true");
        }

        _d.setConfirmDelete(true);

        //is delete button submitted?
        if (event.getSource() == _d.getDeleteButton()) {
            int rowNo = _l.getRowToEdit();
            Personal personal = (Personal) _hibernatePersonalDatasource.getDataRow(rowNo).getBean();
            MessageLog.writeDebugMessage("trying to delete teacher with name = [" +
                    personal.getFamilyName() + " " + personal.getName() + " " + personal.getPatronymic() + "]", this);


            if (personal.getTeachers() != null && personal.getTeachers().size() > 0) {
                try {
                    _d.getValidator().addErrorMessage("Невозможно удалить преподавателя " + personal.getFamilyName() + " " + personal.getName() + " " + personal.getPatronymic());
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

            initializeDepartmentComboBox();
            initializePositionComboBox();
            if (_refreshFlag.getValue().equals("true")) {
                try {
                    _searchformdisplaybox1.trySearch();
                    _refreshFlag.setValue("false");
                } catch (Exception e) {
                    MessageLog.writeErrorMessage(e, this);
                }
            }

        } catch (Exception e) {
            MessageLog.writeErrorMessage("Error in ", e, this);
        }
    }

    private void initializePositionComboBox() throws Exception {
        _positionSelect.resetOptions();
        _hibernatePositionModel.reset();
        _hibernatePositionModel.retrieve();
        for (int i = 0; i < _hibernatePositionModel.getRowCount(); i++) {
            Position position = (Position) _hibernatePositionModel.getDataRow(i).getBean();
            if (position != null) {
                _positionSelect.addOption(position.getPositionId().toString(),
                        position.getName());
            }
        }
    }

    private void initializeDepartmentComboBox() throws Exception {
        _departamentSelect.resetOptions();
        _hibernateDepartamentModel.reset();
        _hibernateDepartamentModel.retrieve();
        for (int i = 0; i < _hibernateDepartamentModel.getRowCount(); i++) {
            Departament departament = (Departament) _hibernateDepartamentModel.getDataRow(i).getBean();
            if (departament != null) {
                _departamentSelect.addOption(departament.getDepartmanentId().toString(),
                        departament.getName());
            }
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
