package lesson_17;
import lesson_15.Links;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserCabinetPage {

    private static final Logger logger = LogManager.getLogger(UserCabinetPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    private By dropdownTrigger = By.xpath("//div[contains(@class, 'flex') and contains(@class, 'gap-1.5') and contains(@class, 'justify-center') and contains(@class, 'self-end')]");
    private By firstDropdownOption = By.xpath("//div[contains(text(), 'Select')]");
    private By secondDropdownOption = By.xpath("//div[contains(text(), 'Drag & Drop')]");
    private By thirdDropdownOption = By.xpath("//div[contains(text(), 'Actions, Alerts & Iframes')]");

    public UserCabinetPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("UserCabinetPage initialized with WebDriver");
    }

    public void hoverOverDropdown() {
        logger.info("Hovering over dropdown");
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownTrigger));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();
        logger.debug("Successfully hovered over dropdown");
    }

    public void selectFirstDropdownOption() {
        logger.info("Selecting first dropdown option");
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(firstDropdownOption));
        optionToSelect.click();
        logger.debug("Successfully selected first dropdown option");
    }

    public void selectSecondDropdownOption() {
        logger.info("Selecting second dropdown option");
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(secondDropdownOption));
        optionToSelect.click();
        logger.debug("Successfully selected second dropdown option");
    }

    public void selectThirdDropdownOption() {
        logger.info("Selecting third dropdown option");
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(thirdDropdownOption));
        optionToSelect.click();
        logger.debug("Successfully selected third dropdown option");
    }

}
