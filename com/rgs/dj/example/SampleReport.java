/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgs.dj.example;

import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.rgs.dj.export.HTMLExporter;
import com.rgs.dj.layouts.CustomDJStyleHelper;
import com.rgs.dj.layouts.CustomJasperHelper;
import com.rgs.dj.layouts.CustomLayoutManager;
import com.rgs.dj.layouts.JasperContext;
import com.rgs.dj.layouts.JasperField;
import com.rgs.erp.presentation.common.JasperUtil;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Client1
 */
public class SampleReport {
    private JRDataSource ds;
    
    public void dynamicPO(String fileType) {
        OutputStream stream = null;

      
        try {
            


            DynamicReportBuilder mainReport = new DynamicReportBuilder();

            Integer margin = new Integer(20);
            mainReport
                    .setDetailHeight(15).setTitle("hi")
                    .setMargins(30, 20, 30, 15)
                    .setDetailHeight(new Integer(15)).setLeftMargin(margin)
                    .setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
                    .addParameter("Sample Org", String.class.getName())
                    .setColumnsPerPage(1);

            
            mainReport.addField("orderLineList", List.class.getName());
            mainReport.setPrintColumnNames(true);


            mainReport.setUseFullPageWidth(true);

            DynamicReport dr = mainReport.build(); //Finally build the report!

           
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            
            String date = df.format(new Date());

            List<JasperField> titleFields = new ArrayList<JasperField>();

            List<JasperField> columnFooterFields = new ArrayList<JasperField>();

            titleFields.add(new JasperField("", 455));
            titleFields.add(new JasperField("Sample Org", 100, 10, CustomDJStyleHelper.amount));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));

            titleFields.add(new JasperField("Purchase Invoice", 350, 15));
            titleFields.add(new JasperField("Org Address", 205, 15, CustomDJStyleHelper.amount));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));

            titleFields.add(new JasperField("To", 276, 15, CustomDJStyleHelper.borderAtLeftAndTopStyle));
            titleFields.add(new JasperField("PO Id", 100, 15, CustomDJStyleHelper.borderAtLeftAndTopStyle));
            titleFields.add(new JasperField("PO-1", 179, 15, CustomDJStyleHelper.borderAtTopRightStyle));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));

            titleFields.add(new JasperField("Vendor Name", 276, 15, CustomDJStyleHelper.borderAtLeftStyle));
            titleFields.add(new JasperField("Order Date", 100, 15, CustomDJStyleHelper.borderAtLeftStyle));
            titleFields.add(new JasperField(date, 179, 15, CustomDJStyleHelper.borderAtRightStyle));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));

            titleFields.add(new JasperField("", 276, 15, CustomDJStyleHelper.borderAtLeftStyle));
            titleFields.add(new JasperField("Payment Terms", 100, 15, CustomDJStyleHelper.borderAtLeftStyle));
            titleFields.add(new JasperField("CASH", 179, 15, CustomDJStyleHelper.borderAtBottomRightStyle));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));
            titleFields.add(new JasperField("", 0));


            JasperContext context = new JasperContext(titleFields, 6, null, 6, columnFooterFields, 6, 450);

            JasperPrint jp = CustomJasperHelper.generateJasperPrint(dr, new CustomLayoutManager(context), ds);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            stream = response.getOutputStream();

            if (fileType.equals("pdf")) {

                response.addHeader("content-disposition", "attachment; filename=DynamicJasper.pdf");
                JasperExportManager.exportReportToPdfStream(jp, stream);
                facesContext.responseComplete();

            }

            if (fileType.equals("xls")) {
                response.addHeader("content-disposition", "attachment; filename=DynamicJasper.xls");
                response.setContentType("application/xls");
                facesContext.responseComplete();

                JRXlsExporter exporter = (new JasperUtil()).getXlsExpoter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
                exporter.exportReport();

            }
            if (fileType.equals("html")) {

                String imageServletUrl = "reports/image";
                LayoutManager layoutManager = new CustomLayoutManager();
                Map parameters = new HashMap();
                Map exporterParams = new HashMap();

                exporterParams.put(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
                exporterParams.put(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                HTMLExporter.exportToHtml(request, response, imageServletUrl, jp, parameters, exporterParams);
            }
            if (fileType.equals("jasperviewer")) {
                JasperViewer.viewReport(jp);
            }
            facesContext.responseComplete();
            stream.flush();
            stream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
