package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static lesson_15.ElementComparator_task3.compareElements;

public class MainHomework15 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(Links.LOGIN_PAGE.getLink());
        Thread.sleep(2000);
        wait.until(ExpectedConditions.urlContains("login"));

        String originalWindow = driver.getWindowHandle();

        CheckRegistrationLink_task1.checkRegistrationLink(driver, wait);
        CheckLogo_task1.checkLogo(driver, wait);

        System.out.println("=============================TASK2==============================");
        OpenPagesAndSwitch_task2.openPagesAndSwitch(driver);

        driver.switchTo().window(originalWindow);

        System.out.println("=============================TASK3==============================");

        WebElement emailElement = driver.findElement(By.name("email"));
        WebElement passwordElement = driver.findElement(By.name("password"));
        compareElements(emailElement, passwordElement);

        System.out.println("============================TASK4===============================");
        LoginUser_task4.loginUser(driver, wait);

        System.out.println("============================TASK5===============================");
        CheckLoadPhoto_task5.uploadPhotoAndCheckSuccess(driver, wait);

        driver.quit();

    }
}

