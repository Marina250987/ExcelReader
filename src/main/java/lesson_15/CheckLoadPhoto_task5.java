
package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckLoadPhoto_task5 {

    public static void uploadPhotoAndCheckSuccess(WebDriver driver, WebDriverWait wait) {
        try {
            wait.withTimeout(Duration.ofSeconds(20));

            wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/"));
            WebElement avatarIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("img[src='/static/media/upload_photo.5bec1675be4e2c769f92d6ebfe18b5b1.svg']")
            ));
            avatarIcon.click();

            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            fileInput.sendKeys("C:\\Users\\Roma\\Pictures\\Screenshots/photo.png");

            WebElement successModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("section.relative.flex.justify-center.items-center.px-16.py-16.text-2xl.font-light.leading-8.text-center.text-black.bg-white.max-w-[480px]")));

            WebElement successIcon = successModal.findElement(By.cssSelector("img[alt=''][class*='fill-yellow-400']"));

            WebElement successText = successModal.findElement(By.cssSelector("p.mt-12"));

            if (successIcon.isDisplayed() && successText.isDisplayed() && successText.getText().equals("Your photo has been updated")) {
                System.out.println("Photo uploaded successfully and success modal is displayed!");
            } else {
                System.out.println("Failed to upload photo or success modal is not displayed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}