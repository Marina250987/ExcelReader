package lesson_18;

import lesson_15.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RefactoringHomework_17_Test2 {
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
    public void task2Test() {
        DragAndDropPage dragAndDropPage = cabinetPage.hoverOverDropdown()
                .selectSecondDropdownOption();

        dragAndDropPage.dragElement1()
                .dragElement2()
                .dragElement3()
                .dragElement4();

        Assert.assertEquals(dragAndDropPage.getCongratulationsMessage(),
                "Congratulations! Let's test for the best!",
                "The congratulations message does not match expected.");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
