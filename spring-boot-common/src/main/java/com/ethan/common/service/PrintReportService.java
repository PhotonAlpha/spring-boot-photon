package com.ethan.common.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface PrintReportService {
    byte[] exportReports() throws JRException, IOException;
}
