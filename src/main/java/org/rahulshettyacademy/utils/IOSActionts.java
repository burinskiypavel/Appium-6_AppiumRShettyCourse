package org.rahulshettyacademy.utils;

import io.appium.java_client.ios.IOSDriver;

public class IOSActionts extends AppiumUtils {

    IOSDriver driver;

    public IOSActionts(IOSDriver driver){
        //super(driver);
        this.driver = driver;
    }
}