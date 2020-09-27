package googleTasks;

import io.appium.java_client.AppiumDriver;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class googleTasks1 {

    WebDriverWait wait;
    public static AppiumDriver<MobileElement> driver=null;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Nageshemulator");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.google.android.apps.tasks");
        caps.setCapability("appActivity",".ui.TaskListsActivity" );
        caps.setCapability("noReset","true");

        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task1(){
        //Open Google Tasks app
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<String> taskList=new ArrayList<String>();
        taskList.add("Complete Activity with Google Tasks");
        taskList.add("Complete Activity with Google Keep");
        taskList.add("Complete the second activity with google keep");
        for(int i=0; i<taskList.size(); i++){
            System.out.println(taskList.get(i));
            driver.findElementByAccessibilityId("Create new task").click();
            driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys(taskList.get(i));
            driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
        }

        List<MobileElement> taskListOut = driver.findElementsById("com.google.android.apps.tasks:id/task_item_layout");
        int listSizes = taskListOut.size();

        System.out.println(listSizes);
        Assert.assertEquals(listSizes,3);




    }

    @AfterTest
    public void tearDown(){


    }
}
