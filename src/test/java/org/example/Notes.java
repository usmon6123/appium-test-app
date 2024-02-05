package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Notes {

    String titleText;
    double sms;
    public Notes(String title, double sms) {
    }

    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

//    static By searchTextButton = By.xpath("//android.widget.TextView[@content-desc=\"Original language: English.\"]");
        static By skip = By.id("android:id/button2");
        static By plusBefore = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout");
        static By plus = By.id("com.miui.notes:id/content_add");



//        static By plusAfter = By.id("");

    @BeforeTest
    public void beforeTest() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();


            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");


            cap.setCapability("appPackage", "com.miui.notes");
            cap.setCapability("appActivity", ".ui.NotesListActivity");


            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");
//            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);
//            cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

            wait = new WebDriverWait(driver, 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static  By title = By.id("com.miui.notes:id/note_title");
    static  By content = By.id("com.miui.notes:id/rich_editor");
    static By save = By.id("com.miui.notes:id/done");

    static By home = By.id("com.miui.notes:id/home");

    @Test
    public void test() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        WebElement plusE = driver.findElement(plus);
        plusE.click();

        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        WebElement titleE = driver.findElement(title);
        titleE.click();
        driver.getKeyboard().pressKey(titleText);

        driver.manage().timeouts().implicitlyWait(5,SECONDS);

        WebElement contentE = driver.findElement(content);
        contentE.click();
        driver.getKeyboard().pressKey(String.valueOf(sms));

        driver.manage().timeouts().implicitlyWait(5,SECONDS);

        WebElement saveE = driver.findElement(save);
        saveE.click();

        driver.manage().timeouts().implicitlyWait(5,SECONDS);

        WebElement homeE = driver.findElement(home);
        homeE.click();
        driver.manage().timeouts().implicitlyWait(5,SECONDS);


    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }
}
