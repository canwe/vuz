package com.itm.vuz.groupcard;

import com.salmonllc.html.HtmlComponent;
import com.salmonllc.jsp.tags.BaseEmptyTag;
import com.salmonllc.util.MessageLog;

public class GroupCardTag extends BaseEmptyTag {


	public HtmlComponent createComponent() {
		return new GroupCardComponent(getName(), getSemester(), getCourse(), 
                getYear(), getGroup(), getSearch(), getEditnotes(), getHelper().getController());
	}

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getEditnotes() {
        return editnotes;
    }

    public void setEditnotes(String editnotes) {
        this.editnotes = editnotes;
    }

    private String semester;
    private String course;
    private String year;
    private String group;

    private String search;

    private String editnotes;

}
