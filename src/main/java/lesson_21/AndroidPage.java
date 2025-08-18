package lesson_21;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AndroidPage {
    AppiumDriver driver;
    WebDriverWait wait;

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final class Locators {
        // Основные локаторы
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By viewButtons = AppiumBy.xpath("//android.widget.TextView[@content-desc]");
        private static final By textSwitcher = AppiumBy.accessibilityId("TextSwitcher");
        private static final By nextButton = AppiumBy.accessibilityId("Next");
        private static final String COUNTER_XPATH_TEMPLATE = "//android.widget.TextView[@text='%d']";

        // Локаторы для работы с датой и временем
        private static final By dataWidgets = AppiumBy.accessibilityId("Date Widgets");
        private static final By dialogButton = AppiumBy.accessibilityId("1. Dialog");
        private static final By changeDateButton = AppiumBy.accessibilityId("change the date");
        private static final By calendarView = AppiumBy.xpath("//android.view.View[@resource-id='android:id/month_view']");
        private static final By okButton = AppiumBy.id("android:id/button1");
        private static final By dateDisplay = AppiumBy.id("io.appium.android.apis:id/dateDisplay");
        private static final By changeTimeButton = AppiumBy.accessibilityId("change the time (spinner)");

        // Локаторы для выбора времени
        private static final By hourPicker = AppiumBy.xpath("//android.widget.EditText[@resource-id='android:id/numberpicker_input']");
        private static final By minutePicker = AppiumBy.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[2]");
        private static final By pmButton = AppiumBy.xpath("//android.widget.Button[@text='PM']");
    }

    public void clickOnViews() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
    }

    public int countViewButtons() {
        Set<String> uniqueButtonTexts = new HashSet<>();
        int previousSize = 0;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            List<WebElement> currentButtons = driver.findElements(Locators.viewButtons);
            for (WebElement button : currentButtons) {
                uniqueButtonTexts.add(button.getText());
            }
            if (uniqueButtonTexts.size() == previousSize) {
                attempts++;
            } else {
                attempts = 0;
            }
            previousSize = uniqueButtonTexts.size();
            try {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
            } catch (Exception e) {
                break;
            }
        }
        return uniqueButtonTexts.size();
    }

    public void scrollUntilTextSwitcherAndClick() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.textSwitcher)).click();
    }

    public void clickOnNextButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.nextButton)).click();
    }

    public void checkNextButtonCounter(int expectedClicks) {
        for (int i = 1; i <= expectedClicks; i++) {
            clickOnNextButton();
            By counterLocator = AppiumBy.xpath(String.format(Locators.COUNTER_XPATH_TEMPLATE, i));
            WebElement counterElement = wait.until(ExpectedConditions.visibilityOfElementLocated(counterLocator));
            wait.until(ExpectedConditions.textToBePresentInElement(counterElement, String.valueOf(i)));
            assert counterElement.getText().equals(String.valueOf(i)) :
                    String.format("The counter does not match the expected value. Expected: %d, Actual: %s", i, counterElement.getText());
        }
    }

    public void clickOnDataWidgets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dataWidgets)).click();
    }

    public void clickOnDialogButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dialogButton)).click();
    }

    public void setTomorrowDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeDateButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.calendarView));

        List<WebElement> dates = driver.findElements(By.xpath("//android.view.View[@content-desc]"));
        WebElement currentDate = driver.findElement(By.xpath("//android.view.View[@selected='true']"));
        int currentIndex = dates.indexOf(currentDate);
        if (currentIndex != -1 && currentIndex + 1 < dates.size()) {
            dates.get(currentIndex + 1).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.okButton)).click();
    }

    public void setTimeTo11_11_PM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTimeButton)).click();

        WebElement hourPickerElement = wait.until(ExpectedConditions.elementToBeClickable(Locators.hourPicker));
        hourPickerElement.clear();
        hourPickerElement.sendKeys("11");

        WebElement minutePickerElement = wait.until(ExpectedConditions.elementToBeClickable(Locators.minutePicker));
        minutePickerElement.clear();
        minutePickerElement.sendKeys("11");

        WebElement pmButtonElement = wait.until(ExpectedConditions.elementToBeClickable(Locators.pmButton));
        pmButtonElement.click();

        wait.until(ExpectedConditions.elementToBeClickable(Locators.okButton)).click();
    }

    public void verifyDateTimeDisplay() {
        WebElement dateDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dateDisplay));
        String actualDateTime = dateDisplayElement.getText();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String expectedDate = tomorrow.format(DateTimeFormatter.ofPattern("M-d-yyyy"));
        assert actualDateTime.contains(expectedDate) && actualDateTime.contains("11:11") :
                String.format("The displayed date/time does not match the expected value. Expected to contain: %s 11:11, Actual: %s",
                        expectedDate, actualDateTime);
    }
}