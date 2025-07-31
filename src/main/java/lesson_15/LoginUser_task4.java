package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUser_task4 {

    public static void loginUser(WebDriver driver, WebDriverWait wait) {
        driver.get(Links.LOGIN_PAGE.getLink());

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("testovyiivan@mail.ru");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys("1234567R");

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        signInButton.click();

        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
        System.out.println("User logged in successfully!");
    }
}
