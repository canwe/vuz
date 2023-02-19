
 //** Copyright Statement ***************************************************
 //The Salmon Open Framework for Internet Applications (SOFIA)
 // Copyright (C) 1999 - 2002, Salmon LLC
 //
 // This program is free software; you can redistribute it and/or
 // modify it under the terms of the GNU General Public License version 2
 // as published by the Free Software Foundation;
 //
 // This program is distributed in the hope that it will be useful,
 // but WITHOUT ANY WARRANTY; without even the implied warranty of
 // MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 // GNU General Public License for more details.
 //
 // You should have received a copy of the GNU General Public License
 // along with this program; if not, write to the Free Software
 // Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 //
 // For more information please visit http://www.salmonllc.com
 //** End Copyright Statement ***************************************************

package com.salmonllc.wml;

/////////////////////////
//$Archive: /SOFIA/SourceCode/com/salmonllc/wml/WmlSubmit.java $
//$Author: Dan $
//$Revision: 5 $
//$Modtime: 6/12/03 12:46p $
/////////////////////////

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.jsp.JspContainer;
import com.salmonllc.jsp.JspController;
import com.salmonllc.jsp.tags.PageTag;
import com.salmonllc.sql.*;
import com.salmonllc.util.MessageLog;
import com.salmonllc.util.Util;

/**
 * This container will construct an anchor tag with a html link (HREF) reference.
 */
public class WmlSubmit extends JspContainer {
    private DataStoreEvaluator _dsEval;
    private int _rowNo = -1;
    private String _class;
    private String _title;
    private WmlCard _card;
    private Vector _listeners;

    /**
     * WmlSubmit constructor comment.
     * @param name The name of the link
     * @param p The page the link will go in.
     */

    public WmlSubmit(String name, com.salmonllc.html.HtmlPage p) {
        super(name, p);
    }

    /**
     *Does the binding for the component. This method is called by the framework and should not be called directly
     */
    public void doBinding() throws Exception {
        String dataSource = getDataSource();
        String dsName = null;
        String dsExp = null;

        if (dataSource == null)
            return;

        int pos = dataSource.indexOf(":");
        if (pos == -1)
            dsName = dataSource;
        else {
            dsName = dataSource.substring(0, pos);
            dsExp = dataSource.substring(pos + 1);
        }

        DataStoreBuffer ds = ((JspController)getPage()).getDataSource(dsName);
        if (ds == null)
            return;

        if (!ds.getAutoBind())
            return;

        setTitleExpression(ds, ((JspController)getPage()).convertExpressionOperators(dsExp));
    }

    /**
     *Generates the Html for the component. This method is called by the framework and should not be called directly
     */
    public void generateHTML(PrintWriter p, int rowNo) throws java.io.IOException {
        StringBuffer sb = new StringBuffer();

        if (!_visible)
            return;
        StringBuffer title = null;
           try {
                if (_dsEval != null) {
                    if (rowNo > -1) {
                        _title = _dsEval.evaluateRowFormat(rowNo);
                    }
                    else {
                        _title = _dsEval.evaluateRowFormat();
                    }
                }
            }
            catch (Exception e) {
            }
            // sr 12-08-2000 was getting a null pointer exception
            if (!Util.isNull(_title)) {
                title = new StringBuffer(_title);
                int titleLength = title.length();
                for (int i = 0; i < titleLength; i++) {
                    if (title.charAt(i) == ' ') {
                        title.setCharAt(i, '+');
                    }
                }
            }
        String row = "";
        if (rowNo != -1)
            row = "_" + row + new Integer(rowNo).toString();
        String href=getPage().getCurrentRequest().getRequestURI();
        int iSlashIndex=href.lastIndexOf('/');
        if (iSlashIndex>=0)
            href=href.substring(iSlashIndex+1);
        StringBuffer sbQuery=new StringBuffer();

        if (getPage().isWMLMaintained())
            sbQuery.append(PageTag.getSessionIdentifier()+"="+PageTag.getWmlSessId(getPage().getSession())+"&amp;");
        if (_card!=null) {
            WmlFormComponent[] wfca=_card.getInputComponents();
            if (wfca!=null) {
                for (int i=0;i<wfca.length;i++) {
                   String sParameter=wfca[i].getName()+"=$("+wfca[i].getName()+")";
                   sbQuery.append(sParameter+"&amp;");
                }
            }
         }
        sbQuery.append("method=post&amp;");
        sbQuery.append(getName()+row+"=1&amp;");
        if (sbQuery.toString().endsWith("&amp;"))
          sbQuery.delete(sbQuery.length()-5,sbQuery.length());


        sb.append("<a");
        sb.append(" id=\"" + getName() + row + "\"");
        if (!sbQuery.toString().trim().equals(""))
            sb.append(" href=\"" + encodeURL(href+"?"+sbQuery.toString()) + "\"");
        else
            sb.append(" href=\"" + encodeURL(href) + "\"");
        if (_title != null)
           sb.append(" title=\"" + _title + "\"");
        if (_class != null)
            sb.append(" class=\"" + _class + "\"");
        sb.append(">");
        sb.append(_title);
        sb.append("</a>");

        p.print(sb.toString());

    }
    /**
     * This method gets the DataStoreEvaluator being used for href expressions.
     * @return DataStoreEvaluator
     * @see DataStoreEvaluator
     */
    public DataStoreEvaluator getTitleExpression() {
        return _dsEval;
    }
    /**
     * Use this method to get the class of the submit link
     */
    public String getClassName() {
        return _class;
    }
    /**
     * Use this method to get the title for the submit link
     */
    public String getTitle() {
        return _title;
    }
    /**
     * This method sets a datastore expression that will be used to compute the href for the link.
     * @param ds com.salmonllc.sql.DataStoreBuffer
     * @param expression The expression to evaluate
     */
    public void setTitleExpression(DataStoreBuffer ds, DataStoreExpression expression) throws Exception {
        try {
            _dsEval = new DataStoreEvaluator(ds, expression);
        }
        catch (Exception e) {
            MessageLog.writeErrorMessage("setHrefExpression(DataStoreBuffer ds, DataStoreExpression expression )", e, this);
            throw e;
        }
    }
    /**
     * This method sets a datastore expression that will be used to compute the href for the link.
     * @param ds com.salmonllc.sql.DataStoreBuffer
     * @param expression java.lang.String
     */
    public void setTitleExpression(DataStoreBuffer ds, String expression) throws Exception {
        try {
            _dsEval = new DataStoreEvaluator(ds, expression);
        }
        catch (Exception e) {
            MessageLog.writeErrorMessage("setHrefExpression(DataStoreBuffer ds, String expression )", e, this);
            throw e;
        }
    }
    /**
     * This method sets the class for the submit link.
     */
    public void setClassName(String className) {
        _class = className;
    }
    /**
     * This method sets the title for the submit link.
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     *Processes the submitted parameters. This method is called by the framework and should not be called directly
     */
    public boolean processParms(Hashtable parms, int rowNo) throws Exception {
         String name = getName();
         if (rowNo > -1)
             name += "_" + rowNo;

         if (parms.get(name) != null) {
             _rowNo = rowNo;
             return true;
         }

         return false;
     }

    /**
     * This method sets the card the submit belongs to.
     */
   public void setCard(WmlCard card) {
        _card=card;
    }


    /**
     * This method adds a listener the will be notified when this button causes the page to be submitted.
     * @param l The listener to add.
     */
    public void addSubmitListener(SubmitListener l) {
        if (_listeners == null)
            _listeners = new Vector();

        for (int i = 0; i < _listeners.size(); i++) {
            if (((SubmitListener) _listeners.elementAt(i)) == l)
                return;
        }

        _listeners.addElement(l);
    }

    /**
     *Executes any events for the component. This method is called by the framework and should not be called directly
     */
    public boolean executeEvent(int type) throws Exception {
        if (type != EVENT_SUBMIT)
            return true;

        if (_listeners == null)
            return true;

        SubmitEvent e = new SubmitEvent(getPage(), this, getName(), getFullName(), _rowNo);

        for (int i = 0; i < _listeners.size(); i++) {
            SubmitListener l = (SubmitListener) _listeners.elementAt(i);
            e.setNextListener( _listeners.size() > (i + 1) ?_listeners.elementAt(i + 1) : null);
            if (!l.submitPerformed(e))
                return false;
        }

        return true;
    }

    /**
     * This method removes a listener from the list that will be notified if this button causes the page to be submitted.
     * @param l The listener to remove.
     */
    public void removeSubmitListener(SubmitListener l) {
        if (_listeners == null)
            return;

        for (int i = 0; i < _listeners.size(); i++) {
            if (((SubmitListener) _listeners.elementAt(i)) == l) {
                _listeners.removeElementAt(i);
                return;
            }
        }
    }

}
