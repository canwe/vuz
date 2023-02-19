/*
 * $Id$
 * Created on 17.05.2006 10:07:14
 * Last modification $Date$
 */

package com.itm.vuz.groupcard.models;

import com.itm.vuz.groupcard.GroupCardStudent;
import com.salmonllc.sql.BeanDataStore;
import com.salmonllc.sql.Remotable;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreProxy;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardBeanModel extends BeanDataStore implements Remotable{

    public GroupCardBeanModel() {
        super();
        addBeanDefinition(GroupCardStudent.class, "Student");

    }
  //$CUSTOMMETHODS$
  //Put custom methods between these comments, otherwise they will be overwritten if the model is regenerated

/**
 * By implementing Remoteable, the model can be used from a DataStoreProxy, which is required for the SOFIA Jasper Reports viewer.
 */

 public boolean request(String reqType, HttpServletRequest req, boolean sessionValid, String userID, String password, String criteria) throws DataStoreException {
  //this model is used for reports, allow read requests only, no updates
  if (reqType.equals(DataStoreProxy.OPERATION_UPDATE))
    return false;
  else
    return true;
}
  //$ENDCUSTOMMETHODS$




}
