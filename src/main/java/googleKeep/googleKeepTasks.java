package googleKeep;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class googleKeepTasks {
    WebDriverWait wait;
    public static AppiumDriver<MobileElement> driver = null;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Nageshemulator");
        caps.setCapability("platformName","android");
        caps.setCapability("packageName","com.google.android.keep");
        caps.setCapability("activityName",".activities.BrowseActivity");
        caps.setCapability("noReset","true");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Test1() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<MobileElement> initialCount= driver.findElementsById("com.google.android.keep:id/browse_note_interior_content");
        System.out.println(initialCount.size());
        driver.findElementByAccessibilityId("New text note").click();
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Important note");
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("title notes");
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        List<MobileElement> endCount= driver.findElementsById("com.google.android.keep:id/browse_note_interior_content");
        System.out.println(endCount.size());
        Assert.assertEquals(initialCount.size()+1, endCount.size());
    }


    @Test
    public void Test2(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<MobileElement> initialCount2= driver.findElementsById("com.google.android.keep:id/browse_note_interior_content");
        System.out.println(initialCount2.size());
        driver.findElementByAccessibilityId("New text note").click();
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/editable_title\")")).sendKeys("Important note");
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.keep:id/edit_note_text\")")).sendKeys("title notes");
        driver.findElementByAccessibilityId("Reminder").click();
       // driver.findElementByAccessibilityId("com.google.android.keep:id/menu_reminder").click();
        driver.findElementById("com.google.android.keep:id/text").click();
        driver.findElementById("com.google.android.keep:id/reminder_date_tomorrow").click();
        driver.findElementById("com.google.android.keep:id/save").click();

        driver.findElementByAccessibilityId("Open navigation drawer").click();
        List<MobileElement> endCount2= driver.findElementsById("com.google.android.keep:id/browse_note_interior_content");
        System.out.println(endCount2.size());
        Assert.assertEquals(initialCount2.size()+1, endCount2.size());

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
