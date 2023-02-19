package com.itm.vuz.common.utils;

/**
 * holds page name and page url for history of links
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 15.08.2006
 * Time: 21:45:46
 */
public class LinkHistory {
    public LinkHistory(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public LinkHistory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    private String name;
    private String url;

}
