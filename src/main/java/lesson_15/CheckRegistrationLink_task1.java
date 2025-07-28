package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckRegistrationLink_task1 {

    public static void checkRegistrationLink(WebDriver driver, WebDriverWait wait) {
        System.out.println("=============================TASK1_part1=========================");
        WebElement registrationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Registration']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(registrationLink).perform();
        registrationLink.click();
        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/registration"));
        String currentUrl = driver.getCurrentUrl();

        if ("https://qa-course-01.andersenlab.com/registration".equals(currentUrl)) {
            System.out.println("Test passed! URL matches expected");
        } else {
            System.out.println("Test failed! URL does not match expected");
        }
    }
}
