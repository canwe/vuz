/*
 * Galantis Framework
 *
 * Copyright (C) 2005. All Rights Reserved.
 *
 * $Id$
 * Created on 03.06.2006 18:04:43
 * Last modification $Date$
 */

package com.itm.vuz.common.models;

import com.salmonllc.sql.BeanDataStore;
import com.salmonllc.sql.DataStoreException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Adapter to jasper data source from sofia bean data source
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class JasperBeanDataSource implements JRDataSource {

    protected BeanDataStore _ds;
    boolean _doNext = false;

    public JasperBeanDataSource(BeanDataStore _ds) {
        this._ds = _ds;
    }

    public boolean next() throws JRException {
        if (_doNext)
            return _ds.gotoNext();
        else {
            if (_ds.getRow() == -1)
                _ds.gotoFirst();
            _doNext = true;
            return _ds.getRowCount() > 0;
        }
    }

    public Object getFieldValue(JRField jrField) throws JRException {
        try {
            String _name = jrField.getName();

            return _ds.getAny(_name);
        } catch (DataStoreException ex) {
            throw new JRException(ex.getMessage(), ex);
        }
    }

    /**
     * Resets the DataSource so it can be used to fill another report
     */
    public void reset() {
        _doNext = false;
        _ds.gotoFirst();
    }

}
