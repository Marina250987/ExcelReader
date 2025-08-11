package lesson_17;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lesson_15.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


@Epic("User Cabinet")
@Feature("User Login and Course Selection")
public class Homework_17_Test1 {
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
    @Description("Test user login and course selection functionality")
    @Story("User should be able to select courses based on criteria")
    public void task1Test() {
        cabinetPage.hoverOverDropdown();
        cabinetPage.selectFirstDropdownOption();
        By newPageHeader = By.xpath("//h1[contains(text(), 'Choose your course')]");
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(newPageHeader));
        Assert.assertEquals(headerElement.getText(), "Choose your course", "The header text does not match expected.");

        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[data-lol='SelectCountry']")));
        countryDropdown.click();
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("USA");

        WebElement languageDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='SelectLanguage']")));
        languageDropdown.click();
        Select languageSelect = new Select(languageDropdown);
        languageSelect.selectByVisibleText("English");

        WebElement typeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@title='Select type']")));
        typeDropdown.click();
        Select typeSelect = new Select(typeDropdown);
        typeSelect.selectByVisibleText("Testing");

        WebElement coursesDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MultipleSelect")));
        Select coursesSelect = new Select(coursesDropdown);
        coursesSelect.selectByVisibleText("AQA Java");
        coursesSelect.selectByVisibleText("AQA Python");

        WebElement selectedCountryOption = countrySelect.getFirstSelectedOption();
        Assert.assertEquals(selectedCountryOption.getText(), "USA", "The selected country does not match expected.");

        WebElement selectedLanguageOption = languageSelect.getFirstSelectedOption();
        Assert.assertEquals(selectedLanguageOption.getText(), "English", "The selected language does not match expected.");

        WebElement selectedTypeOption = typeSelect.getFirstSelectedOption();
        Assert.assertEquals(selectedTypeOption.getText(), "Testing", "The selected type does not match expected.");

        List<WebElement> selectedOptions = coursesSelect.getAllSelectedOptions();
        Assert.assertEquals(selectedOptions.size(), 2, "The number of selected courses does not match expected.");


        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate endDate = nextMonday.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        WebElement startDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-calendar='1']")));
        startDateInput.click();
        startDateInput.clear();
        startDateInput.sendKeys(nextMonday.format(formatter));

        WebElement endDateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-calendar='2']")));
        endDateInput.click();
        endDateInput.clear();
        endDateInput.sendKeys(endDate.format(formatter));

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='SelectPageSearchButton']")));
        searchButton.click();

        By resultsHeader = By.xpath("//h2[contains(text(), \"Unfortunately, we did not find any courses matching your chosen criteria.\")]");
        WebElement resultsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsHeader));
        Assert.assertEquals(resultsElement.getText(), "Unfortunately, we did not find any courses matching your chosen criteria.", "The results text does not match expected.");
    }

    @AfterClass
    public void close() {
        DriverSetUp.quitDriver();
    }
}
