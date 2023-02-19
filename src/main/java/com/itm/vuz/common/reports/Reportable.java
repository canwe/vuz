/*
 * Galantis Framework
 *
 * Copyright (C) 2005. All Rights Reserved.
 * 
 * $Id$
 * Created on 06.06.2006 21:18:31
 * Last modification $Date$
 */

package com.itm.vuz.common.reports;

import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * defines design/print report methods
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public interface Reportable {

  /**
   * design jasper report
   * @return
   */
  JasperDesign designReport(); 

}
