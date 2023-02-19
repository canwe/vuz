package com.itm.vuz.common;

import com.salmonllc.html.*;
import com.salmonllc.html.events.PageListener;
import com.salmonllc.html.events.SubmitListener;
import com.salmonllc.html.events.PageEvent;
import com.salmonllc.html.events.SubmitEvent;
import com.salmonllc.jsp.JspController;
import com.salmonllc.util.MessageLog;
import com.salmonllc.sql.DataStoreBuffer;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 13.07.2006
 * Time: 21:21:51
 */
public class CalendarComponent extends HtmlContainer implements PageListener, SubmitListener{

    public static final String ROW_NUM_MACRO = "$ROW_NUM";
    public static final String BTN_NAME_MACRO = "$BTN_NAME";


    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btShowCalendar.setEnabled(enabled);
        btClearDate.setEnabled(enabled);
    }

    public CalendarComponent(String name, HtmlPage page) {
        super(name, page);
        this.name = name;
        _name = name + ROW_NUM_MACRO;

        controller = (JspController)page;
        buildComponent();
    }

    public DataStoreBuffer get_ds() {
        return _ds;
    }

    public void set_ds(DataStoreBuffer _ds) {
        this._ds = _ds;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    private void applyColumn(){
        if(column!=null){
            if(get_ds() != null){
                ed.setColumn(get_ds(), column);
            }
        }else{
            if(datasource!=null && datasource.trim().length() > 0){
                String dataSourceName = datasource.substring(0, datasource.indexOf(":"));
                String columnName = datasource.substring(datasource.indexOf(":")+1);
                DataStoreBuffer ds = controller.getDataSource(dataSourceName);
                ed.setColumn(ds, columnName);
            }
        }
    }

    public String getValue(){
        return ed.getValue();
    }

    public void setValue(String value){
        ed.setValue(value);
    }

    public DateFormat getFormat(){
        return DATE_FORMAT;
    }


    private void buildComponent(){
        ed = new HtmlTextEdit(name + "Edit", controller);
        ed.setDisplayFormat("yyyy-MM-dd");
        //edHidden = new HtmlHiddenField(name + "Edit" + name + "Hidden", null, controller);
        MessageLog.writeDebugMessage("datasource = [" + datasource + "]", this);

        ed.setReadOnly(Boolean.valueOf(readonly).booleanValue());
        ed.setSize(Integer.valueOf(size).intValue());
        ed.setMaxLength(Integer.valueOf(maxlength).intValue());
        btShowCalendarName = _name + "btShowCalendar";
        btShowCalendar = new CalendarButton(btShowCalendarName, "...", "", controller);
        btShowCalendarFullName = btShowCalendar.getFullName();
        clearOnClick = _name + "DateClear();";
        btClearDateName = _name + "btClearDate";
        btClearDate = new CalendarButton(btClearDateName, " X ", clearOnClick, controller);
        int ind=0;
        this.insertComponentAt(ed, ind++);

        //this.insertComponentAt(edHidden, ind++);
        this.insertComponentAt(btShowCalendar, ind++);
        this.insertComponentAt(btClearDate, ind++);

        jscript = new HtmlScript("", "javascript", controller);
        StringBuffer jCode = new StringBuffer();

        // clear script
        jCode.append("function " + _name + "DateClear(){\n");
        //jCode.append(edHidden.getJavaScriptName() + ".value=\"\";\n");
        jCode.append(ed.getJavaScriptName() + ROW_NUM_MACRO + ".value=\"\";\n");
        jCode.append("};\n");

        // date changed script
        jCode.append("function " + _name + "DateChanged(calendar){\n");
        jCode.append(ed.getJavaScriptName() + ROW_NUM_MACRO + ".value=calendar.date.print(\"" + displayformat + "\");\n");
        //jCode.append(edHidden.getJavaScriptName() + ".value=calendar.date.print(\"%Y-%m-%d\");\n");
        jCode.append("};\n");

        // calendar setup script
        jCode.append("Calendar.setup({\n");
        jCode.append("inputField : " + ed.getJavaScriptName() + ROW_NUM_MACRO + ",\n");
        jCode.append("ifFormat : \"" + displayformat + "\",\n");
        jCode.append("button : \""  + BTN_NAME_MACRO + "\",\n");
        jCode.append("range : " + "[1970, 2080]" + ",\n");
        jCode.append("onUpdate : " + _name + "DateChanged" + "\n");
        jCode.append("});\n");

        code = jCode.toString();
        jscript.setScript(code);
        //MessageLog.writeDebugMessage("javascript = [" + jCode.toString() + "]", this);

        this.insertComponentAt(jscript, ind++);
    }


    public void pageRequested(PageEvent p) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void pageRequestEnd(PageEvent p) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void pageSubmitEnd(PageEvent p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void pageSubmitted(PageEvent p) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean submitPerformed(SubmitEvent e) throws Exception {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void generateHTML(PrintWriter p, int rowNo) throws Exception {

        applyColumn();

        // replace clear button on click function
        btClearDate.setOnClick(replaceMacro(clearOnClick, rowNo));
        //replace buttons names
        MessageLog.writeDebugMessage("replacing btClearDate [" + btClearDateName + "]", this);
        String newbtClearDateName = replaceMacro(btClearDateName, rowNo);
        MessageLog.writeDebugMessage("newbtClearDateName [" + newbtClearDateName + "]", this);
        btClearDate.setName(newbtClearDateName);

        MessageLog.writeDebugMessage("replacing btShowCalendarName [" + btShowCalendarName + "]", this);
        String newbtShowCalendarName = replaceMacro(btShowCalendarName, rowNo);
        btShowCalendar.setName(newbtShowCalendarName);
        MessageLog.writeDebugMessage("newbtShowCalendarName [" + newbtShowCalendarName + "]", this);

        // replace ROW_NUM_MACRO in jscript with rowNo
        jscript.setScript(replaceMacro(code, rowNo));

        super.generateHTML(p, rowNo);
    }

    private String replaceMacro(String str, int rowNo){
        StringBuffer script = new StringBuffer(str);
        int ind = -1;
        while((ind = script.indexOf(ROW_NUM_MACRO))!=-1){
            script.replace(ind, ind + ROW_NUM_MACRO.length(), (rowNo > -1) ? "_" + rowNo : "");
        }

        ind = -1;
        while((ind = script.indexOf(BTN_NAME_MACRO))!=-1){
            script.replace(ind, ind + BTN_NAME_MACRO.length(), btShowCalendar.getFullName());
        }
        return script.toString();
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
        ed.setReadOnly(Boolean.valueOf(readonly).booleanValue());
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
        ed.setSize(Integer.valueOf(size).intValue());
    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
        ed.setMaxLength(Integer.valueOf(maxlength).intValue());
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
/*
    //TODO: changing display format?
    public String getDisplayformat() {
        return displayformat;
    }

    public void setDisplayformat(String displayformat) {
        this.displayformat = displayformat;
    }
*/

    String name;
    String _name;
    JspController controller;
    HtmlTextEdit ed;
    //HtmlHiddenField edHidden;
    HtmlButton btShowCalendar;
    HtmlButton btClearDate;
    HtmlScript jscript;

    String code;
    String clearOnClick;
    String btShowCalendarName;
    String btShowCalendarFullName;
    String btClearDateName;

    private DataStoreBuffer _ds = null;
    private String column = null;

    private String readonly = "true";
    private String size = "6";
    private String maxlength = "6";
    private String datasource = "";
    private String displayformat = "%Y-%m-%d";

    class CalendarButton extends HtmlButton{
        public CalendarButton(String name, String dispName, String onClick, HtmlPage p) {
            super(name, dispName, onClick, p);    //To change body of overridden methods use File | Settings | File Templates.
        }

        /**
         * This will return the full name of the component. The full name is the name of the component appended to the names of its parent components and seperated by underscores. For example if this is a button named "button1" inside a table named "table1" the full name for this component would be "table1_button1".
         */
        public String getFullName() {
            HtmlComponent parent = getParent();
            String name = getName();
            // no need to create a new string for every time through the loop
            String parentName = null;
            //
            while (parent != null) {
                parentName = parent.getName();
                if (parentName != null) {
                    if (parentName != "") {
                        name = parentName + "_" + name;
                    }
                }
                parent = parent.getParent();
            }
            return getPortletNameSpace() + name;
        }


    }

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");


}
