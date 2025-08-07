package lesson_18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class SelectPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h1[contains(text(), 'Choose your course')]")
    private WebElement pageHeader;

    @FindBy(css = "select[data-lol='SelectCountry']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//select[@id='SelectLanguage']")
    private WebElement languageDropdown;

    @FindBy(xpath = "//select[@title='Select type']")
    private WebElement typeDropdown;

    @FindBy(id = "MultipleSelect")
    private WebElement coursesDropdown;

    @FindBy(css = "input[data-calendar='1']")
    private WebElement startDateInput;

    @FindBy(css = "input[data-calendar='2']")
    private WebElement endDateInput;

    @FindBy(css = "button[name='SelectPageSearchButton']")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[contains(text(), \"Unfortunately, we did not find any courses matching your chosen criteria.\")]")
    private WebElement resultsHeader;

    public SelectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public SelectPage selectCountry(String country) {
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);
        return this;
    }

    public SelectPage selectLanguage(String language) {
        Select languageSelect = new Select(languageDropdown);
        languageSelect.selectByVisibleText(language);
        return this;
    }

    public SelectPage selectType(String type) {
        Select typeSelect = new Select(typeDropdown);
        typeSelect.selectByVisibleText(type);
        return this;
    }

    public SelectPage selectCourses(List<String> courses) {
        Select coursesSelect = new Select(coursesDropdown);
        for (String course : courses) {
            coursesSelect.selectByVisibleText(course);
        }
        return this;
    }

    public SelectPage setStartDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(TemporalAdjusters.next(java.time.DayOfWeek.MONDAY));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        startDateInput.clear();
        startDateInput.sendKeys(nextMonday.format(formatter));
        return this;
    }

    public SelectPage setEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(TemporalAdjusters.next(java.time.DayOfWeek.MONDAY));
        LocalDate endDate = nextMonday.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        endDateInput.clear();
        endDateInput.sendKeys(endDate.format(formatter));
        return this;
    }

    public SearchResultsPage clickSearch() {
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public String getResultsHeaderText() {
        return resultsHeader.getText();
    }
}
