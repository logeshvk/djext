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
public class TitleField {
       
    public String value;
    public int width;
    public int height;
    public Style style;

    public TitleField(String value) {
        this.value = value;
        width = 100;
        height=20;
    }

    public TitleField(String value, int width) {
        this.value = value;
        this.width = width;
        height=20;
    }

    public TitleField(String value, int width, int height) {
        this.value = value;
        this.width = width;
        this.height = height;
    }

    public TitleField(String value, int width, Style style) {
        this.value = value;
        this.width = width;
        this.style = style;
    }
    
    
}
