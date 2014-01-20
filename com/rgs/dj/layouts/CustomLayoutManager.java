/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.DJColSpan;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.BarCodeColumn;
import ar.com.fdvs.dj.domain.entities.columns.ImageColumn;
import ar.com.fdvs.dj.domain.entities.columns.PercentageColumn;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import ar.com.fdvs.dj.util.ExpressionUtils;
import ar.com.fdvs.dj.util.HyperLinkUtil;
import ar.com.fdvs.dj.util.Utils;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.util.JRExpressionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Main Layout Manager recommended for most cases.</br>
 * </br>
 * It provides DJ full features (styles, groups, conditional styles, </br>
 * expressions, group and total variables, etc)</br>
 * It Provides provision to display additional information at title and column </br>
 * footer band
 */
public class CustomLayoutManager extends ClassicLayoutManager {

    private int type;
    /**
     * Holds values to be display at title band and column footer band
     */
    private JasperContext context;
    private static final Log log = LogFactory.getLog(ClassicLayoutManager.class);

    public CustomLayoutManager() {
        type = 1;
        this.context = new JasperContext();
    }

    public CustomLayoutManager(JasperContext context) {
        type = 2;
        this.context = context;
    }

    public List<JasperField> getTitleFieldList() {
        return context.getTitleFieldList();
    }

    public int getTitleFieldCount() {
        return context.getColumnFooterFieldCount();
    }

    public List<JasperField> getPageHeaderFieldList() {
        return context.getPageHeaderFieldList();
    }

    public int getPageHeaderFieldCount() {
        return context.getPageHeaderFieldCount();
    }

    public List<JasperField> getColumnFooterFieldList() {
        return context.getColumnFooterFieldList();
    }

    public int getColumnFooterFieldCount() {
        return context.getColumnFooterFieldCount();
    }

    protected void generateColumnFooter() {
        int maxPrintWidth = getReport().getOptions().getPrintableWidth();

        getDesign().setColumnFooter(new JRDesignBand());
        JRDesignBand band = (JRDesignBand) getDesign().getColumnFooter();
        log.info("Generating column footer band...");

        band.setHeight(context.getFooterHeight());

        JRDesignExpression printWhenExpression = new JRDesignExpression();
        printWhenExpression.setValueClass(Boolean.class);
        printWhenExpression.setText(EXPRESSION_TRUE_WHEN_FIRST_PAGE);

        JRDesignExpression valueExp = null;
        JRDesignTextField valeTextField = null;

        JRDesignExpression labelExp = null;
        JRDesignTextField labelTextField = null;


        int yoffset = 0;
        int xoffset = 0;

        int index = 0;
        if (getColumnFooterFieldList() != null) {
            for (JasperField columnFooterFld : getColumnFooterFieldList()) {

                index++;

                if (index > getColumnFooterFieldCount()) {
                    yoffset = yoffset + columnFooterFld.getHeight();
                    xoffset = 0;
                    index = 1;
                }
                valeTextField = new JRDesignTextField();
                valueExp = new JRDesignExpression();

                labelTextField = new JRDesignTextField();
                labelExp = new JRDesignExpression();

                log.info(columnFooterFld.getValue());


                labelExp.setText("\"" + columnFooterFld.getValue() + "\"");
                labelExp.setValueClass(String.class);
                labelTextField.setExpression(labelExp);
                labelTextField.setWidth(200);
                labelTextField.setHeight(columnFooterFld.getHeight());
                labelTextField.setY(yoffset);
                labelTextField.setX(xoffset);
                labelTextField.setPrintWhenExpression(printWhenExpression);
                labelTextField.setRemoveLineWhenBlank(false);
                if (xoffset >= maxPrintWidth) {
                    log.error("Max Width reached: " + xoffset);
                    labelTextField.setWidth(maxPrintWidth - (xoffset - columnFooterFld.getWidth()));
                    log.error("Width : " + (maxPrintWidth - (xoffset - columnFooterFld.getWidth())));
                    xoffset = maxPrintWidth;
                } else {
                    log.error("Width : " + columnFooterFld.getWidth());
                    labelTextField.setWidth(columnFooterFld.getWidth());
                }

                if (columnFooterFld.getStyle() != null) {
                    applyStyleToElement(columnFooterFld.getStyle(), labelTextField);
                } else {
                    applyStyleToElement(CustomDJStyleHelper.columnDetail, labelTextField);
                }
                band.addElement(labelTextField);

                xoffset = xoffset + columnFooterFld.getWidth();

            }
        }
    }

    /**
     * Adds title and subtitle to the TitleBand when it applies. If title is not
     * present then subtitle will be ignored
     */
    protected void generateTitleBand() {
        log.info(type + " Generating title band...");
        log.info("Printable Width : " + getReport().getOptions().getPrintableWidth());
        JRDesignBand band = (JRDesignBand) getDesign().getPageHeader();
        int yOffset = 0;

        if (getReport().getTitle() == null) {
            if (band != null) {
                log.info("Current height for Title is " + band.getHeight());
                band.setHeight(0);
                log.info("Setting height for Title as 0");
            }
            return;
        }


        if (band != null && !getDesign().isTitleNewPage()) {
            //Title and subtitle comes afer the page header
            yOffset = band.getHeight();

        } else {
            band = (JRDesignBand) getDesign().getTitle();
            if (band == null) {
                band = new JRDesignBand();
                getDesign().setTitle(band);
            }
        }

        JRDesignExpression printWhenExpression = new JRDesignExpression();
        printWhenExpression.setValueClass(Boolean.class);
        printWhenExpression.setText(EXPRESSION_TRUE_WHEN_FIRST_PAGE);


        JRDesignExpression valueExp = null;
        JRDesignTextField valeTextField = null;

        JRDesignExpression labelExp = null;
        JRDesignTextField labelTextField = null;


        int maxPrintWidth = getReport().getOptions().getPrintableWidth();
        int yoffset = 0;
        int xoffset = 0;
        log.info("outside header");

        int index = 0;
        for (JasperField titleField : getTitleFieldList()) {

            index++;

            if (index > getTitleFieldCount()) {
                yoffset = yoffset + titleField.getHeight();
                xoffset = 0;
                index = 1;
            }
            valeTextField = new JRDesignTextField();
            valueExp = new JRDesignExpression();

            labelTextField = new JRDesignTextField();
            labelExp = new JRDesignExpression();

            log.info(titleField.getValue());


            labelExp.setText("\"" + titleField.getValue() + "\"");
            labelExp.setValueClass(String.class);
            labelTextField.setExpression(labelExp);

            labelTextField.setHeight(getReport().getOptions().getSubtitleHeight().intValue());
            labelTextField.setY(yoffset);
            labelTextField.setX(xoffset);
            labelTextField.setPrintWhenExpression(printWhenExpression);
            labelTextField.setRemoveLineWhenBlank(false);
            xoffset = xoffset + titleField.getWidth();
            if (xoffset >= maxPrintWidth) {
                labelTextField.setWidth(maxPrintWidth - (xoffset - titleField.getWidth()));
                xoffset = maxPrintWidth;
            } else {
                labelTextField.setWidth(titleField.getWidth());
            }
            log.error(labelExp.getText() + " : Width : " + titleField.getWidth());

            if (titleField.getStyle() != null) {
                applyStyleToElement(titleField.getStyle(), labelTextField);
            } else {
                applyStyleToElement(CustomDJStyleHelper.title, labelTextField);
            }
            band.addElement(labelTextField);



//            valueExp.setText("\"" + titleField.value + "\"");
//            valueExp.setValueClass(String.class);
//            valeTextField.setExpression(valueExp);
//            valeTextField.setWidth(200);
//            valeTextField.setHeight(getReport().getOptions().getSubtitleHeight().intValue());
//            valeTextField.setY(yoffset);
//            valeTextField.setX(200);
//            valeTextField.setPrintWhenExpression(printWhenExpression);
//            valeTextField.setRemoveLineWhenBlank(true);
////            applyStyleToElement(getReport().getSubtitleStyle(), subtitle);
//            band.addElement(valeTextField);
        }

    }

    protected void generateHeaderBand() {
        log.debug(type + "Generating main report header band.");
        JRDesignBand header = (JRDesignBand) getDesign().getColumnHeader();
        if (header == null) {
            header = new JRDesignBand();
            getDesign().setColumnHeader(header);
        }


        /**
         * Note: Te column names, when in header, are printed at the begining of
         * every page. You may dont want this option if you have groups that
         * prints column names.
         */
        if (getReport().getOptions().isPrintColumnNames()) {
            generateHeaderBand(header);
        }



//		if (!DynamicJasperHelper.existsGroupWithColumnNames(getReport().getColumnsGroups()))
//			generateHeaderBand(header);
    }

    protected void generateHeaderBand(JRDesignBand band) {
        log.debug(type + "Adding column names in header band.");
        band.setHeight(getReport().getOptions().getHeaderHeight());

        for (AbstractColumn col : (List<AbstractColumn>) getVisibleColumns()) {

            if (col.getTitle() == null) {
                continue;
            }

            Style headerStyle = col.getHeaderStyle();
            if (headerStyle == null) {
                headerStyle = getReport().getOptions().getDefaultHeaderStyle();
            }

            generateColspanHeader(col, band);

            JRDesignExpression expression = new JRDesignExpression();
            JRDesignTextField textField = new JRDesignTextField();
            expression.setText("\"" + col.getTitle() + "\"");

            //sets header markup (if any)
            if (col.getHeaderMarkup() != null) {
                textField.setMarkup(col.getHeaderMarkup().toLowerCase());
            }

            expression.setValueClass(String.class);

            textField.setKey("header_" + col.getTitle());
            textField.setExpression(expression);

            if (col.hasParentCol()) {
                textField.setY(col.getPosY() + band.getHeight() / 2);
                textField.setHeight(band.getHeight() / 2);

            } else {
                textField.setY(col.getPosY());
                textField.setHeight(band.getHeight());
            }

            textField.setX(col.getPosX().intValue());
            textField.setWidth(col.getWidth().intValue());

            textField.setPrintWhenDetailOverflows(true);
            textField.setBlankWhenNull(true);

            applyStyleToElement(headerStyle, textField);
            band.addElement(textField);
        }
    }

    private void generateColspanHeader(AbstractColumn col, JRDesignBand band) {

        DJColSpan colSpan = col.getColSpan();
        if (colSpan != null && colSpan.isFirstColum(col)) {
            //Set colspan
            JRDesignTextField spanTitle = new JRDesignTextField();
            JRDesignExpression colspanExpression = new JRDesignExpression();
            colspanExpression.setValueClassName(String.class.getName());
            colspanExpression.setText("\"" + col.getColSpan().getTitle() + "\"");

            spanTitle.setExpression(colspanExpression);
            spanTitle.setKey("colspan-header" + col.getTitle());

            spanTitle.setX(col.getPosX().intValue());
            spanTitle.setY(col.getPosY());
            spanTitle.setHeight(band.getHeight() / 2);
            spanTitle.setWidth(colSpan.getWidth());

            Style spanStyle = colSpan.getColspanHeaderStyle();

            if (spanStyle == null) {
                spanStyle = getReport().getOptions().getDefaultHeaderStyle();
            }

            applyStyleToElement(spanStyle, spanTitle);
            band.addElement(spanTitle);
        }
    }

    @Override
    protected void transformDetailBand() {

        log.debug("transforming Detail Band...");

        JRDesignSection detailSection = (JRDesignSection) getDesign().getDetailSection();

        //TODO: With this new way, we can use template content as it comes, and add a new band for DJ on top or bellow it.
        JRDesignBand detail = null;
        if (detailSection.getBandsList().isEmpty()) {
            detail = new JRDesignBand();
            detailSection.getBandsList().add(detail);
        } else {
            detail = (JRDesignBand) detailSection.getBandsList().iterator().next();
        }

        detail.setHeight(getReport().getOptions().getDetailHeight().intValue());

        int heightOffset = 0;

        for (Iterator iter = getVisibleColumns().iterator(); iter.hasNext();) {

            AbstractColumn column = (AbstractColumn) iter.next();

            /**
             * Barcode column
             */
            if (column instanceof BarCodeColumn) {
                BarCodeColumn barcodeColumn = (BarCodeColumn) column;
                JRDesignImage image = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());
                JRDesignExpression imageExp = new JRDesignExpression();
//				imageExp.setText("ar.com.fdvs.dj.core.BarcodeHelper.getBarcodeImage("+barcodeColumn.getBarcodeType() + ", "+ column.getTextForExpression()+ ", "+ barcodeColumn.isShowText() + ", " + barcodeColumn.isCheckSum() + ", " + barcodeColumn.getApplicationIdentifier() + ","+ column.getWidth() +", "+ report.getOptions().getDetailHeight().intValue() + " )" );

                //Do not pass column height and width mecause barbecue
                //generates the image with wierd dimensions. Pass 0 in both cases
                String applicationIdentifier = barcodeColumn.getApplicationIdentifier();
                if (applicationIdentifier != null && !"".equals(applicationIdentifier.trim())) {
                    applicationIdentifier = "$F{" + applicationIdentifier + "}";
                } else {
                    applicationIdentifier = "\"\"";
                }
                imageExp.setText("ar.com.fdvs.dj.core.BarcodeHelper.getBarcodeImage(" + barcodeColumn.getBarcodeType() + ", " + column.getTextForExpression() + ", " + barcodeColumn.isShowText() + ", " + barcodeColumn.isCheckSum() + ", " + applicationIdentifier + ",0,0 )");


                imageExp.setValueClass(java.awt.Image.class);
                image.setExpression(imageExp);
                image.setHeight(getReport().getOptions().getDetailHeight().intValue());
                image.setWidth(column.getWidth().intValue());
                image.setX(column.getPosX().intValue());
                image.setScaleImage(ScaleImageEnum.getByValue(barcodeColumn.getScaleMode().getValue()));

                image.setOnErrorType(OnErrorTypeEnum.ICON); //FIXME should we provide control of this to the user?

                if (column.getLink() != null) {
                    String name = "column_" + getReport().getColumns().indexOf(column);
                    HyperLinkUtil.applyHyperLinkToElement((DynamicJasperDesign) getDesign(), column.getLink(), image, name);
                }

                applyStyleToElement(column.getStyle(), image);

                detail.addElement(image);

            } /**
             * Image columns
             */
            else if (column instanceof ImageColumn) {
                ImageColumn imageColumn = (ImageColumn) column;
                JRDesignImage image = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());
                JRDesignExpression imageExp = new JRDesignExpression();
                imageExp.setText(column.getTextForExpression());

                imageExp.setValueClassName(imageColumn.getValueClassNameForExpression());
                image.setExpression(imageExp);
                image.setHeight(getReport().getOptions().getDetailHeight().intValue());
                image.setWidth(column.getWidth().intValue());
                image.setX(column.getPosX().intValue());
                image.setScaleImage(ScaleImageEnum.getByValue(imageColumn.getScaleMode().getValue()));

                applyStyleToElement(column.getStyle(), image);

                if (column.getLink() != null) {
                    String name = "column_" + getReport().getColumns().indexOf(column);
                    HyperLinkUtil.applyHyperLinkToElement((DynamicJasperDesign) getDesign(), column.getLink(), image, name);
                }

                detail.addElement(image);
            } /**
             * Regular Column
             */
            else {
                if (getReport().getOptions().isShowDetailBand()) {
                    JRDesignTextField textField = generateTextFieldFromColumn(column, getReport().getOptions().getDetailHeight().intValue(), null);

                    heightOffset = heightOffset + textField.getHeight();
                    log.info("height Offset : " + heightOffset);

                    if (column.getLink() != null) {
                        String name = getDesign().getName() + "_column_" + getReport().getColumns().indexOf(column);
                        HyperLinkUtil.applyHyperLinkToElement((DynamicJasperDesign) getDesign(), column.getLink(), textField, name);
                    }


                    transformDetailBandTextField(column, textField);

                    if (textField.getExpression() != null) {
                        detail.addElement(textField);
                    }
                }

            }

        }
        log.info("height Offset : " + heightOffset + " detail hight " + detail.getHeight());
        generateColumnFooter();
    }

    protected JRDesignTextField generateTextFieldFromColumn(AbstractColumn col, int height, DJGroup group) {
        JRDesignTextField textField = new JRDesignTextField();
        JRDesignExpression exp = new JRDesignExpression();

        if (col.getPattern() != null && "".equals(col.getPattern().trim())) {
            textField.setPattern(col.getPattern());
        }

        if (col.getTruncateSuffix() != null) {
            textField.getPropertiesMap().setProperty(JRTextElement.PROPERTY_TRUNCATE_SUFFIX, col.getTruncateSuffix());
        }

        List columnsGroups = getReport().getColumnsGroups();
        if (col instanceof PercentageColumn) {
            PercentageColumn pcol = (PercentageColumn) col;

            if (group == null) { //we are in the detail band
                DJGroup innerMostGroup = (DJGroup) columnsGroups.get(columnsGroups.size() - 1);
                exp.setText(pcol.getTextForExpression(innerMostGroup));
            } else {
                exp.setText(pcol.getTextForExpression(group));
            }

            textField.setEvaluationTime(EvaluationTimeEnum.AUTO);
        } else {
            exp.setText(col.getTextForExpression());

        }

        exp.setValueClassName(col.getValueClassNameForExpression());
        textField.setExpression(exp);
        textField.setWidth(col.getWidth().intValue());
        log.info(col.getName() + ": " + exp.getText() + "Width : " + col.getWidth());
        textField.setX(col.getPosX().intValue());
        textField.setY(col.getPosY().intValue());
        textField.setHeight(height);

        textField.setBlankWhenNull(col.getBlankWhenNull());

        textField.setPattern(col.getPattern());

        if (col.getMarkup() != null) {
            textField.setMarkup(col.getMarkup().toLowerCase());
        }

        textField.setPrintRepeatedValues(col.getPrintRepeatedValues().booleanValue());

        textField.setPrintWhenDetailOverflows(true);

        Style columnStyle = col.getStyle();
        if (columnStyle == null) {
            columnStyle = getReport().getOptions().getDefaultDetailStyle();
        }

        applyStyleToElement(columnStyle, textField);
        JRDesignStyle jrstyle = (JRDesignStyle) textField.getStyle();

        if (group != null) {
            int index = columnsGroups.indexOf(group);
//            JRDesignGroup previousGroup = (JRDesignGroup) getDesign().getGroupsList().get(index);
            JRDesignGroup previousGroup = getJRGroupFromDJGroup(group);
            textField.setPrintWhenGroupChanges(previousGroup);

            /**
             * Since a group column can share the style with non group columns,
             * if oddRow coloring is enabled, we modified this shared style to
             * have a colored background on odd rows. We don't want that for
             * group columns, that's why we create our own style from the
             * existing one, and remove proper odd-row conditional style if
             * present
             */
            JRDesignStyle groupStyle = Utils.cloneStyle(jrstyle);

            groupStyle.setName(groupStyle.getFontName() + "_for_group_" + index + "_");
            textField.setStyle(groupStyle);
            try {
                getDesign().addStyle(groupStyle);
            } catch (JRException e) {
                /**
                 * e.printStackTrace(); //Already there, nothing to do *
                 */
            }

        } else {

            JRDesignStyle alternateStyle = Utils.cloneStyle(jrstyle);

            alternateStyle.setName(alternateStyle.getFontName() + "_for_column_" + col.getName() + "_");
            alternateStyle.getConditionalStyleList().clear();
            textField.setStyle(alternateStyle);
            try {
                getDesign().addStyle(alternateStyle);
            } catch (JRException e) {
                /**
                 * e.printStackTrace(); //Already there, nothing to do *
                 */
            }


            setUpConditionStyles(alternateStyle, col);
            /*
             if (getReport().getOptions().isPrintBackgroundOnOddRows() &&
             (jrstyle.getConditionalStyles() == null || jrstyle.getConditionalStyles().length == 0)) {
             // No group column so this is a detail text field
             JRDesignExpression expression = new JRDesignExpression();
             expression.setValueClass(Boolean.class);
             expression.setText(EXPRESSION_TRUE_WHEN_ODD);

             Style oddRowBackgroundStyle = getReport().getOptions().getOddRowBackgroundStyle();

             JRDesignConditionalStyle condStyle = new JRDesignConditionalStyle();
             condStyle.setBackcolor(oddRowBackgroundStyle.getBackgroundColor());
             condStyle.setMode(JRDesignElement.MODE_OPAQUE);

             condStyle.setConditionExpression(expression);
             jrstyle.addConditionalStyle(condStyle);
             }*/
        }
        return textField;
    }

    private void setUpConditionStyles(JRDesignStyle jrstyle, AbstractColumn column) {

        if (getReport().getOptions().isPrintBackgroundOnOddRows() && Utils.isEmpty(column.getConditionalStyles())) {
            JRDesignExpression expression = new JRDesignExpression();
            expression.setValueClass(Boolean.class);
            expression.setText(EXPRESSION_TRUE_WHEN_ODD);

            Style oddRowBackgroundStyle = getReport().getOptions().getOddRowBackgroundStyle();

            JRDesignConditionalStyle condStyle = new JRDesignConditionalStyle();
            condStyle.setBackcolor(oddRowBackgroundStyle.getBackgroundColor());
            condStyle.setMode(ModeEnum.OPAQUE);

            condStyle.setConditionExpression(expression);
            jrstyle.addConditionalStyle(condStyle);

            return;
        }

        if (Utils.isEmpty(column.getConditionalStyles())) {
            return;
        }

        for (Iterator iterator = column.getConditionalStyles().iterator(); iterator.hasNext();) {
            ConditionalStyle condition = (ConditionalStyle) iterator.next();

            if (getReport().getOptions().isPrintBackgroundOnOddRows()
                    && Transparency.TRANSPARENT == condition.getStyle().getTransparency()) { //condition style + odd row (only if conditional style's background is transparent)

                JRDesignExpression expressionForConditionalStyle = ExpressionUtils.getExpressionForConditionalStyle(condition, column.getTextForExpression());
                String expStr = JRExpressionUtil.getExpressionText(expressionForConditionalStyle);

                //ODD
                JRDesignExpression expressionOdd = new JRDesignExpression();
                expressionOdd.setValueClass(Boolean.class);
                expressionOdd.setText("new java.lang.Boolean(" + EXPRESSION_TRUE_WHEN_ODD + ".booleanValue() && ((java.lang.Boolean)" + expStr + ").booleanValue() )");

                Style oddRowBackgroundStyle = getReport().getOptions().getOddRowBackgroundStyle();

                JRDesignConditionalStyle condStyleOdd = makeConditionalStyle(condition.getStyle());
//				Utils.copyProperties(condStyleOdd, condition.getStyle().transform());
                condStyleOdd.setBackcolor(oddRowBackgroundStyle.getBackgroundColor());
                condStyleOdd.setMode(ModeEnum.OPAQUE);
                condStyleOdd.setConditionExpression(expressionOdd);
                jrstyle.addConditionalStyle(condStyleOdd);

                //EVEN
                JRDesignExpression expressionEven = new JRDesignExpression();
                expressionEven.setValueClass(Boolean.class);
                expressionEven.setText("new java.lang.Boolean(" + EXPRESSION_TRUE_WHEN_EVEN + ".booleanValue() && ((java.lang.Boolean)" + expStr + ").booleanValue() )");

                JRDesignConditionalStyle condStyleEven = makeConditionalStyle(condition.getStyle());
                condStyleEven.setConditionExpression(expressionEven);
                jrstyle.addConditionalStyle(condStyleEven);

            } else { //No odd row, just the conditional style
                JRDesignExpression expression = ExpressionUtils.getExpressionForConditionalStyle(condition, column.getTextForExpression());
                JRDesignConditionalStyle condStyle = makeConditionalStyle(condition.getStyle());
                condStyle.setConditionExpression(expression);
                jrstyle.addConditionalStyle(condStyle);
            }
        }

        //The last condition is the basic one
        //ODD
        if (getReport().getOptions().isPrintBackgroundOnOddRows()) {

            JRDesignExpression expressionOdd = new JRDesignExpression();
            expressionOdd.setValueClass(Boolean.class);
            expressionOdd.setText(EXPRESSION_TRUE_WHEN_ODD);

            Style oddRowBackgroundStyle = getReport().getOptions().getOddRowBackgroundStyle();

            JRDesignConditionalStyle condStyleOdd = new JRDesignConditionalStyle();
            condStyleOdd.setBackcolor(oddRowBackgroundStyle.getBackgroundColor());
            condStyleOdd.setMode(ModeEnum.OPAQUE);
            condStyleOdd.setConditionExpression(expressionOdd);

            jrstyle.addConditionalStyle(condStyleOdd);

            //EVEN
            JRDesignExpression expressionEven = new JRDesignExpression();
            expressionEven.setValueClass(Boolean.class);
            expressionEven.setText(EXPRESSION_TRUE_WHEN_EVEN);

            JRDesignConditionalStyle condStyleEven = new JRDesignConditionalStyle();
            condStyleEven.setBackcolor(jrstyle.getBackcolor());
            condStyleEven.setMode(jrstyle.getModeValue());
            condStyleEven.setConditionExpression(expressionEven);

            jrstyle.addConditionalStyle(condStyleEven);
        }
    }

    private void printParameters() {
        for (Object obj : getReport().getParameters()) {
            log.info("Class : " + obj.getClass());
            log.info(" >> Value : " + obj);
        }
    }
}
