package org.rahulshettyacademy.TestUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulshettyacademy.padeObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fls = new FileInputStream("src/main/java/org/rahulshettyacademy/resources/data.properties");
        String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("idAddress");
        prop.load(fls);
        //String ipAddress = prop.getProperty("idAddress");
        String port = prop.getProperty("port");

        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        //options.setDeviceName("Android Device");
        //options.setCapability("deviceName", "Galaxy A12");
        //options.setCapability("deviceName", "Redmi Note 4");
        options.setCapability("deviceName", "Pixel 2");

        //options.setApp("C:\\Users\\user\\IdeaProjects\\appium-5\\src\\test\\java\\resources\\ApiDemos-debug.apk");
        //options.setApp("src\\test\\java\\resources\\ApiDemos-debug.apk");
        options.setApp("src/test/java/org/rahulshettyacademy/resources/General-Store.apk");
        options.setChromedriverExecutable("driver/chromedriver.exe");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
        System.out.println("Started fine");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        service.stop();
        driver.quit();
    }
}