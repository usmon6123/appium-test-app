package org.example.samsung_s21;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.example.database.DatabaseConnection;
import org.example.m10.OrderModul;
import org.example.query.TestQuery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class NotesSamsung_s21 {

    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    By newNote = By.id("com.samsung.android.app.notes:id/inflate_note_fab");
    By defaultClick = By.xpath("(//android.widget.TextView[@text=\"Все заметки\"])[2]");
    By cancel = By.id("com.samsung.android.app.notes:id/composer_toolbar_navigate");

    @BeforeTest
    public void beforeTest() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "SM_G981N");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "13");

            cap.setCapability("appPackage", "com.samsung.android.app.notes");
            cap.setCapability("appActivity", ".memolist.MemoListBaseActivity");
            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");
//androidx.biometric.DeviceCredentialHandlerActivity

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    By firstClick = By.xpath("//android.view.ViewGroup[@resource-id=\"com.samsung.android.app.notes:id/zoom_lock_tip\"]");
    @Test
    public void test() throws InterruptedException, SQLException {
        TestQuery testQuery = new TestQuery();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        for (; true; ) {
            if (testQuery.newData()) {
                org.example.modul.Test test = testQuery.getTest();
//                Thread.sleep(10000);
                System.out.println(test.toString());
                helper(test.getTitle(), test.getSms());
                testQuery.updateStatusSuccess(test.getId());
            } else {
                driver.manage().timeouts().implicitlyWait(10, SECONDS);
                WebElement selectDevice = driver.findElement(defaultClick);
                selectDevice.click();
            }
            Thread.sleep(500);
        }

    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }


    public void helper(String titleText, double sms) {

        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        WebElement firstButton = driver.findElement(newNote);
        firstButton.click();
        driver.getKeyboard().pressKey(titleText);

        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        WebElement homeE = driver.findElement(cancel);
        homeE.click();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        homeE.click();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

}
