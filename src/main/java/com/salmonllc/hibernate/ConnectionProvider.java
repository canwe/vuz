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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import com.salmonllc.sql.DBConnection;
import com.salmonllc.util.ApplicationContext;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.service.UnknownUnwrapTypeException;

import javax.sql.DataSource;

/**
 * A connection provider that allows Hibernate to use SOFIA's connection pool

 * To use this provider add this line to the hibernate configuration xml

 * 		<property name="connection.provider_class">com.salmonllc.hibernate.ConnectionProvider</property>
 */
public class ConnectionProvider implements org.hibernate.engine.jdbc.connections.spi.ConnectionProvider {

	Hashtable _openConnections=new Hashtable();	
	String _appName,_profile;
	/**
	 * 
	 */
	public ConnectionProvider() {
		super();
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.connection.ConnectionProvider#configure(java.util.Properties)
	 */
	public void configure(Properties arg0) throws HibernateException {
		_appName=arg0.getProperty("sofia.app.name");
		_profile=arg0.getProperty("sofia.dbprofile");
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.connection.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		DBConnection conn=null;
		if (_appName != null)  
			conn=DBConnection.getConnection(_appName,_profile);
		else {	
			ApplicationContext cont=ApplicationContext.getContext();
			conn=DBConnection.getConnection(cont.getAppID(), _profile);
		}	
		_openConnections.put(conn.getJDBCConnection(),conn);
		return conn.getJDBCConnection();
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.connection.ConnectionProvider#closeConnection(java.sql.Connection)
	 */
	public void closeConnection(Connection arg0) throws SQLException {
		DBConnection conn=(DBConnection)_openConnections.get(arg0);
		if (conn != null) {
			conn.freeConnection();
			_openConnections.remove(arg0);
		}	
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return true;
	}

	/* (non-Javadoc)
	 * @see net.sf.hibernate.connection.ConnectionProvider#close()
	 */
	public void close() throws HibernateException {
		Enumeration e=_openConnections.elements();
		while (e.hasMoreElements()) {
			DBConnection conn = (DBConnection) e.nextElement();
			conn.freeConnection();
		}	
	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return org.hibernate.engine.jdbc.connections.spi.ConnectionProvider.class.equals( unwrapType ) ||
				ConnectionProvider.class.isAssignableFrom( unwrapType );
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		if ( org.hibernate.engine.jdbc.connections.spi.ConnectionProvider.class.equals( unwrapType ) ||
				ConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}
}
