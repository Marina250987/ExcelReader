package lesson_18;

import lesson_15.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Arrays;

public class RefactoringHomework_17_Test1 {
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
    public void task1Test() {
        SelectPage selectPage = cabinetPage.hoverOverDropdown()
                .selectFirstDropdownOption();

        selectPage.selectCountry("USA")
                .selectLanguage("English")
                .selectType("Testing")
                .selectCourses(Arrays.asList("AQA Java", "AQA Python"))
                .setStartDate()
                .setEndDate();

        SearchResultsPage searchResultsPage = selectPage.clickSearch();

        Assert.assertEquals(searchResultsPage.getResultsHeaderText(),
                "Unfortunately, we did not find any courses matching your chosen criteria.",
                "The results text does not match expected.");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}

