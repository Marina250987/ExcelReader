package lesson_18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserCabinetPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'flex') and contains(@class, 'gap-1.5') and contains(@class, 'justify-center') and contains(@class, 'self-end')]")
    private WebElement dropdownTrigger;

    @FindBy(xpath = "//div[contains(text(), 'Select')]")
    private WebElement firstDropdownOption;

    @FindBy(xpath = "//div[contains(text(), 'Drag & Drop')]")
    private WebElement secondDropdownOption;

    @FindBy(xpath = "//div[contains(text(), 'Actions, Alerts & Iframes')]")
    private WebElement thirdDropdownOption;

    public UserCabinetPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public UserCabinetPage hoverOverDropdown() {
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOf(dropdownTrigger));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();
        return this;
    }

    public SelectPage selectFirstDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOf(firstDropdownOption));
        optionToSelect.click();
        return new SelectPage(driver);
    }

    public DragAndDropPage selectSecondDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOf(secondDropdownOption));
        optionToSelect.click();
        return new DragAndDropPage(driver);
    }

    public ActionsAlertsIframesPage selectThirdDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOf(thirdDropdownOption));
        optionToSelect.click();
        return new ActionsAlertsIframesPage(driver);
    }
    public static UserCabinetPage verifySuccessfulLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        System.out.println("User logged in successfully!");
        return new UserCabinetPage(driver);
    }

}
