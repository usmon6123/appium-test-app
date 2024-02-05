package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CalculatorTest {

    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    By deviceButton = By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.app.ActionBar.Tab[2]");

    By systemButton = By.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.app.ActionBar.Tab[3]");

    @BeforeTest
    public void beforeTest() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();


            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");

            cap.setCapability("appPackage", "com.cpuid.cpu_z");
            cap.setCapability("appActivity", "com.cpuid.cpu_z.MainActivity");

            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "false");
//            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);
//            cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        WebElement selectDevice = driver.findElement(deviceButton);
        selectDevice.click();

        driver.manage().timeouts().implicitlyWait(10, SECONDS);


        WebElement systemDevice = driver.findElement(systemButton);
        systemDevice.click();

        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }

}
