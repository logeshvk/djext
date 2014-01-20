/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import ar.com.fdvs.dj.core.DJJRDesignHelper;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.DynamicReportOptions;
import ar.com.fdvs.dj.domain.constants.Page;
import java.util.Iterator;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Client1
 */
public class CustomDJJRDesignHelper  extends DJJRDesignHelper{
    
    private static final Log log = LogFactory.getLog(DJJRDesignHelper.class);
    
    public static DynamicJasperDesign getNewDesign(DynamicReport dr) {
		log.info("obtaining DynamicJasperDesign instance");
		DynamicJasperDesign des = new DynamicJasperDesign();
		DynamicReportOptions options = dr.getOptions();
		Page page = options.getPage();

		des.setPrintOrder( PrintOrderEnum.VERTICAL );

        OrientationEnum orientation = OrientationEnum.PORTRAIT;
        if (!page.isOrientationPortrait()){
            orientation = OrientationEnum.LANDSCAPE;
        }
		des.setOrientation(orientation);

		des.setPageWidth(page.getWidth());
		des.setPageHeight(page.getHeight());

		des.setColumnWidth(options.getColumnWidth());
		des.setColumnSpacing(options.getColumnSpace().intValue());
		des.setLeftMargin(options.getLeftMargin().intValue());
		des.setRightMargin(options.getRightMargin().intValue());
		des.setTopMargin(options.getTopMargin().intValue());
		des.setBottomMargin(options.getBottomMargin().intValue());

        if (dr.getLanguage() != null)
            des.setLanguage(dr.getLanguage().toLowerCase());

        JRDesignSection detailSection = (JRDesignSection) des.getDetailSection();
        detailSection.getBandsList().add(new JRDesignBand());

		des.setPageHeader(new JRDesignBand());
		des.setPageFooter(new JRDesignBand());
                des.setColumnFooter(new JRDesignBand());
		des.setSummary(new JRDesignBand());

		//Behavior options
		populateBehavioralOptions(dr, des);

		if (dr.getQuery() != null){
			JRDesignQuery query = getJRDesignQuery(dr);
			des.setQuery(query);
		}

		for (Iterator iterator = dr.getProperties().keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			des.setProperty(name, (String) dr.getProperties().get(name));
		}

		des.setName(dr.getReportName() != null ? dr.getReportName() : "DJR");
		return des;
	}

}
