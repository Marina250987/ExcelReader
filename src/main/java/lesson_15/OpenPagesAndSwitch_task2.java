package lesson_15;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class OpenPagesAndSwitch_task2 {

    public static void openPagesAndSwitch(WebDriver driver) throws InterruptedException {
        List<String> urls = new ArrayList<>();
        urls.add("http://www.automationpractice.pl/index.php");
        urls.add("https://zoo.waw.pl/");
        urls.add("https://www.w3schools.com/");
        urls.add("https://www.clickspeedtester.com/click-counter/");
        urls.add("https://andersenlab.com/");

        for (String url : urls) {
            ((ChromeDriver) driver).executeScript("window.open('" + url + "', '_blank');");
        }

        Thread.sleep(6000);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            Thread.sleep(2000);
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            if (!title.isEmpty() && !url.isEmpty() && !url.equals("data:,")) {
                System.out.println("Page title: " + title);
                System.out.println("Page URL: " + url);
            }
        }

        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains("Zoo")) {
                driver.close();
                break;
            }
        }
    }
}
