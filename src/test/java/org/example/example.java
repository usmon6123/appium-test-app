package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.example.query.TestQuery;
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
// Java dilinde yazilan kodlarin olusturulmasi
public class example {

    // Ozellikle cagirilacak olan driver ve wait nesneleri tanimlandi
    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    // Ekranin cikarilmasini saglayan butonun id'si
    static By skip = By.id("android:id/button2");


    // Yeni not ekle butonunun id'si
    static By plus = By.id("com.miui.notes:id/content_add");

    // Notun basligi olan textviewun id'si
    static By title = By.id("com.miui.notes:id/note_title");

    // Notun icerikleri olan textviewun id'si
    static By content = By.id("com.miui.notes:id/rich_editor");

    // Kaydet butonunun id'si
    static By save = By.id("com.miui.notes:id/done");

    // Ana ekranin cikarilmasini saglayan butonun id'si
    static By home = By.id("com.miui.notes:id/home");

    // Notlarin cikarilmasini saglayan butonun xpath'si
    static By notesButton = By.xpath("//android.widget.FrameLayout[@resource-id=\"com.miui.notes:id/content\"]");

    // Burda Appium Server ile baglantisini kuruyoruz.
    @BeforeTest
    public void beforeTest() {
        try {
            // DesiredCapabilities nesnesi tanimlandi
            DesiredCapabilities cap = new DesiredCapabilities();

            // Cagirilacak cihazin ismi ve OS versiyonu verildi
            cap.setCapability("deviceName", "Redmi 9T");
            cap.setCapability("platformName", "android");
            cap.setCapability("platformVersion", "10.0");
            cap.setCapability("udid", "93e3d5f00920");

            // Uygulama paketi ve aktivitesi verildi
            cap.setCapability("appPackage", "com.miui.notes");
            cap.setCapability("appActivity", ".ui.NotesListActivity");

            // Cikarilacak ekranin unutulmamasi saglandi
            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "true");

            // AppiumDriver nesnesi tanimlandi
            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4724/wd/hub"), cap);

            // WebDriverWait nesnesi tanimlandi
            wait = new WebDriverWait(driver, 100000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // telefonla baglanti kurulduktan sonra tamam isler bu metodda oluyor.
    @Test
    public void test() throws SQLException, InterruptedException {

        // TestQuery sınıfının nesnesi tanimlandi
        TestQuery query = new TestQuery();

        while (true) {

            // Veri var mı kontrol edildi
            if (query.newData()) {

                // Test sınıfının nesnesi tanimlandi
                org.example.modul.Test test = query.getTest();

                // Cihaza yazdirma islemi yapiliyor
                phoneEdit(test.getTitle(), test.getSms());

                // Veri guncellenme islemi yapiliyor
                query.updateStatusSuccess(test.getId());
            }
            //yeni veri gelmemisse
            else {
                // Not kapanmamasi icin bos yere tiklaniyor
                WebElement defaultClick = driver.findElement(notesButton);
                defaultClick.click();

                // Zaman adi arttırılarak cikarilma zamani arttırılıyor
                driver.manage().timeouts().implicitlyWait(5, SECONDS);

                // 500 milisaniye bekleniyor
                Thread.sleep(500);
            }
        }
    }

    // Test sonlandirildiktan sonra calistirilacak olan metod
    @AfterTest
    public void afterTest() {
        // Driver kapatilacak
        driver.quit();
    }

    // Cihaza yazdirma islemi
    public void phoneEdit(String titleText, double sms) {

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        // Yeni not ekle butonu cagiriliyor
        WebElement plusE = driver.findElement(plus);

        // Yeni not ekle butonu cagirildi
        plusE.click();

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        // Notun basligi olan textviewu cagiriliyor
        WebElement titleE = driver.findElement(title);

        // Notun basligi olan textviewu cagirildi
        titleE.click();

        // Txt kutusundaki veriler yazdiriliyor
        driver.getKeyboard().pressKey(titleText);

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        // Notun icerikleri olan textviewu cagiriliyor
        WebElement contentE = driver.findElement(content);

        // Notun icerikleri olan textviewu cagirildi
        contentE.click();

        // Txt kutusundaki veriler yazdiriliyor
        driver.getKeyboard().pressKey(String.valueOf(sms));

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        // Kaydet butonu cagiriliyor
        WebElement saveE = driver.findElement(save);

        // Kaydet butonu cagirildi
        saveE.click();

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        // Ana ekranin cikarilmasini saglayan butonu cagiriliyor
        WebElement homeE = driver.findElement(home);

        // Ana ekranin cikarilmasini saglandi
        homeE.click();

        // Zaman adi arttırılarak cagirilma zamani arttırılıyor
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }
}