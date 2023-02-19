//** Copyright Statement ***************************************************
//The Salmon Open Framework for Internet Applications (SOFIA)
//Copyright (C) 1999 - 2004, Salmon LLC
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License version 2
//as published by the Free Software Foundation;
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program; if not, write to the Free Software
//Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//For more information please visit http://www.salmonllc.com
//** End Copyright Statement ***************************************************
package com.salmonllc.hibernate;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import com.salmonllc.properties.Props;
import com.salmonllc.sql.AutoRetrieveCriteria;
import com.salmonllc.sql.BeanDataStore;
import com.salmonllc.sql.ColumnDefinition;
import com.salmonllc.sql.DSColumnDescriptor;
import com.salmonllc.sql.DSDataRow;
import com.salmonllc.sql.DSDataStoreDescriptor;
import com.salmonllc.sql.DataStoreBuffer;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreQBEInterface;
import com.salmonllc.util.MessageLog;
import com.salmonllc.util.Util;

/**
 * A bean datastore that can automatically retrieve and persist data via
 * Hibernate HQL and session API
 *  
 */
public class HibernateDataStore extends BeanDataStore implements DataStoreQBEInterface {
	private HibernateException _updateException;
	private BeanClassInfo _beanInfo[] = new BeanClassInfo[0];
	private BeanClassInfo _beanFrom[] = new BeanClassInfo[0];
	private String _where, _order, _from, _join;
	private boolean _distinct=true;

	/**
	 * Construct a new Hibernate DataStore. The DataStore must then be
	 * configured by calling addClass, setFrom, setWhere, setOrder, setJoin,
	 * etc.. before it can be used.
	 */
	public HibernateDataStore() {
		super();
		initValues();
	}

	/**
	 * Constuct a new HibernateDataStore passing a BeanDataStore.BeanClassInfo
	 * object and a string of extra information from the JSP tag
	 * 
	 * @throws ClassNotFoundException
	 */
	public HibernateDataStore(BeanClassInfo inf, String extraInfo) throws ClassNotFoundException {
		super(inf);
		initValues();
		_beanInfo = new BeanClassInfo[1];
		_beanInfo[0] = inf;
		inf.setIndex(0);
		parseExtraInfo(extraInfo);
	}

	/**
	 * Constuct a new HibernateDataStore passing an array of
	 * BeanDataStore.BeanClassInfo objects (so the DataStore can handle multiple
	 * objects in it's select) and a string of extra information from the JSP
	 * tag
	 * 
	 * @throws ClassNotFoundException
	 */

	public HibernateDataStore(BeanClassInfo inf[], String extraInfo) throws ClassNotFoundException {
		super(inf);
		initValues();
		_beanInfo = inf;
		parseExtraInfo(extraInfo);
	}

	protected void initValues() {
		setBooleanDataType(DataStoreBuffer.DATATYPE_STRING,"true","false");
		setLowerCaseFirstLetter(true);
	}	
	/**
	 * Loads the data into the DataStore
	 * 
	 * @param criteriaString:
	 *            The selection criteria to use or null for all possible values
	 */
	public void retrieve(String criteriaString) throws DataStoreException {
		String query = "select ";
		if (_distinct)
			query += "distinct ";
		String comma = "";
		for (int i = 0; i < _beanInfo.length; i++) {
			query += comma + _beanInfo[i].getAlias();
			comma = ",";
		}
		query = buildQuery(query, criteriaString);

		MessageLog.writeSQLMessage("HQL Query:", query, this);
		try {
			execute(query);
		} catch (Exception ex) {
			throw new DataStoreException(ex.getMessage(), ex);
		}
	}

	private String buildQuery(String query, String criteriaString) {
		query += " from ";
		String comma = "";
		for (int i = 0; i < _beanInfo.length; i++) {
			query += comma + _beanInfo[i].getBeanClass().getName() + " " + _beanInfo[i].getAlias();
			comma = ", ";
		}
		if (_from != null)
			query += ", " + _from;
		if (_join != null)
			query += " " + _join;

		if (criteriaString != null || _where != null)
			query += " where ";
		if (_where != null)
			query += _where;
		if (criteriaString != null)
			query += (_where == null ? criteriaString : (" and " + criteriaString));
		if (_order != null)
			query += " order by " + _order;
		return query;
	}
	/**
	 * Execute an arbitrary HSQL query, loading the results into the datastore
	 * buffer. The objects retrieved must match the classes specifed when the
	 * datastore was created.
	 * 
	 * @throws Exception
	 */
	public void execute(String query) throws Exception {
		Session sess = HibernateSessionFactory.getSession();
		Query q = sess.createQuery(query);
		List l = q.list();
		reset();
		insertRows(l);
	}

	/**
	 * Loads the data into the DataStore
	 */
	public void retrieve() throws Exception {
		retrieve(null);
	}
	/**
	 * Persists the changes made to the database back to the beans and also to
	 * the database. Transaction's will be handled automatically from within the
	 * datastore.
	 */
	public synchronized void update() throws Exception {
		update(true);
	}

	/**
	 * Persists the changes made to the database back to the beans and also to
	 * the database. Transaction's will be handled automatically from within the
	 * datastore if the handleTrans argument is true, otherwise you must handle
	 * it yourself outside the datastore using the Hibernate session object.
	 */
	public void update(boolean handleTrans) throws Exception {
		_updateException = null;
		Session sess = HibernateSessionFactory.getSession();
		Transaction trans = null;
		if (handleTrans)
			trans = sess.beginTransaction();

		super.update(false);
		try {
			sess.flush();
		} catch (HibernateException ex) {
			_updateException = ex;
		}

		if (trans != null) {
			if (_updateException == null) {
				trans.commit();
				DSDataStoreDescriptor desc=getDescriptor();
				for (int i=0; i < getRowCount();i++) {
					if (getRowStatus(i)==STATUS_NEW_MODIFIED || getRowStatus(i)==STATUS_MODIFIED) {
						Object o=getBean(i);
						DSDataRow r=getDataRow(i);
						r.populateFromBean(desc,o,STATUS_NOT_MODIFIED);
					}	
				}	
				resetStatus();
			} else
				trans.rollback();
		}
		if (_updateException != null)
			throw (_updateException);
	}

	/**
	 * Called when a bean should be deleted from the database, subclasses may
	 * override
	 */

	public void beanDeleted(Object bean) {
		try {
			Session sess = HibernateSessionFactory.getSession();
			sess.delete(bean);
		} catch (HibernateException e) {
			_updateException = e;
		}
	}

	/**
	 * Called when a bean should be added or updated in the database, subclasses
	 * may override
	 */
	public void beanUpdated(Object bean, boolean newBean) throws Exception {
		try {
			Session sess = HibernateSessionFactory.getSession();
			if (newBean)
				sess.save(bean);
			else
				sess.update(bean);
		} catch (HibernateException e) {
			_updateException = e;
		}

	}

	/**
	 * Builds selection criteria for the primary key field for the specified row
	 */
	public String buildCriteriaStringForRow(int row) {
		try {
			SessionFactory fact = HibernateSessionFactory.getSessionFactory();
			Session sess = HibernateSessionFactory.getSession();
			String exp = "";
			String and = "";
			for (int i = 0; i < _beanInfo.length; i++) {
				ClassMetadata md = fact.getClassMetadata(_beanInfo[i].getBeanClass());
				Object bean = getBean(row);
				if (bean instanceof Object[])
					bean = ((Object[]) bean)[_beanInfo[i].getIndex()];
				if (bean != null) {
					Object id = md.getIdentifier(bean);
					String propName = md.getIdentifierPropertyName();
					String fragment = org.hibernate.criterion.Expression.eq(_beanInfo[i].getAlias() + "." + propName, id).toString();
					exp += fragment + and;
					and = " and ";
				}
			}
			return exp;
		} catch (HibernateException e) {
			MessageLog.writeErrorMessage("buildCriteriaStringForRow()", e, this);
		} catch (DataStoreException e) {
			MessageLog.writeErrorMessage("buildCriteriaStringForRow()", e, this);
		}

		return super.buildCriteriaStringForRow(row);
	}

	/**
	 * Builds and loads the datastore using AutoRetrieve selection criteria from
	 * the JSP
	 */
	public void autoRetrieve() throws Exception {
		AutoRetrieveCriteria crit = getAutoRetrieveCriteria();
		if (crit == null || crit.getCriteriaCount() == 0)
			retrieve();
		else {
			String critString = buildCriteriaString(crit);
			retrieve(critString);
		}
	}



	private static String buildCriteriaString(AutoRetrieveCriteria crit) {
		StringBuffer ret = new StringBuffer();

		for (int i = 0; i < crit.getCriteriaCount(); i++) {
			if (crit.getPrefix(i) != null)
				ret.append(crit.getPrefix(i));
			ret.append(lowerCaseFirstLetter(crit.getColumn(i)));
			ret.append(' ');
			ret.append(crit.getOperator(i));
			ret.append(' ');
			ret.append(crit.getValue(i));
			if (crit.getSuffix(i) != null)
				ret.append(crit.getSuffix(i));
			if (crit.getConnector(i) != null) {
				ret.append(' ');
				ret.append(crit.getConnector(i));
				ret.append(' ');
			}
		}

		if (ret.length() == 0)
			return null;
		else
			return ret.toString();
	}

	/**
	 * Get a list of aliaes for the objects used in this datastore
	 */
	public String[] getTableList(boolean updateable) {
		String ret[] = new String[_beanInfo.length];
		for (int i = 0; i < _beanInfo.length; i++)
			ret[i] = _beanInfo[i].getAlias();
		return ret;
	}

	/**
	 * For HQL joins in the from clause of the query. Joins may also go in the
	 * where clause as well.
	 */
	public String getJoin() {
		return _join;
	}
	/**
	 * For HQL joins in the from clause of the query. Joins may also go in the
	 * where clause as well.
	 */
	public void setJoin(String join) {
		_join = join;
	}

	/**
	 * the order by clause for the HQL query
	 */
	public String getOrderBy() {
		return _order;
	}
	/**
	 * the order by clause for the HQL query
	 */
	public void setOrderBy(String order) {
		_order = order;
	}
	/**
	 * the from clause for the HQL query. Should contain tables and aliases for
	 * this query for classes that are being used to query the data (joins,
	 * selection criteria), but not actually loaded when the query is executed.
	 * Classes that should be loaded should be specified using the addBean
	 * method
	 */
	public String getFrom() {
		return _from;
	}
	/**
	 * the from clause for the HQL query. Should contain tables and aliases for
	 * this query for classes that are being used to query the data (joins,
	 * selection criteria), but not actually loaded when the query is executed.
	 * Classes that should be loaded should be specified using the addBean
	 * method
	 * 
	 * @throws ClassNotFoundException
	 */
	public void setFrom(String from) throws ClassNotFoundException {
		_from = from;
		if (_from == null || from.length() == 0)
			_beanFrom = new BeanClassInfo[0];
		else {
			ArrayList l = new ArrayList();
			StringTokenizer tok = new StringTokenizer(from, ",");
			while (tok.hasMoreTokens()) {
				String tab = tok.nextToken();
				int pos = tab.indexOf(" ");
				String alias = null;
				if (pos != -1) {
					alias = tab.substring(pos + 1);
					tab = tab.substring(0, pos);
				}
				Class c = Class.forName(tab);
				BeanClassInfo inf = newBeanInfo(c, alias, -1);
				l.add(inf);
			}
			_beanFrom = new BeanClassInfo[l.size()];
			l.toArray(_beanFrom);
		}
	}

	/**
	 * selection or join criteria used when this datastore is loaded
	 */
	public String getWhere() {
		return _where;
	}

	/**
	 * selection or join criteria used when this datastore is loaded
	 */
	public void setWhere(String where) {
		_where = where;
	}

	/**
	 * Add a bean to the DataStore's buffer. The contents of each bean added
	 * will be loaded when retrieve() is called.
	 * @param beanType The class name for the bean
	 * @param alias The name the bean is referenced by
	 */ 
	public void addBeanDefinition(Class beanType, String alias) {
		if (_beanInfo == null || _beanInfo.length==0) {
			_beanInfo = new BeanClassInfo[1];
			_beanInfo[0] = newBeanInfo(beanType, alias, 0);
		} else {
			BeanClassInfo inf[] = new BeanClassInfo[_beanInfo.length + 1];
			System.arraycopy(_beanInfo, 0, inf, 0, _beanInfo.length);
			inf[inf.length - 1] = newBeanInfo(beanType, alias, inf.length - 1);
			_beanInfo = inf;
		}
		super.addBeanDefinition(beanType, alias);
	}

	/**
	 * Parses an beanextrainfo attribute string from the datasource into from,where, orderby etc...
	 */
	protected void parseExtraInfo(String extraInfo) {
		if (extraInfo == null)
			return;
		extraInfo = " " + extraInfo;
		String work = extraInfo.toUpperCase();
		int fromPos = work.indexOf(" FROM ");
		int wherePos = work.indexOf(" WHERE ");
		int orderPos = work.indexOf(" ORDER BY ");
		int joinPos = -1;
		int jp = work.indexOf(" LEFT JOIN ");
		if (jp != -1 && (jp < joinPos || joinPos == -1))
			joinPos = jp;
		jp = work.indexOf(" LEFT OUTER JOIN ");
		if (jp != -1 && (jp < joinPos || joinPos == -1))
			joinPos = jp;
		jp = work.indexOf(" INNER JOIN ");
		if (jp != -1 && (jp < joinPos || joinPos == -1))
			joinPos = jp;
		jp = work.indexOf(" JOIN ");
		if (jp != -1 && (jp < joinPos || joinPos == -1))
			joinPos = jp;

		if (fromPos != -1) {
			int end = findEnd(fromPos, wherePos, orderPos, joinPos, extraInfo.length());
			_from = extraInfo.substring(fromPos + 6, end);
		}
		if (wherePos != -1) {
			int end = findEnd(wherePos, fromPos, orderPos, joinPos, extraInfo.length());
			_where = extraInfo.substring(wherePos + 7, end);
		}
		if (orderPos != -1) {
			int end = findEnd(orderPos, fromPos, wherePos, joinPos, extraInfo.length());
			_order = extraInfo.substring(orderPos + 10, end);
		}
		if (joinPos != -1) {
			int end = findEnd(joinPos, fromPos, wherePos, orderPos, extraInfo.length());
			_join = extraInfo.substring(joinPos, end);
		}
	}

	private int findEnd(int start, int pos1, int pos2, int pos3, int length) {
		int end = length;

		if (pos1 > start)
			end = pos1;

		if (pos2 > start && pos2 < end)
			end = pos2;

		if (pos3 > start && pos3 < end)
			end = pos3;

		return end;
	}

	/**
	 * Returns the number of classes the datastore accesses
	 */
	public int getAliasCount() {
		return _beanFrom.length + _beanInfo.length;
	}

	/**
	 * Returns Props.DBMSTYPE_HIBERNATE
	 */
	public String getDBMS() {
		return Props.DBMSTYPE_HIBERNATE;
	}

	/**
	 * Returns the alias used for a particular class
	 */
	public String getAlias(int tableNo) throws DataStoreException {
		BeanClassInfo inf = getBeanInfo(tableNo);
		if (inf == null)
			return null;
		else
			return inf.getAlias();
	}

	/**
	 * Returns the class name at the specified index
	 */
	public String getTable(int tableNo) throws DataStoreException {
		BeanClassInfo inf = getBeanInfo(tableNo);
		if (inf == null)
			return null;
		else
			return inf.getBeanClass().getName();
	}

	private BeanClassInfo getBeanInfo(int tableNo) {
		if (tableNo < _beanInfo.length)
			return _beanInfo[tableNo];
		else {
			tableNo = tableNo - _beanInfo.length;
			if (tableNo < _beanFrom.length)
				return _beanFrom[tableNo];
		}
		return null;
	}
	/**
	 * Returns the name of the specified column in the database query
	 */
	public String getColumnDatabaseName(int col) throws DataStoreException {
		DSDataStoreDescriptor desc = getDescriptor();
		DSColumnDescriptor colDesc = desc.getColumn(col);
		return lowerCaseFirstLetter(colDesc.getInternalName());
	}

	/**
	 * @see DataStoreQBEInterface#getColumnTableName(int)
	 */
	public String getColumnTableName(int col) throws DataStoreException {
		DSDataStoreDescriptor desc = getDescriptor();
		DSColumnDescriptor colDesc = desc.getColumn(col);
		return colDesc.getBeanInfo().getAlias();
	}

	/**
	 * Return all the available attributes for a particular class
	 */
	public ColumnDefinition[] getColumnsForTable(String table) {
		ArrayList l=new ArrayList();
		getColumnsForTable(table,l);
		ColumnDefinition def[]= new ColumnDefinition[l.size()];
		l.toArray(def);
		return def;
	}
	
	private void getColumnsForTable(String table,  ArrayList l) {
		try {
			SessionFactory fact = HibernateSessionFactory.getSessionFactory();
			int count = getAliasCount();
			Class c = null;
			String searchTable=table;
			for (int i = 0; i < count; i++) {
				if (getTable(i).equals(searchTable)) {
					BeanClassInfo inf = getBeanInfo(i); 
					c = inf.getBeanClass();
					break;
				}
			}

			if (c==null) {
				DSDataStoreDescriptor desc=getDescriptor();
				int colCount=desc.getColumnCount();
				for (int i=0; i < colCount; i++) {
					DSColumnDescriptor colDesc=desc.getColumn(i);
					String colName=colDesc.getInternalName();
					if (colName.startsWith(table)) {
						int dotCount=0;
						for (int j=table.length(); j < colName.length(); j++) {
							if (colName.charAt(j)=='.')
								dotCount++;
						}	
						if (dotCount==1) {
							Method parentGetters[]=colDesc.getParentGetMethods();
							if (parentGetters != null && parentGetters.length > 0) {
								c=parentGetters[parentGetters.length - 1].getReturnType();
								table=lowerCaseFirstLetter(table);
								break;
							}	
						}	
					}	
				}	
			}
			
			if (c==null)
				return;

			ClassMetadata md = fact.getClassMetadata(c);
			String names[] = md.getPropertyNames();
			Type types[] = md.getPropertyTypes();
			String tableName = table;

			for (int i = 0; i < names.length; i++) {
				ColumnDefinition def=new ColumnDefinition(tableName, names[i], getDsType(types[i].getReturnedClass()), false);
				l.add(def);
			}	
			ColumnDefinition def=new ColumnDefinition(tableName, md.getIdentifierPropertyName(), getDsType(md.getIdentifierType().getReturnedClass()),true);
			
			l.add(def);
			return;
		} catch (HibernateException e) {
			MessageLog.writeErrorMessage("getColumnsForTable()", e, this);
		} catch (DataStoreException e) {
			MessageLog.writeErrorMessage("getColumnsForTable()", e, this);
		}
		return;	
	}	
	private int getDsType(Class c) {
		if (Util.instanceOf(c, Integer.class) || Util.instanceOf(c, Integer.TYPE))
			return DATATYPE_INT;
		else if (Util.instanceOf(c, String.class))
			return DATATYPE_STRING;
		else if (Util.instanceOf(c, Character.class) || Util.instanceOf(c, Character.TYPE))
			return DATATYPE_STRING;
		else if (Util.instanceOf(c, Float.class) || Util.instanceOf(c, Float.TYPE))
			return DATATYPE_FLOAT;
		else if (Util.instanceOf(c, Double.class) || Util.instanceOf(c, Double.TYPE))
			return DATATYPE_DOUBLE;
		else if (Util.instanceOf(c, Long.class) || Util.instanceOf(c, Long.TYPE))
			return DATATYPE_LONG;
		else if (c.isAssignableFrom(byte[].class))
			return DATATYPE_BYTEARRAY;
		else if (Util.instanceOf(c, Short.class) || Util.instanceOf(c, Short.TYPE))
			return DATATYPE_SHORT;
		else if (Util.instanceOf(c, Boolean.class) || Util.instanceOf(c, Boolean.TYPE))
			return DATATYPE_ANY;
		else if (Util.instanceOf(c, java.sql.Timestamp.class))
			return DATATYPE_DATETIME;
		else if (Util.instanceOf(c, java.sql.Time.class))
			return DATATYPE_TIME;
		else if (Util.instanceOf(c, java.util.Date.class))
			return DATATYPE_DATE;
		else if (Util.instanceOf(c, BigDecimal.class))
			return DATATYPE_DOUBLE;
		else
			return DATATYPE_ANY;
	}

	/**
	 * Estimate the number of rows retrieved for a particular selection criteria
	 * string
	 */
	public int estimateRowsRetrieved(String criteria) throws Exception {
		String query = buildQuery("select count(*)", criteria);
		Session sess = HibernateSessionFactory.getSession();
		MessageLog.writeSQLMessage("HQL Query:", query, this);
		Integer count = (Integer) sess.createQuery(query).uniqueResult();
		return count.intValue();
	}
	
	/**
	 * Override this method to provide your own creation logic when a new bean is added to the datastore
	 * @return the bean to add
	 */
	protected Object createBean() {
		int count=_beanInfo.length;
		if (count == 1) {
			Class c=_beanInfo[0].getBeanClass();
			try {
				return c.newInstance();
			} catch (Exception e) {
				MessageLog.writeErrorMessage("createBean()",e,this);
				return null;
			} 
		}
		else {
			Object o[]=new Object[count];
			for (int i=0; i < count; i++) {
				Class c=_beanInfo[i].getBeanClass();
				try {
					o[i]=c.newInstance();
				} catch (Exception e) {
					MessageLog.writeErrorMessage("createBean()",e,this);
					return null;
				} 
				return o;
			}	
		}	
		return null;
	}
	
	public int insertRow() {
		return insertRow(createBean(),true);
	}
	public int insertRow(int atPosition) {
		return insertRow(createBean(),atPosition,true);
	}

	protected int getDataTypeForMethod(Class beanType, Method getMethod) {
		String methName=getMethod.getName();
		String attName=null;
		if (methName.startsWith("get"))
			attName=methName.substring(3);
		else
			attName=methName.substring(2);
		attName=Character.toLowerCase(attName.charAt(0)) + attName.substring(1);
		try {
			SessionFactory fact=HibernateSessionFactory.getSessionFactory();
			ClassMetadata md=fact.getClassMetadata(beanType);
			Type t=null;
			if (attName.equals(md.getIdentifierPropertyName()))
				t=md.getIdentifierType();
			else
				t=md.getPropertyType(attName);
			return getDsType(t.getReturnedClass());
		} catch (HibernateException e) {
			MessageLog.writeErrorMessage("getDataTypeForMethod()",e,this);
			return -1;
		}
	}
	/**
	 * @return Returns whether or not a distinct will be added before each query.
	 */
	public boolean getDistinct() {
		return _distinct;
	}
	/**
	 * @return sets whether or not a distinct will be added before each query.
	 */
	public void setDistinct(boolean distinct) {
		_distinct = distinct;
	}
}