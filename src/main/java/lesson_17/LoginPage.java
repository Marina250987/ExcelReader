package lesson_17;
import lesson_15.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password) {
        driver.get(Links.LOGIN_PAGE.getLink());
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        WebElement passwordInput = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
    }
}
