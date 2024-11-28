package TestDesign.Listeners;

import TestDesign.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult testResult){
        try{
            Object currentClass = testResult.getInstance();
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            takesScreenshot(driver, testResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void takesScreenshot(WebDriver driver, ITestResult iTestResult){
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File tempFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        String screenshotName = iTestResult.getName()+"_"+date.toString()
                .replace(":","_").replace(" ","_");

        try {
            FileUtils.copyFile(tempFile, new File("./Screenshots/"+screenshotName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
