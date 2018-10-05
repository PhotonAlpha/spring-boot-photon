/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.controller;

import com.ethan.common.service.PrintReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-boot
 * @description: This controller is used to Reference to regular the controller 
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-27 15:48
 **/
@RestController
@Api(value = "PrintReportController", description = "Used to Print PDF Reports", tags = "Print PDF Reports")
public class PrintReportController extends BasicController{
    @Autowired
    private PrintReportService reportService;

    @ApiOperation(value = "export pdf")
    @GetMapping(value = "booklet/print")
    public ResponseEntity<?> getData(HttpServletRequest request, HttpServletResponse response) {
        try {
            final byte[] data = reportService.exportReports();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_PDF);
            // header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employeeReport.pdf");

            // for view
            header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.pdf");
            header.setContentLength(data.length);
            return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
