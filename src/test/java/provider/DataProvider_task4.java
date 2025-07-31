package provider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import static org.testng.Assert.assertTrue;

public class DataProvider_task4 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        driver.manage().window().maximize();
    }

    @DataProvider(name = "testData")
    public Object[][] providedData() {
        return new Object[][]{
                {"testovyiivan@mail.ru", "1234567R"},
                {"mail2f@mail.ru", "123465690"},
                {"mail3qqq@gmail.com", "1234000u"}
        };
    }

    @Test(dataProvider = "testData")
    public void testLoginWithDataProvider(String email, String password) {
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));

        try {
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.equals("https://qa-course-01.andersenlab.com/"),
                    "Login failed for user: " + email + ". Current URL: " + currentUrl);
            System.out.println("Login successful for user: " + email);
        } catch (Exception e) {
            assertTrue(false, "Login failed for user: " + email + ". Error: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
