package com.epam;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Mikhail_Churakov on 5/14/2017.
 */
public class DataProviders {
    private String csvFile = "C:\\Users\\Mikhail_Churakov\\IdeaProjects\\Homework2\\Book1.csv";

    @DataProvider
    public Object[][] correctData() {
        return new Object[][]{
                {"epam", "1234"},
        };
    }

    @DataProvider
    public Object[][] incorrectDataCsv()  {
        return new Object[][]{
                {"Epam", "1234"},
                {"epam","12345"},
                {"epam",""},
                {"","12345"}
        };
    }
}
