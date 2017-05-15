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
        Object incorrectData[][] = null;
        try {
            int i = 0;
            int j = 0;
            String testLine = "";
            BufferedReader file = new BufferedReader(new FileReader(csvFile));
            while ((file.readLine()) !=null)
                j++;
            incorrectData = new Object[j][2];
            file = new BufferedReader(new FileReader(csvFile));
            while ((testLine = file.readLine()) !=null) {
                String[] row = testLine.split(",");
                incorrectData[i][0] = row[0];
                incorrectData[i][1] = row[1];
                i++;
            }
        } catch (IOException ex) {
            System.out.println("File is not found");
        }
        return incorrectData;
    }
}
