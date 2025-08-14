package lesson_18;

import lesson_15.Links;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;


    @FindBy(xpath = "//img[@alt='logo']")
    private WebElement logo;

    @FindBy(css = "h1.text-2xl")
    private WebElement pageName;

    @FindBy(xpath = "//span[text()='Registration']")
    private WebElement registrationLink;

    @FindBy(xpath = "//label[contains(@class, 'flex flex-col') and .//span[contains(text(), 'Email')]]//following-sibling::div//span[contains(@class, 'text-rose-500')]")
    private WebElement emailRequiredValidationMessage;

    @FindBy(xpath = "//label[contains(@class, 'flex flex-col') and .//span[contains(text(), 'Password')]]//following-sibling::div//span[contains(@class, 'text-rose-500')]")
    private WebElement passwordRequiredValidationMessage;

    @FindBy(xpath = "//label[contains(., 'Email')]/following-sibling::div/span[contains(text(), 'Email or password is not valid')]")
    private WebElement emailValidationMessage;

    @FindBy(xpath = "//label[contains(., 'Password')]/following-sibling::div/span[contains(text(), 'Email or password is not valid')]")
    private WebElement passwordValidationMessage;

    @FindBy(xpath = "//span[contains(@class, 'text-rose-500') and contains(text(), 'Maximum 20 characters')]")
    private WebElement maxLengthValidationMessage;

    @FindBy(xpath = "//span[contains(@class, 'text-rose-500') and contains(text(), 'Minimum 8 characters')]")
    private WebElement minLengthValidationMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.get(Links.LOGIN_PAGE.getLink());
        return this;
    }

    public LoginPage enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOf(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

        public LoginPage checkLogo() {
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOf(logo));
        String logoAlt = logoElement.getAttribute("alt");
        Assert.assertEquals(logoAlt, "logo", "Logo alt text is incorrect");
        Dimension logoSize = logoElement.getSize();
        Dimension expectedSize = new Dimension(140, 43);
        Assert.assertEquals(logoSize, expectedSize, "Logo size is incorrect");
        return this;
    }
    public LoginPage checkRegistrationLink() {
        WebElement registrationLinkElement = wait.until(ExpectedConditions.visibilityOf(registrationLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(registrationLinkElement).perform();
        registrationLinkElement.click();
        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/registration"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/registration", "URL does not match expected");
        return this;
    }
    public LoginPage checkPageName(String expectedPageName) {
        String pageNameText = wait.until(ExpectedConditions.visibilityOf(pageName)).getText();
        Assert.assertEquals(pageNameText, expectedPageName, "Page name is incorrect");
        return this;
    }
    public LoginPage checkEmailField() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(emailField)).isDisplayed(), "Email field is not displayed");
        return this;
    }

    public LoginPage checkPasswordField() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed(), "Password field is not displayed");
        return this;
    }

    public LoginPage checkLoginButton() {
        Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(loginButton)).isDisplayed(), "Sign in button is not displayed");
        return this;
    }
    public LoginPage checkEmptyFieldsValidation() {
        loginButton.click();
        Assert.assertTrue(emailRequiredValidationMessage.isDisplayed(), "Validation message for Email field is not displayed");
        Assert.assertTrue(passwordRequiredValidationMessage.isDisplayed(), "Validation message for Password field is not displayed");
        Assert.assertFalse(loginButton.isEnabled(), "Sign in button is active");
        Assert.assertEquals(driver.getCurrentUrl(), Links.LOGIN_PAGE.getLink(), "User is logged in");
        return this;
    }
    public LoginPage checkInvalidPasswordLogin(String email, String invalidPassword) {
        Assert.assertEquals(driver.getCurrentUrl(), Links.LOGIN_PAGE.getLink(), "User is logged in with invalid password");
        Assert.assertTrue(passwordValidationMessage.isDisplayed(), "Validation message for Password field is not displayed");
        return this;
    }
    public LoginPage checkInvalidEmailLogin(String invalidEmail, String Password) {
        Assert.assertEquals(driver.getCurrentUrl(), Links.LOGIN_PAGE.getLink(), "User is logged in with invalid password");
        Assert.assertTrue(emailValidationMessage.isDisplayed(), "Validation message for Password field is not displayed");
        return this;
    }


    public LoginPage checkLoginButtonActiveState() {
        Actions actions = new Actions(driver);
        actions.moveToElement(loginButton).perform();
        String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');";
        String backgroundColor = (String) ((JavascriptExecutor) driver).executeScript(script, loginButton);
        Assert.assertEquals(backgroundColor, "rgb(254, 218, 0)", "Sign in button is not highlighted in bright yellow on hover");
        Assert.assertTrue(loginButton.isEnabled(), "Sign in button is not clickable");
        return this;
    }
    public LoginPage checkLoginButtonInactiveState() {
        Assert.assertFalse(loginButton.isEnabled(), "Sign in button is active");
        String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('opacity');";
        String opacity = (String) ((JavascriptExecutor) driver).executeScript(script, loginButton);
        Assert.assertEquals(opacity, "0.6", "Sign in button does not have the correct inactive style");
        return this;
    }
    public LoginPage checkPasswordLengthValidationMessages(String password) {
        enterPassword(password);

        if (password.length() > 20) {
            Assert.assertTrue(maxLengthValidationMessage.isDisplayed(), "Validation message for max length is not displayed");
        } else if (password.length() < 8) {
            Assert.assertTrue(minLengthValidationMessage.isDisplayed(), "Validation message for min length is not displayed");
        }
        return this;
    }
    public WebElement getEmailRequiredValidationMessage() {
        return emailRequiredValidationMessage;
    }

    public WebElement getPasswordRequiredValidationMessage() {
        return passwordRequiredValidationMessage;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}
