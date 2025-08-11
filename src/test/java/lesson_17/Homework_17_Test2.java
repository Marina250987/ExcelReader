package lesson_17;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lesson_15.DriverSetUp;
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

@Epic("User Cabinet")
@Feature("User Login and Sort responsibilities")
public class Homework_17_Test2 {
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
    @Description("Test user login and sorting manual and automation responsibilities")
    @Story("The user should be able to drag and drop cards with responsibilities into the desired field")
    public void task2Test() throws InterruptedException {
        cabinetPage.hoverOverDropdown();
        cabinetPage.selectSecondDropdownOption();
        By newPageHeader2 = By.xpath("//h1[contains(text(), 'Sort your responsibilities')]");
        WebElement headerElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(newPageHeader2));
        Assert.assertEquals(headerElement2.getText(), "Sort your responsibilities", "The header text does not match expected.");

        WebElement draggableElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"manual1\"]")));
        WebElement targetElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target-manual1\"]")));
        Actions actions1 = new Actions(driver);
        actions1.clickAndHold(draggableElement1)
                .moveToElement(targetElement1)
                .release()
                .build()
                .perform();

        WebElement draggableElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"manual2\"]")));
        WebElement targetElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target-manual2\"]")));
        Actions actions2 = new Actions(driver);
        actions2.clickAndHold(draggableElement2)
                .moveToElement(targetElement2)
                .release()
                .build()
                .perform();

        WebElement draggableElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"auto1\"]")));
        WebElement targetElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target-auto1\"]")));
        Actions actions3 = new Actions(driver);
        actions3.clickAndHold(draggableElement3)
                .moveToElement(targetElement3)
                .release()
                .build()
                .perform();

        WebElement draggableElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"auto2\"]")));
        WebElement targetElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target-auto2\"]")));
        Actions actions4 = new Actions(driver);
        actions4.clickAndHold(draggableElement4)
                .moveToElement(targetElement4)
                .release()
                .build()
                .perform();

        By congratulationsMessage = By.xpath("//div[contains(@class, 'text-lg') and contains(., 'Congratulations! Let')]");
        WebElement congratulationsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(congratulationsMessage));
        Assert.assertEquals(congratulationsElement.getText(), "Congratulations! Let's test for the best!", "The congratulations message does not match expected.");
    }

    @AfterClass
    public void close() {
        DriverSetUp.quitDriver();
    }
}
