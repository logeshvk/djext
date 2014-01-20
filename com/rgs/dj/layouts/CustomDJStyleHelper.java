/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import java.awt.Color;

/**
 *
 * @author Client1
 */
public class CustomDJStyleHelper {

    public static Style header;
    public static Style headerBorder;
    public static Style title;
    public static Style titleBorder;
    public static Style columnHeader;
    public static Style amount;
    public static Style amountColumn;
    public static Style columnDetail;
    public static Style borderAtLeftStyle;
    public static Style borderAtLeftandBottomStyle;
    public static Style defaultStyle;
    public static Style borderAtLeftAndTopStyle;
    public static Style borderAtLeftBottomTopStyle;
    public static Style borderAtRightStyle;
    public static Style borderAtBottomStyle;
    public static Style borderAtTopStyle;
    public static Style borderAtBottomRightStyle;
    public static Style borderAtTopRightStyle;
    public static Style borderAtLeftRightStyle;
    public static Style borderAtLeftTopRightStyle;
    public static Style borderAtLeftBottomRightStyle;
    public static Style borderAtLeftBottomTopRightStyle;

    static {
        header = new Style();
        headerBorder = new Style();
        title = new Style();
        titleBorder = new Style();
        amount = new Style();
        amountColumn = new Style();
        columnDetail = new Style();

        defaultStyle = new Style();
        borderAtLeftStyle = new Style();
        borderAtBottomStyle = new Style();
        borderAtLeftandBottomStyle = new Style();
        borderAtLeftAndTopStyle = new Style();
        borderAtLeftBottomTopStyle = new Style();
        borderAtRightStyle = new Style();
        borderAtTopStyle = new Style();
        columnHeader = new Style();
        borderAtBottomRightStyle = new Style();
        borderAtTopRightStyle = new Style();
        borderAtLeftRightStyle = new Style();
        borderAtLeftTopRightStyle = new Style();
        borderAtLeftBottomRightStyle = new Style();
        borderAtLeftBottomTopRightStyle  = new Style();

        columnHeader.setFont(Font.ARIAL_SMALL_BOLD);
        columnHeader.setHorizontalAlign(HorizontalAlign.CENTER);
        columnHeader.setPaddingLeft(2);
        columnHeader.setBorder(Border.THIN());
        columnHeader.setBackgroundColor(Color.LIGHT_GRAY);
        columnHeader.setVerticalAlign(VerticalAlign.MIDDLE);
        columnHeader.setTransparency(Transparency.OPAQUE);
                
        header.setFont(Font.ARIAL_BIG);
        header.setHorizontalAlign(HorizontalAlign.CENTER);
        header.setPaddingLeft(2);

        headerBorder.setFont(Font.ARIAL_BIG);
        headerBorder.setPaddingLeft(2);
        headerBorder.setHorizontalAlign(HorizontalAlign.CENTER);
        headerBorder.setBorder(Border.THIN());

        title.setFont(Font.ARIAL_MEDIUM);
        title.setPaddingLeft(2);
        
        title.setHorizontalAlign(HorizontalAlign.LEFT);
        titleBorder.setFont(Font.ARIAL_MEDIUM);
        titleBorder.setPaddingLeft(2);
        titleBorder.setHorizontalAlign(HorizontalAlign.LEFT);


        amount.setFont(Font.ARIAL_SMALL);
        amount.setHorizontalAlign(HorizontalAlign.RIGHT);
        titleBorder.setPaddingLeft(2);


        amountColumn.setFont(Font.ARIAL_SMALL);
        amountColumn.setHorizontalAlign(HorizontalAlign.RIGHT);
        amountColumn.setBorder(Border.THIN());
        titleBorder.setPaddingLeft(2);

        columnDetail.setFont(Font.ARIAL_SMALL);
        columnDetail.setHorizontalAlign(HorizontalAlign.LEFT);
        columnDetail.setBorder(Border.THIN());
        titleBorder.setPaddingLeft(2);

        defaultStyle.setFont(Font.ARIAL_SMALL);
        defaultStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        titleBorder.setPaddingLeft(2);

        title.setHorizontalAlign(HorizontalAlign.CENTER);
        title.setFont(Font.ARIAL_BIG);
        titleBorder.setPaddingLeft(2);

        borderAtLeftStyle.setBorderLeft(Border.THIN());
        borderAtLeftStyle.setFont(Font.ARIAL_SMALL);
        titleBorder.setPaddingLeft(2);


        borderAtBottomStyle.setBorderBottom(Border.THIN());
        borderAtBottomStyle.setFont(Font.ARIAL_SMALL);
        borderAtBottomStyle.setHorizontalAlign(HorizontalAlign.LEFT);

        borderAtLeftandBottomStyle.setBorderLeft(Border.THIN());
        borderAtLeftandBottomStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftandBottomStyle.setBorderBottom(Border.THIN());
        borderAtLeftandBottomStyle.setHorizontalAlign(HorizontalAlign.LEFT);

        borderAtLeftAndTopStyle.setBorderLeft(Border.THIN());
        borderAtLeftAndTopStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftAndTopStyle.setBorderTop(Border.THIN());
        borderAtLeftAndTopStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtLeftAndTopStyle.setPaddingLeft(2);
        borderAtLeftAndTopStyle.setPaddingTop(2);

        borderAtLeftBottomTopStyle.setBorderLeft(Border.THIN());
        borderAtLeftBottomTopStyle.setBorderTop(Border.THIN());
        borderAtLeftBottomTopStyle.setBorderBottom(Border.THIN());
        borderAtLeftBottomTopStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftBottomTopStyle.setHorizontalAlign(HorizontalAlign.LEFT) ;

        borderAtRightStyle.setBorderRight(Border.THIN());
        borderAtRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);

        borderAtTopStyle.setBorderTop(Border.THIN());
        borderAtTopStyle.setFont(Font.ARIAL_SMALL);
        borderAtTopStyle.setHorizontalAlign(HorizontalAlign.LEFT);
    
        borderAtBottomRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtBottomRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtBottomRightStyle.setBorderBottom(Border.THIN());
        borderAtBottomRightStyle.setBorderRight(Border.THIN());

        borderAtTopRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtTopRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtTopRightStyle.setBorderTop(Border.THIN());
        borderAtTopRightStyle.setBorderRight(Border.THIN());

        borderAtLeftRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtLeftRightStyle.setBorderLeft(Border.THIN());
        borderAtLeftRightStyle.setBorderRight(Border.THIN());

        borderAtLeftTopRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftTopRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtLeftTopRightStyle.setBorderLeft(Border.THIN());
        borderAtLeftTopRightStyle.setBorderTop(Border.THIN());
        borderAtLeftTopRightStyle.setBorderRight(Border.THIN());
       
        borderAtLeftBottomRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftBottomRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtLeftBottomRightStyle.setBorderLeft(Border.THIN());
        borderAtLeftBottomRightStyle.setBorderBottom(Border.THIN());
        borderAtLeftBottomRightStyle.setBorderRight(Border.THIN());
    
        borderAtLeftBottomTopRightStyle.setFont(Font.ARIAL_SMALL);
        borderAtLeftBottomTopRightStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        borderAtLeftBottomTopRightStyle.setBorderLeft(Border.THIN());
        borderAtLeftBottomTopRightStyle.setBorderBottom(Border.THIN());
        borderAtLeftBottomTopRightStyle.setBorderRight(Border.THIN());
        borderAtLeftBottomTopRightStyle.setBorderTop(Border.THIN());
        
}
}