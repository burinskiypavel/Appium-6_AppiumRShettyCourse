package org.rahulshettyacademy.utils;

import com.aventstack.extentreports.utils.FileUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppiumUtils {
    //AppiumDriver driver;
    public AppiumDriverLocalService service;


    //public AppiumUtils(AppiumDriver driver){
    //    this.driver = driver;
    //}

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port){
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\user\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public void waitForElementsToAppear(WebElement ele, AppiumDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinaionFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinaionFile));
        return destinaionFile;
    }
}