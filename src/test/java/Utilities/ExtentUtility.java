package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BasePackage.BaseClass;

public class ExtentUtility implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    // Global Variables
    public static String repName;
    public static String reportPath;

    // ON START
    @Override
    public void onStart(ITestContext context) {

        // Time Stamp
        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                        .format(new Date());

        // Report Name
        repName = "TestReport_" + timeStamp + ".html";

        // Report Path
        reportPath = System.getProperty("user.dir")
                + "/reports/"
                + repName;

        // Spark Reporter
        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("Extent Report");

        sparkReporter.config().setReportName("Automation Report");

        sparkReporter.config().setTheme(Theme.DARK);

        // Extent Reports
        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        // System Info
        extent.setSystemInfo("Tester", "Vidhya");

        extent.setSystemInfo("Environment", "QA");

        extent.setSystemInfo("Username",
                System.getProperty("user.name"));

        String os =
                context.getCurrentXmlTest()
                        .getParameter("os");

        extent.setSystemInfo("Operating System", os);

        String browser =
                context.getCurrentXmlTest()
                        .getParameter("browser");

        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups =
                context.getCurrentXmlTest()
                        .getIncludedGroups();

        if (!includedGroups.isEmpty()) {

            extent.setSystemInfo(
                    "Groups",
                    includedGroups.toString());
        }
    }

    // TEST SUCCESS
    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());

        test.assignCategory(
                result.getMethod().getGroups());

        test.log(
                Status.PASS,
                result.getName()
                        + " got successfully executed");
    }

    // TEST FAILURE
    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());

        test.assignCategory(
                result.getMethod().getGroups());

        test.log(
                Status.FAIL,
                result.getName()
                        + " got failed");

        test.log(
                Status.FAIL,
                result.getThrowable());

        try {

            String imgPath =
                    new BaseClass()
                            .captureScreen(
                                    result.getName());

            test.addScreenCaptureFromPath(imgPath);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // TEST SKIPPED
    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());

        test.assignCategory(
                result.getMethod().getGroups());

        test.log(
                Status.SKIP,
                result.getName()
                        + " got skipped");

        test.log(
                Status.INFO,
                result.getThrowable());
    }

    // ON FINISH
    @Override
    public void onFinish(ITestContext context) {

        // Flush Report
        extent.flush();

        // Open Report Automatically
        File extentReport = new File(reportPath);

        try {

            Desktop.getDesktop()
                    .browse(extentReport.toURI());

        } catch (IOException e) {

            e.printStackTrace();
        }
/*
        // SEND EMAIL
        try {

            URL url =
                    new File(reportPath)
                            .toURI()
                            .toURL();

            // Create Email
            ImageHtmlEmail email =
                    new ImageHtmlEmail();

            email.setDataSourceResolver(
                    new DataSourceUrlResolver(url));

            // SMTP
            email.setHostName("smtp.googlemail.com");

            email.setSmtpPort(465);

            // Gmail & App Password
            email.setAuthenticator(
                    new DefaultAuthenticator(
                            "yourgmail@gmail.com",
                            "your_app_password"));

            email.setSSLOnConnect(true);

            // Sender
            email.setFrom("yourgmail@gmail.com");

            // Subject
            email.setSubject("Automation Test Results");

            // Message
            email.setMsg(
                    "Please find attached extent report");

            // Receiver
            email.addTo("receiver@gmail.com");

            // Attach Report
            email.attach(
                    url,
                    "Extent Report",
                    "Automation Execution Report");

            // Send
            email.send();

            System.out.println(
                    "Email Sent Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }*/
    }
}