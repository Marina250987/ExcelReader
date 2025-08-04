package lesson_17;
import lesson_15.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserCabinetPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By dropdownTrigger = By.xpath("//div[contains(@class, 'flex') and contains(@class, 'gap-1.5') and contains(@class, 'justify-center') and contains(@class, 'self-end')]");
    private By firstDropdownOption = By.xpath("//div[contains(text(), 'Select')]");
    private By secondDropdownOption = By.xpath("//div[contains(text(), 'Drag & Drop')]");
    private By thirdDropdownOption = By.xpath("//div[contains(text(), 'Actions, Alerts & Iframes')]");

    public UserCabinetPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverOverDropdown() {
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownTrigger));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();
    }

    public void selectFirstDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(firstDropdownOption));
        optionToSelect.click();
    }

    public void selectSecondDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(secondDropdownOption));
        optionToSelect.click();
    }

    public void selectThirdDropdownOption() {
        WebElement optionToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(thirdDropdownOption));
        optionToSelect.click();
    }

}
