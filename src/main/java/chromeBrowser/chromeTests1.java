package chromeBrowser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class chromeTests1 {
    MobileDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","android");
        caps.setCapability("deviceName","Nageshemulator");
        caps.setCapability("appPackage","com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset",true);
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
       // wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void chromeTest1(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.training-support.net/selenium");
        driver.findElement(MobileBy.AndroidUIAutomator(
                "UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
        driver.findElement(
                By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]"))
                .click();
        //Add Task
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Add Tasks to list");
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Get number of tasks");
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys("Clear the list");
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
        //verify tasks added to the list
        Assert.assertTrue(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add tasks to list\")")).isDisplayed());
        Assert.assertTrue(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Clear the list\")")).isDisplayed());
        Assert.assertTrue(driver.findElement(MobileBy.AndroidUIAutomator("text(\"Get number of tasks\")")).isDisplayed());
        //Strike
        List<MobileElement> listOfTasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
        for (MobileElement task : listOfTasks) {
            task.click();
        }
        driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();

        List<MobileElement> pendingTasks = driver
                .findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
        Assert.assertEquals(pendingTasks.size(), 0);
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
