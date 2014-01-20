/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.layouts;

import ar.com.fdvs.dj.core.CoreException;
import ar.com.fdvs.dj.core.DJDefaultScriptlet;
import ar.com.fdvs.dj.core.DJJRDesignHelper;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import static ar.com.fdvs.dj.core.DynamicJasperHelper.generateJasperPrint;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.DynamicReport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Client1
 */
public class CustomJasperHelper extends DynamicJasperHelper {

    private static final Random random = new Random(System.currentTimeMillis());
    private static final Log log = LogFactory.getLog(CustomJasperHelper.class);

    
    protected static DynamicJasperDesign generateJasperDesign(DynamicReport dr) {
        DynamicJasperDesign jd = null;
        //Create new JasperDesign from the scratch
        jd = CustomDJJRDesignHelper.getNewDesign(dr);

        //Force a unique name to the report
        jd.setName("" + jd.getName() + "_" + random.nextInt(10000));

        log.debug("The name for this report will be: " + jd.getName());

        jd.setScriptletClass(DJDefaultScriptlet.class.getName()); //Set up scripttlet so that custom expressions can do their magic
        registerParameters(jd, dr);

        return jd;
    }
}
