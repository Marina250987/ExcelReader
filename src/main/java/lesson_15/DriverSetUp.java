package lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetUp {
    private static WebDriver driver;

    private static WebDriver setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    private static WebDriver getInstance(){
        if(driver==null) {
            driver = setUpDriver();
        }
        return driver;
    }
    public static WebDriver getDriver(){
        driver=getInstance();
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}