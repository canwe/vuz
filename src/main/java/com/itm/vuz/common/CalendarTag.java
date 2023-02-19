package com.itm.vuz.common;

import com.salmonllc.html.HtmlComponent;
import com.salmonllc.jsp.JspController;
import com.salmonllc.jsp.tags.BaseEmptyTag;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 13.07.2006
 * Time: 21:19:05
 */
public class CalendarTag extends BaseEmptyTag{


    public HtmlComponent createComponent() {
        JspController controller = getHelper().getController();
        CalendarComponent comp =  new CalendarComponent(getName(), controller);
        if(getReadonly()!=null){
            comp.setReadonly(getReadonly());
        }
        if(getSize()!=null){
            comp.setSize(getSize());
        }
        if(getMaxlength()!=null){
            comp.setMaxlength(getMaxlength());
        }
        if(getDatasource()!=null){
            comp.setDatasource(getDatasource());
        }
        return comp;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    private String readonly;
    private String size;
    private String maxlength;
    private String datasource;
    private String displayformat;
}
