package lesson_18;

import lesson_15.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;


public class RefactoringHomework_17_Test3 {
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
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("1234567R")
                .clickLoginButton();
    }

    @Test
    public void task3Test() throws InterruptedException {
        ActionsAlertsIframesPage actionsPage = cabinetPage.hoverOverDropdown()
                .selectThirdDropdownOption();

        actionsPage.clickConfirm();
        Assert.assertEquals(actionsPage.getCongratulationsText(), "Congratulations, you have successfully enrolled in the course!");
        actionsPage.doubleClickDiscount();

        Assert.assertEquals(actionsPage.getDiscountMessageText(), "You received a 10% discount on the second course.");

        actionsPage.rightClickCancel()
                .handleCancelAlert("Test");
        Assert.assertEquals(actionsPage.getCancellationMessage("Test"), "Your course application has been cancelled. Reason: Test");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

}
