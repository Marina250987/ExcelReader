package lesson_17;

import lesson_15.DriverSetUp;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Homework_17_Test3 {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private UserCabinetPage cabinetPage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        cabinetPage = new UserCabinetPage(driver);

        try {
            loginPage.login("testovyiivan@mail.ru", "1234567R");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'flex') and contains(@class, 'gap-1.5') and contains(@class, 'justify-center') and contains(@class, 'self-end')]")));
        } catch (Exception e) {
            System.err.println("An error occurred during setup: " + e.getMessage());
            Assert.fail("Failed to login or navigate to user cabinet page: " + e.getMessage());
        }
    }

    @Test
    public void task3Test() {
        cabinetPage.hoverOverDropdown();
        cabinetPage.selectThirdDropdownOption();
        By newPageHeader3 = By.xpath("//h1[contains(text(), 'Your application has been accepted!')]");
        WebElement headerElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(newPageHeader3));
        Assert.assertEquals(headerElement3.getText(), "Your application has been accepted!", "The header text does not match expected.");

        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[title='Finish your registration']")));
        driver.switchTo().frame(iframeElement);

        WebElement confirmElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm']")));
        confirmElement.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "You have called alert!", "Alert text does not match expected.");
        alert.accept();
        By congratulationsMessage = By.xpath("//span[contains(text(), 'Congratulations, you have successfully enrolled in the course!')]");
        WebElement congratulationsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(congratulationsMessage));
        Assert.assertEquals(congratulationsElement.getText(), "Congratulations, you have successfully enrolled in the course!", "The congratulations message does not match expected.");

        WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Get Discount']")));
        Actions actions = new Actions(driver);
        actions.doubleClick(discountElement).perform();
        Alert discountAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(discountAlert.getText(), "Are you sure you want to apply the discount?", "Discount alert text does not match expected.");
        discountAlert.accept();
        By discountMessage = By.xpath("//span[contains(text(), 'You received a 10% discount on the second course.')]");
        WebElement discountElementMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(discountMessage));
        Assert.assertEquals(discountElementMessage.getText(), "You received a 10% discount on the second course.", "The discount message does not match expected.");

        WebElement cancelElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel course']")));
        Actions actionsForRightClick = new Actions(driver);
        actionsForRightClick.contextClick(cancelElement).perform();
        String inputText = "Test";
        Alert cancelAlert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(cancelAlert.getText(), "Here you may describe a reason why you are cancelling your registration (or leave this field empty).", "Cancel alert text does not match expected.");
        cancelAlert.sendKeys(inputText);
        cancelAlert.accept();
        String expectedCancellationMessage = "Your course application has been cancelled. Reason: " + inputText;
        By cancellationMessageLocator = By.xpath("//span[contains(text(), '" + expectedCancellationMessage + "')]");
        WebElement cancellationElementMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(cancellationMessageLocator));
        Assert.assertEquals(cancellationElementMessage.getText(), expectedCancellationMessage, "The cancellation message does not match expected.");

        driver.switchTo().defaultContent();

    }

    @AfterClass
    public void close() {
        DriverSetUp.quitDriver();
    }
}
