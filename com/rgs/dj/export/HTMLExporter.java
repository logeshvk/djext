/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.export;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.output.ReportWriter;
import ar.com.fdvs.dj.output.ReportWriterFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/**
 *
 * The HTML exporter can send data to output stream. HTMLExporter will export
 * the report into an html format.
 */
public class HTMLExporter {

    /**
     * 
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     * @param imageServletUrl -Image Servlet Url 
     * @param _jasperPrint JasperPrint
     * @param parameters - Report Parameters
     * @param exporterParams - Export Parameters
     * @throws JRException
     * @throws IOException 
     */

    public static void exportToHtml(HttpServletRequest request,
            HttpServletResponse response,
            String imageServletUrl,
            JasperPrint _jasperPrint,
            Map parameters,
            Map exporterParams) throws JRException, IOException {
        if (parameters == null) {
            parameters = new HashMap();
        }
        if (exporterParams == null) {
            exporterParams = new HashMap();
        }

        final ReportWriter reportWriter = ReportWriterFactory.getInstance().getReportWriter(_jasperPrint, DJConstants.FORMAT_HTML, parameters);
        parameters.put(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + imageServletUrl);

        Map imagesMap = new HashMap();
        JRExporter exporter = reportWriter.getExporter();
        exporter.setParameters(exporterParams);

        exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/" + imageServletUrl + "?image=");
        // Needed to support chart images:
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, _jasperPrint);
        HttpSession session = request.getSession();
        session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, _jasperPrint);
        session.setAttribute("net.sf.jasperreports.j2ee.jasper_print", _jasperPrint);

        //write generated HTML to the http-response (the one you got from the helper)
        reportWriter.writeTo(response);
    }
}
