package lesson_18;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ActionsAlertsIframesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "iframe[title='Finish your registration']")
    private WebElement iframeElement;

    @FindBy(xpath = "//button[text()='Confirm']")
    private WebElement confirmElement;

    @FindBy(xpath = "//button[text()='Get Discount']")
    private WebElement discountElement;

    @FindBy(xpath = "//button[text()='Cancel course']")
    private WebElement cancelElement;

    @FindBy(xpath = "//*[contains(text(), 'Congratulations, you have successfully enrolled in the course!')]")
    private WebElement congratulationsElement;

    @FindBy(xpath = "//*[contains(text(), 'You received a 10% discount on the second course.')]")
    private WebElement discountElementMessage;

    public ActionsAlertsIframesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public ActionsAlertsIframesPage switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
        return this;
    }

    public ActionsAlertsIframesPage clickConfirm() {
        switchToFrame();
        confirmElement.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "You have called alert!");
        alert.accept();
        switchToDefaultContent();
        return this;
    }

    public String getCongratulationsText() {
        switchToDefaultContent();
        return congratulationsElement.getText();
    }

    public ActionsAlertsIframesPage doubleClickDiscount() {
        switchToFrame();
        WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(this.discountElement));
        Actions actions = new Actions(driver);
        actions.doubleClick(discountElement).perform();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Are you sure you want to apply the discount?");
        alert.accept();
        switchToDefaultContent();
        return this;
    }

    public String getDiscountMessageText() {
        switchToDefaultContent();
        return discountElementMessage.getText();
    }

    public ActionsAlertsIframesPage rightClickCancel() {
        switchToFrame();
        Actions actions = new Actions(driver);
        actions.contextClick(cancelElement).perform();
        return this;
    }

    public ActionsAlertsIframesPage handleCancelAlert(String inputText) {
        Alert cancelAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(cancelAlert.getText(), "Here you may describe a reason why you are cancelling your registration (or leave this field empty).");
        cancelAlert.sendKeys(inputText);
        cancelAlert.accept();
        switchToDefaultContent();
        return this;
    }

    public String getCancellationMessage(String inputText) {
        switchToDefaultContent();
        String expectedCancellationMessage = "Your course application has been cancelled. Reason: " + inputText;
        WebElement cancellationElementMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedCancellationMessage + "')]")));
        return cancellationElementMessage.getText();
    }

    public ActionsAlertsIframesPage switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }
}
