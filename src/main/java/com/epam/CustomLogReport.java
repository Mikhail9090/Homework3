package com.epam;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mikhail_Churakov on 5/14/2017.
 */
public class CustomLogReport implements IReporter {
    private String dirReport = "test-report";
    private PrintWriter mOut;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        outputDirectory = dirReport;
        new File(outputDirectory).mkdirs();
        try {
            mOut = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                    outputDirectory, "custom-report.html"))));
        } catch (IOException e) {
            System.out.println("Error in creating writer: " + e);
        }
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            mOut.print("Suite Name: " + suiteName + "<br>");
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (String testName : suiteResults.keySet()) {
                mOut.print("Test Name: " + testName + "<br>");
                ISuiteResult suiteResult = suiteResults.get(testName);
                ITestContext testContext = suiteResult.getTestContext();
                mOut.print("FAILED TEST-CASES: " + testContext.getFailedTests().size() + "<br>");
                IResultMap passResult = testContext.getPassedTests();
                Set testsPassed = passResult.getAllResults();
                mOut.print("PASSED TEST-CASES: " + testsPassed.size() + "<br>");
                IResultMap skippedResult = testContext.getSkippedTests();
                Set testsSkipped = skippedResult.getAllResults();
                mOut.print("SKIPPED TEST-CASES: " + testsSkipped.size() + "<br>");
            }
        }
        mOut.flush();
        mOut.close();
    }
}
