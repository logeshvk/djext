/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import ar.com.fdvs.dj.domain.Style;

/**
 *
 * @author Client1
 */
public class JasperField {

    private String value;
    private int width;
    private int height;
    private Style style;

    public JasperField(String value, int width, Style style) {
        this.value = value;
        this.width = width;
        this.height = 7;
        this.style = style;
    }

    public JasperField(String value, int width, int height, Style style) {
        this.value = value;
        this.width = width;
        this.height = height;
        this.style = style;
    }

    public JasperField(String value, int width) {
        this.value = value;
        this.width = width;
        this.height = 7;
        this.style = CustomDJStyleHelper.defaultStyle;
    }

    public JasperField(String value, int width, int height) {
        this.value = value;
        this.width = width;
        this.height = height;
        this.style = CustomDJStyleHelper.defaultStyle;
    }

    public JasperField(String value) {
        this.value = value;
        this.width = 100;
        this.height = 7;
        this.style = CustomDJStyleHelper.defaultStyle;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
