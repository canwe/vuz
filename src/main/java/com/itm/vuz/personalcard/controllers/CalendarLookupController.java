package com.itm.vuz.personalcard.controllers;
//The Salmon Open Framework for Internet Applications (SOFIA)
//Copyright (C) 1999 - 2002, Salmon LLC
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

//Salmon import statements
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.salmonllc.jsp.*;
import com.salmonllc.html.HtmlLookUpComponent;
import com.salmonllc.html.HtmlScriptGenerator;
import com.salmonllc.html.events.*;

/**
 * This controller is for the calendar popup window. It is used to set the current date of the 
 * calendar to the one specified in the lookup component
 */
public class CalendarLookupController extends JspController implements PageListener, SubmitListener {

	//Visual Components
	public com.salmonllc.html.HtmlCalendar _calendar1;
    public JspLink _cancel;
    public JspLink _today;

	/**
	 * Initialize the page. Set up listeners and perform other initialization activities.
	 */
	public void initialize() {
		addPageListener(this);
		_today.addSubmitListener(this);
	}


	/**
	 * Set the month and year from the selected month and year in the calendar
	 * @param event the page event to be processed
	 */
	public void pageRequested(PageEvent event) {
		if (! isReferredByCurrentPage()) {
			
			//set the appropriate script for the cancel button
			HtmlScriptGenerator gen = new HtmlScriptGenerator(this);
			_cancel.setHref("javascript:" + gen.generateCancelLookupScript());

			//set the month and year
			String val=HtmlLookUpComponent.getParentLookupValue(this);
			if (val != null) {
				String format=HtmlLookUpComponent.getParentLookupFormat(this);
				SimpleDateFormat df=new SimpleDateFormat(format);
				try {
					Date d = df.parse(val);
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTime(d);
					_calendar1.setCalendarYear(cal.get(GregorianCalendar.YEAR));
					_calendar1.setCalendarMonth(cal.get(GregorianCalendar.MONTH));
				} catch (ParseException e) {}
			}	
		}	
	}

	public void pageRequestEnd(PageEvent event) {
	}
	public void pageSubmitEnd(PageEvent event) {
	}
	public void pageSubmitted(PageEvent event) {
	}

	public boolean submitPerformed(SubmitEvent e) throws Exception {
		HtmlLookUpComponent comp=getPopupLookupComponent();
		String lookupFormat = comp.getEditField().getDisplayFormat();
		HtmlScriptGenerator gen = new HtmlScriptGenerator(this);
		String dtString = null;
		Date d = new Date(System.currentTimeMillis());
		if (lookupFormat != null) {
			SimpleDateFormat df = new SimpleDateFormat(lookupFormat);
			dtString = df.format(d);
		} else {
			dtString = d.toString();
		}
		writeScript(gen.generateReturnValueToLookupScript(dtString,""));
		return true;
	}

}
