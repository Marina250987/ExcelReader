package parameterization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class Parameterization_task5 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"email1", "password1"})
    public void testLoginWithParameters1(String email, String password) {
        performLoginTest(email, password);
    }

    @Test
    @Parameters({"email2", "password2"})
    public void testLoginWithParameters2(String email, String password) {
        performLoginTest(email, password);
    }

    @Test
    @Parameters({"email3", "password3"})
    public void testLoginWithParameters3(String email, String password) {
        performLoginTest(email, password);
    }

    private void performLoginTest(String email, String password) {
        driver.get("https://qa-course-01.andersenlab.com/login");

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
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
