package com.itm.vuz.examlist.models;

import com.itm.vuz.common.models.JasperBeanDataSource;
import com.salmonllc.sql.BeanDataStore;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRException;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 30.07.2006
 * Time: 22:10:23
 */
public class ExamListJasperBeanDataSource extends JasperBeanDataSource{
    public ExamListJasperBeanDataSource(BeanDataStore _ds) {
        super(_ds);
    }

    public Object getFieldValue(JRField jrField) throws JRException {
        Object obj = super.getFieldValue(jrField);
        return (obj == null) ? "" : obj.toString();
    }
}
