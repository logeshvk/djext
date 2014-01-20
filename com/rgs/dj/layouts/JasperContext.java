/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Jasper Context will hold additional information to print jasper report
 */
public class JasperContext {
    //Title

    /**
     * Holds title field info
     */
    private List<JasperField> titleFieldList;
    private int titleFieldCount;
    //PageHeader
    /**
     * Holds page header field info
     */
    private List<JasperField> pageHeaderFieldList;
    private int pageHeaderFieldCount;
    //ColumnFooter
    /**
     * Holds column field info
     */
    private List<JasperField> columnFooterFieldList;
    private int columnFooterFieldCount;
    private int footerHeight;

    public JasperContext() {
        columnFooterFieldList = new ArrayList<JasperField>();
        pageHeaderFieldList = new ArrayList<JasperField>();
        titleFieldList = new ArrayList<JasperField>();
        footerHeight = 15;
    }

    public JasperContext(List<JasperField> titleFieldList, int titleFieldCount) {
        this.titleFieldList = titleFieldList;
        this.titleFieldCount = titleFieldCount;
        footerHeight = 15;
    }

    public JasperContext(List<JasperField> titleFieldList, int titleFieldCount,
            List<JasperField> pageHeaderFieldList, int pageHeaderFieldCount) {
        this.titleFieldList = titleFieldList;
        this.titleFieldCount = titleFieldCount;
        this.pageHeaderFieldList = pageHeaderFieldList;
        this.pageHeaderFieldCount = pageHeaderFieldCount;
        footerHeight = 15;
    }

    public JasperContext(List<JasperField> titleFieldList, int titleFieldCount,
            List<JasperField> pageHeaderFieldList, int pageHeaderFieldCount,
            List<JasperField> columnFooterFieldList, int columnFooterFieldCount) {
        this.titleFieldList = titleFieldList;
        this.titleFieldCount = titleFieldCount;
        this.pageHeaderFieldList = pageHeaderFieldList;
        this.pageHeaderFieldCount = pageHeaderFieldCount;
        this.columnFooterFieldList = columnFooterFieldList;
        this.columnFooterFieldCount = columnFooterFieldCount;
        footerHeight = 15;
    }

    public JasperContext(List<JasperField> titleFieldList, int titleFieldCount, List<JasperField> pageHeaderFieldList,
            int pageHeaderFieldCount, List<JasperField> columnFooterFieldList, int columnFooterFieldCount, int footerHeight) {
        this.titleFieldList = titleFieldList;
        this.titleFieldCount = titleFieldCount;
        this.pageHeaderFieldList = pageHeaderFieldList;
        this.pageHeaderFieldCount = pageHeaderFieldCount;
        this.columnFooterFieldList = columnFooterFieldList;
        this.columnFooterFieldCount = columnFooterFieldCount;
        this.footerHeight = footerHeight;
    }

    public int getFooterHeight() {
        return footerHeight;
    }

    public void setFooterHeight(int footerHeight) {
        this.footerHeight = footerHeight;
    }

    public List<JasperField> getTitleFieldList() {
        return titleFieldList;
    }

    public void setTitleFieldList(List<JasperField> titleFieldList) {
        this.titleFieldList = titleFieldList;
    }

    public int getTitleFieldCount() {
        return titleFieldCount;
    }

    public void setTitleFieldCount(int titleFieldCount) {
        this.titleFieldCount = titleFieldCount;
    }

    public List<JasperField> getPageHeaderFieldList() {
        return pageHeaderFieldList;
    }

    public void setPageHeaderFieldList(List<JasperField> pageHeaderFieldList) {
        this.pageHeaderFieldList = pageHeaderFieldList;
    }

    public int getPageHeaderFieldCount() {
        return pageHeaderFieldCount;
    }

    public void setPageHeaderFieldCount(int pageHeaderFieldCount) {
        this.pageHeaderFieldCount = pageHeaderFieldCount;
    }

    public List<JasperField> getColumnFooterFieldList() {
        return columnFooterFieldList;
    }

    public void setColumnFooterFieldList(List<JasperField> columnFooterFieldList) {
        this.columnFooterFieldList = columnFooterFieldList;
    }

    public int getColumnFooterFieldCount() {
        return columnFooterFieldCount;
    }

    public void setColumnFooterFieldCount(int columnFooterFieldCount) {
        this.columnFooterFieldCount = columnFooterFieldCount;
    }
}
