package lesson_18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DragAndDropPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h1[contains(text(), 'Sort your responsibilities')]")
    private WebElement pageHeader;

    @FindBy(xpath = "//*[@id=\"manual1\"]")
    private WebElement draggableElement1;

    @FindBy(xpath = "//*[@id=\"target-manual1\"]")
    private WebElement targetElement1;

    @FindBy(xpath = "//*[@id=\"manual2\"]")
    private WebElement draggableElement2;

    @FindBy(xpath = "//*[@id=\"target-manual2\"]")
    private WebElement targetElement2;

    @FindBy(xpath = "//*[@id=\"auto1\"]")
    private WebElement draggableElement3;

    @FindBy(xpath = "//*[@id=\"target-auto1\"]")
    private WebElement targetElement3;

    @FindBy(xpath = "//*[@id=\"auto2\"]")
    private WebElement draggableElement4;

    @FindBy(xpath = "//*[@id=\"target-auto2\"]")
    private WebElement targetElement4;

    @FindBy(xpath = "//div[contains(@class, 'text-lg') and contains(., 'Congratulations! Let')]")
    private WebElement congratulationsMessage;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public DragAndDropPage dragElement1() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(draggableElement1)
                .moveToElement(targetElement1)
                .release()
                .build()
                .perform();
        return this;
    }

    public DragAndDropPage dragElement2() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(draggableElement2)
                .moveToElement(targetElement2)
                .release()
                .build()
                .perform();
        return this;
    }

    public DragAndDropPage dragElement3() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(draggableElement3)
                .moveToElement(targetElement3)
                .release()
                .build()
                .perform();
        return this;
    }

    public DragAndDropPage dragElement4() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(draggableElement4)
                .moveToElement(targetElement4)
                .release()
                .build()
                .perform();
        return this;
    }

    public String getCongratulationsMessage() {
        return congratulationsMessage.getText();
    }
}
