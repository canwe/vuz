package com.itm.vuz.common.controllers;

import com.salmonllc.html.events.PageEvent;
import com.salmonllc.util.MessageLog;

/**
 * provides such common tasks as: login checking, access rights, etc.
 * all custom controllers in VUZ project should extend this controller
 */
public class BaseVocabulariesController extends BaseVUZController{

    protected String _okToAddQuestion = "Внимание, добавление новых данных приведёт к утрате несохранённых изменений. Продолжить?";
    protected String _okToEditQuestion = "Внимание, редактирование этого элемента приведёт к утрате несохранённых данных. Продолжить?";
    protected String _okToDeleteQuestion = "Подтвердить удаление?";
    protected String _okToCancelQuestion = "Вы действительно хотите отменить эти изменения?";

    protected boolean EnableFields=true;
    public com.salmonllc.jsp.JspDetailFormDisplayBox _d;
    public com.salmonllc.jsp.JspSearchFormDisplayBox _searchformdisplaybox1;

    public void initialize() {
        super.initialize();
		 addPageListener(this);
        _d.getSaveButton().addSubmitListener(this);
        _d.getDeleteButton().insertSubmitListener(this);
        _d.getAddButton().addSubmitListener(this);
        _d.setOkToDeleteQuestion(_okToDeleteQuestion);
        _d.setOkToAddQuestion(_okToAddQuestion);
        _d.setOkToCancelQuestion(_okToCancelQuestion);
        _d.setOkToEditQuestion(_okToEditQuestion);
	}

    public void pageRequested(PageEvent p) throws Exception{

        super.pageRequested(p);

        if(login!=null && (login.getRoleId().intValue() == roleIdAdmin || login.getRoleId().intValue() == roleIdDecanat)){
            //
            _d.getSaveButton().setVisible(true);
            _d.getAddButton().setVisible(true);
            _d.getDeleteButton().setVisible(true);
            _d.getCancelButton().setVisible(true);
            _searchformdisplaybox1.getAddButton().setVisible(true);
            EnableFields=true;
        }
        else{
             _d.getSaveButton().setVisible(false);
             _d.getAddButton().setVisible(false);
             _d.getDeleteButton().setVisible(false);
             _d.getCancelButton().setVisible(false);
             _searchformdisplaybox1.getAddButton().setVisible(false);
             EnableFields=false;
        }
        _d.setEnabled(EnableFields);
    }
}
