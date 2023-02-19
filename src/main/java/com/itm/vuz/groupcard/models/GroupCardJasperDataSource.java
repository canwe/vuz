package com.itm.vuz.groupcard.models;

import com.itm.vuz.common.models.JasperBeanDataSource;
import com.itm.vuz.groupcard.GroupCardReport;
import com.itm.vuz.groupcard.GroupCardStudent;
import com.salmonllc.sql.BeanDataStore;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.util.MessageLog;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 18.06.2006
 * Time: 11:11:02
 */
public class GroupCardJasperDataSource implements JRDataSource{

    protected BeanDataStore _ds0;
    protected BeanDataStore _ds1;
    boolean _doNext=false;
    SimpleDateFormat dateFormat;

    public GroupCardJasperDataSource(BeanDataStore _ds0, BeanDataStore _ds1) {
        this._ds0 = _ds0;
        this._ds1 = _ds1;
        dateFormat = new SimpleDateFormat("dd.MM");
    }

    public boolean next() throws JRException {
      if (_doNext){
        boolean _doNext0 = _ds0.gotoNext();
        boolean _doNext1 = _ds1.gotoNext();
        return _doNext0 | _doNext1;
      }
      else {
        if (_ds0.getRow() == -1)
          _ds0.gotoFirst();
          if (_ds1.getRow() == -1)
            _ds1.gotoFirst();
        _doNext=true;
        return _ds0.getRowCount() > 0 | _ds1.getRowCount() >0;
      }
    }


    public Object getFieldValue(JRField jrField) throws JRException {
      try {
        BeanDataStore _currentDs = _ds0;
        String _name = jrField.getName();
        //MessageLog.writeDebugMessage("_name = [" + _name + "]", this);
        if(_name.indexOf(GroupCardReport.SECOND_SEMESTR_FLAG)!=-1){
            // second semestr
            _currentDs = _ds1;
            _name = _name.substring(0, _name.indexOf(GroupCardReport.SECOND_SEMESTR_FLAG));
            //MessageLog.writeDebugMessage("new _name = [" + _name + "]", this);
        }
        if(_name.indexOf(GroupCardReport.REPORT_DISCIPLINE_MARK)!=-1){
            String disciplineId = _name.substring(GroupCardReport.REPORT_DISCIPLINE_MARK.length());
            //MessageLog.writeDebugMessage("disciplineId = [" + disciplineId + "]", this);
            int row = _currentDs.getRow();
            GroupCardStudent groupCardRow = (GroupCardStudent)_currentDs.getDataRow(row).getBean();
            return ((groupCardRow.getMarks(Long.valueOf(disciplineId)) == null) ? "" : "" + groupCardRow.getMarks(Long.valueOf(disciplineId)));
        }
        Object val = _currentDs.getAny(_name);
        if(val == null){
            val = new String("");
        }else{
            if(val instanceof Date){
                val = dateFormat.format((Date)val);
            }else{
                val = "" + val;
            }
        }
        //MessageLog.writeDebugMessage("val = [" + val + "]", this);
        return val;
      }
      catch (DataStoreException ex) {
        throw new JRException(ex.getMessage(),ex);
      }
    }

    /**
     * Resets the DataSource so it can be used to fill another report
     */
    public void reset() {
      _doNext=false;
      _ds0.gotoFirst();
      _ds1.gotoFirst();
    }


}
