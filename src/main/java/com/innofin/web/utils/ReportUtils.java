package com.innofin.web.utils;

import com.innofin.web.constants.FrameworkConstants;
import com.innofin.web.exceptions.FrameworkException;
import com.innofin.web.exceptions.InvalidPathForExtentReportFileException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class ReportUtils {

    private ReportUtils() {
    }

    public static String createExtentReportPath() {
        if (FrameworkConstants.override_reports.trim().equalsIgnoreCase(FrameworkConstants.NO)) {
            /**
             * Report name ->
             *
             * Windows_10_Tue_Oct_05_02_30_46_IST_2021_AutomationReport.html
             * Mac_OS_X_Tue_Feb_22_17_09_05_IST_2022_AutomationReport.html
             */
            return FrameworkConstants.EXTENT_REPORT_FOLDER_PATH + BrowserInfoUtils.getOSInfo() + "_" + DateUtils.getCurrentDate() + "_"
                    + FrameworkConstants.EXTENT_REPORT_FILE_NAME;

        } else {
            return FrameworkConstants.EXTENT_REPORT_FOLDER_PATH + "/" + FrameworkConstants.EXTENT_REPORT_FILE_NAME;
        }
    }

    public static void openReports() {
        if
        (FrameworkConstants.open_reports_after_execution.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
            try {
                Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
            } catch (FileNotFoundException fileNotFoundException) {
                throw new InvalidPathForExtentReportFileException("Extent Report file you are trying to reach is not found", fileNotFoundException.fillInStackTrace());
            } catch (IOException ioException) {
                throw new FrameworkException("Extent Report file you are trying to reach got IOException while reading the file", ioException.fillInStackTrace());
            }
        }
    }
}
