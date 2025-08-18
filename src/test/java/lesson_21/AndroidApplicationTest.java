package lesson_21;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.net.MalformedURLException;

public class AndroidApplicationTest {

    private AppiumDriver driver;
    private AndroidPage androidPage;

    @BeforeMethod
    public void setUp() {
        try {
            driver = new AppiumDriverInit().getDriver();
            androidPage = new AndroidPage(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to initialize driver: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Test the number of buttons on the Views screen")
    public void countViewButtonsTest_task1() {
        androidPage.clickOnViews();
        int buttonCount = androidPage.countViewButtons();
        Assert.assertTrue(buttonCount > 0, "No buttons found on the Views screen");
        System.out.println("Number of buttons on the Views screen: " + buttonCount);
    }
    @Test(description = "Test the Next button counter functionality by pressing it")
    public void nextButtonTest_task3() {
        androidPage.clickOnViews();
        androidPage.scrollUntilTextSwitcherAndClick();
        androidPage.checkNextButtonCounter(4);
    }
    @Test(description = "Set the date to tomorrow and the time to 11:11 PM")
    public void setDateAndTimeTest_task2() {
        androidPage.clickOnViews();
        androidPage.clickOnDataWidgets();
        androidPage.clickOnDialogButton();
        androidPage.setTomorrowDate();
        androidPage.setTimeTo11_11_PM();
        androidPage.verifyDateTimeDisplay();
    }
}

