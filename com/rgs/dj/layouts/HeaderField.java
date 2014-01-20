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
public class HeaderField {
    
    public String value;
    public int width;
    public int height;
    public Style style;

    public HeaderField(String value) {
        this.value = value;
        height=20;
        width=100;
    }
    
     public HeaderField(String value, int width) {
        this.value = value;
        this.width = width;
        height=20;
    }

    public HeaderField(String value, int width, int height) {
        this.value = value;
        this.width = width;
        this.height = height;
    }
    
    
}
