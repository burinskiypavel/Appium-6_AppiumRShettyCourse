package org.rahulshettyacademy;

import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.rahulshettyacademy.utils.AndroidActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends AndroidBaseTest {
    AndroidActions androidActions;


    @BeforeMethod
    public void preSetup(){
        formPage.setActivity();
    }

    @Test
    public void fillForm_ErrorValidation(){
        androidActions = new AndroidActions(driver);

        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        androidActions.scrollToTheElement("Argentina");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void fillForm_PositiveFlow(){

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        androidActions.scrollToTheElement("Argentina");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
    }
}