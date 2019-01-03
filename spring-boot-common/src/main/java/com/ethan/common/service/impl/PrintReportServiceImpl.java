/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.common.service.impl;

import com.ethan.common.model.dto.OrderDto;
import com.ethan.common.model.dto.OrderService;
import com.ethan.common.service.PrintReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-10-01 16:08
 **/
@Service
public class PrintReportServiceImpl implements PrintReportService {
    @Autowired
    private OrderService orderService;

    @Override
    public byte[] exportReports() throws JRException, IOException {
        JasperReport jasperReport = loadTemplate();
        OrderDto order = orderService.getOrderByCode("XYZ123456789");
        // Create parameters map.
        final Map<String, Object> parameters = loadParameters(order, Locale.ENGLISH);
        // Create an empty datasource.
        final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

    }

    private Map<String, Object> loadParameters(OrderDto order, Locale locale) throws IOException {
        final Map<String, Object> parameters = new HashMap<>();
        final InputStream logo_stream = new ClassPathResource("/template/images/stackextend-logo.png").getInputStream();
        parameters.put("logo", logo_stream);
        parameters.put("order",  order);
        parameters.put("REPORT_LOCALE", locale);

        return parameters;
    }

    private JasperReport loadTemplate() throws JRException, IOException {
        final InputStream resourceContent = new ClassPathResource("/template/invoice_template.jrxml").getInputStream();
        final JasperDesign jasperDesign = JRXmlLoader.load(resourceContent);
        return JasperCompileManager.compileReport(jasperDesign);
    }
}
