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

import java.io.File;
import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.salmonllc.util.MessageLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.salmonllc.html.events.ServletServiceListener;
import com.salmonllc.jsp.JspServlet;
import com.salmonllc.properties.Props;
import com.salmonllc.util.ApplicationContext;

/**
 * A class that allows you to create cached versions of the hibernate session and session factory object
 * This uses two properties in the SOFIA properties file to configure hibernate
 * 
 * HibernateFactoryClass is the class name of a subclass of this factory. This is useful if you want
 * to add your own HibernateFactory funcionality to SOFIA
 * HibernateConfigFile is the directory and path name for the hibernate configuration file.  If
 * the property is not specified, SOFIA will look for /WEB-INF/properties/hibernate/hibernate.cfg.xml under your
 * web application to configure hibernate.
 */
public class HibernateSessionFactory implements ServletServiceListener {
	private static Hashtable _factories = new Hashtable();
	private static ThreadLocal _session = new ThreadLocal();
	
	/**
	 * Returns the shared hibernate Session object for this page request.
	 */
	public static Session getSession() throws HibernateException {
		if (_session.get() == null)
			_session.set(getSessionFactory().openSession());

		return (Session)_session.get();
	}

	/**
	 * Returns the HibernateSessionFactory for this web application
	 */
	public static SessionFactory getSessionFactory() throws HibernateException {
		ApplicationContext cont = ApplicationContext.getContext();
		String appName = cont.getAppID();
		SessionFactory fact = (SessionFactory) _factories.get(appName);
		if (fact != null)
			return fact;

		synchronized (_factories) {
			//try again in case the factory has been created after sync
			fact = (SessionFactory) _factories.get(appName);
			if (fact != null)
				return fact;
			
			Props p = Props.getProps(appName, null);
			String clazz = p.getProperty(Props.HIBERNATE_FACTORY_CLASS);

			HibernateSessionFactory prov = null;
			if (clazz != null) {
				try {
					Class c = Class.forName(clazz);
					prov = (HibernateSessionFactory) c.newInstance();
				} catch (Exception ex) {
					throw new HibernateException("Error creating class:" + clazz, ex);
				}
			} else {
				prov = new HibernateSessionFactory();
			}

			try {
				fact = prov.configureFactory();
				JspServlet.addServiceListener(prov);
				_factories.put(appName, fact);
			} catch (Exception e) {
				MessageLog.writeErrorMessage("getSessionFactory()", e, new Object());
				throw new HibernateException("Error creating session factory!");
			}

			return fact;
		}
	}

	/**
	 * Subclasses can override to provide custom Hibernate configuration logic
	 */
	protected SessionFactory configureFactory() throws HibernateException {
		ApplicationContext cont = ApplicationContext.getContext();
		Props p = Props.getProps(cont.getAppID(), null);
		String configFile = p.getProperty(Props.HIBERNATE_CONFIG_FILE);
		File f = null;
		if (configFile == null) {
			configFile = cont.getAppDocumentRoot() + File.separator + "WEB-INF" + File.separator + "properties" + File.separator + "Hibernate" + File.separator + "hibernate.cfg.xml";
			f = new File(configFile);
		} else {
			f = new File(configFile);
			if (!f.exists() && configFile.startsWith(File.separator))
				f = new File(cont.getAppDocumentRoot() + configFile);
		}

		try {
			Configuration cfg = new Configuration();
			cfg = cfg.configure(f);
			return cfg.buildSessionFactory();
		} catch (Exception e) {
			MessageLog.writeErrorMessage("configureFactory()", e, this);
		}
		throw new HibernateException("Error configuring session factory!");
	}


	public void serviceStarted(Servlet serv, HttpServletRequest req, HttpServletResponse res) throws Exception {
		

	}

	public void serviceEnded(Servlet serv, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (_session.get() != null) {
			Session s = ((Session)_session.get());
			s.close();
			_session.set(null);
		}	
	}

}