package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckLogo_task1 {

    public static void checkLogo(WebDriver driver, WebDriverWait wait) {
        System.out.println("============================TASK1_part2============================");
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='logo']")));
        String logoAlt = logo.getAttribute("alt");
        if ("logo".equals(logoAlt)) {
            System.out.println("Logo alt text is correct");
        } else {
            System.out.println("Logo alt text is incorrect");
        }

        Dimension logoSize = logo.getSize();
        Dimension expectedSize = new Dimension(140, 43);
        if (logoSize.equals(expectedSize)) {
            System.out.println("Logo size is correct");
        } else {
            System.out.println("Logo size is incorrect");
        }
    }
}

