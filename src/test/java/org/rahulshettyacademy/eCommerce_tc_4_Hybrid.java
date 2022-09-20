package org.rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.rahulshettyacademy.padeObjects.android.CartPage;
import org.rahulshettyacademy.padeObjects.android.ProcuctCatalogue;
import org.rahulshettyacademy.utils.AndroidActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preSetup(){
        formPage.setActivity();
    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void fillForm(String name, String gender, String country) throws InterruptedException {
        AndroidActions androidActions = new AndroidActions(driver);

        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.setCountry(country);
        ProcuctCatalogue procuctCatalogue = formPage.submitForm();
        procuctCatalogue.addItemToCartByIndex(0);
        androidActions.scrollToTheElement("ADD TO CART");
        procuctCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = procuctCatalogue.goToCartPage();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        double totalSum = cartPage.getProductSum();
        double dispayFormattedSum = cartPage.getTotalAmountDisplaed();
        Assert.assertEquals(totalSum, dispayFormattedSum);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
    }

    @DataProvider
    public Object[][]getData(){
        return new Object[][] {{"Rahul Shetty", "femail", "Argentina"}, {"shetty MR", "male", "Argentina"}};
    }
}