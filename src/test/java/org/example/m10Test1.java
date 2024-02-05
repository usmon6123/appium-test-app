package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.example.m10.OrderModul;
import org.example.m10.QueryM10;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.sql.SQLException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class m10Test1 {

    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    By start = By.xpath("//android.view.View[@content-desc=\"0.00₼\"]");
    By topUp = By.xpath("//android.widget.ImageView[@content-desc=\"Top up\"]");

    @BeforeTest
    public void beforeTest() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", "93e3d5f00920");


            cap.setCapability("appPackage", "com.m10");
            cap.setCapability("appActivity", ".MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 100000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    By balanceText = By.xpath("//android.widget.EditText[@text=\"0 ₼\"]");
    By continueButton = By.xpath("//android.view.View[@content-desc=\"Continue\"]");

    By view = By.xpath("//android.webkit.WebView");

    By scanCard = By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]");
    By cardNumber = By.xpath("//android.widget.EditText[@resource-id=\"cp-pan-decor\"]");
    By cardYear = By.xpath("//android.widget.EditText[@resource-id=\"cp-expiration-date\"]");

    By cvv = By.xpath("//android.widget.EditText[@resource-id=\"cvv2\"]");

    By register = By.xpath("//android.widget.CheckBox[@resource-id=\"registerCard\"]");
    //    --------------------
    By backButton = By.xpath("//android.widget.ImageView");
    By payButton = By.xpath("//android.widget.Button[@resource-id=\"OK\"]");
    By cancelButton = By.xpath("//android.widget.Button[@text=\"Cancel\"]");
    //    -----errors------------------------
    By cardNumberWrong = By.xpath("//android.view.View[@resource-id=\"pan-error\"]");
    By cardYearWrong = By.xpath("//android.view.View[@resource-id=\"ExpYear-error\"]");
    By cvvError = By.xpath("//android.view.View[@resource-id=\"cvv2-error\"]");

    @Test
    public void test() throws SQLException, InterruptedException {
        QueryM10 queryM10 = new QueryM10();
        for (; true; ) {
            if (queryM10.newData()) {

                OrderModul orderModul = queryM10.getTransaction();

                queryM10.updateStatusSuccess(orderModul.getId());
                Thread.sleep(2000);

                appium(orderModul);
            } else {
                driver.manage().timeouts().implicitlyWait(10, SECONDS);
                WebElement selectDevice = driver.findElement(start);
                selectDevice.click();
            }


            Thread.sleep(1000);
        }

    }


    public void appium(OrderModul order) throws SQLException, InterruptedException {

        QueryM10 queryM10 = new QueryM10();

        System.out.println("manuel olarak parolayi girit");
        driver.manage().timeouts().implicitlyWait(100, SECONDS);

        WebElement selectDevice = driver.findElement(topUp);
        selectDevice.click();

        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        System.out.println(driver.findElement(balanceText).getText());

        WebElement balanceTextE = driver.findElement(balanceText);
        balanceTextE.click();
        driver.getKeyboard().pressKey(String.valueOf(order.getPrice()));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        WebElement continueButtonE = driver.findElement(continueButton);
        continueButtonE.click();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        if (driver.findElement(view) != null) {
            WebElement viewE = driver.findElement(view);
            viewE.click();
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
        }

        WebElement cardNumberE = driver.findElement(cardNumber);
        cardNumberE.click();
        Thread.sleep(1000);
        if (driver.findElement(scanCard) != null) {
            cardNumberE.click();
        }

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        cardNumberE.click();

        MobileElement cardNumberE2 = (MobileElement) driver.findElement(cardNumber);
        cardNumberE2.sendKeys(order.getCardNumber());


        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        MobileElement cardYearE = (MobileElement) driver.findElement(cardYear);
        cardYearE.sendKeys(order.getCardYear());

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        MobileElement cvvE = (MobileElement) driver.findElement(cvv);
        cvvE.sendKeys(order.getCvv2());


        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        WebElement registerE = driver.findElement(register);
        registerE.click();

        if (driver.findElement(cardNumberWrong) != null || driver.findElement(cardYearWrong) != null || driver.findElement(cvvError) != null) {

            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            WebElement back = driver.findElement(backButton);
            back.click();
            Thread.sleep(500);

            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            WebElement back2 = driver.findElement(backButton);
            back2.click();
            Thread.sleep(500);
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            queryM10.updateStatus(order.getId(), "Wrong", "fake Number Card");
            System.out.println("fake transaction");
        } else {
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            WebElement pay = driver.findElement(payButton);
            pay.click();
            Thread.sleep(500);


            try {
                if (driver.findElement(payButton) != null) {

                    driver.manage().timeouts().implicitlyWait(10, SECONDS);
                    WebElement back = driver.findElement(backButton);
                    back.click();
                    Thread.sleep(500);

                    driver.manage().timeouts().implicitlyWait(10, SECONDS);
                    WebElement back2 = driver.findElement(backButton);
                    back2.click();
                    Thread.sleep(500);
                    driver.manage().timeouts().implicitlyWait(10, SECONDS);
                    System.out.println("error transaction");
                }
            } catch (Exception e) {
                System.out.println("success transaction");
            }
        }
    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }


}
