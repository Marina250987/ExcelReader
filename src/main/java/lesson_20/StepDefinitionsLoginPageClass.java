package lesson_20;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lesson_15.Links;
import lesson_18.LoginPage;
import lesson_18.UserCabinetPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class StepDefinitionsLoginPageClass {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;


    @Given("Set up driver")
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("Opening Login Page")
    public void opening_login_page() {
        loginPage.open();
    }

    @And("enter email {string}")
    public void enter_email(String email) {
        loginPage.enterEmail(email);
    }

    @And("enter password {string}")
    public void enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("click on login button")
    public void click_on_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("check successful login to the user account")
    public void check_successful_login_to_the_user_account() {
        UserCabinetPage.verifySuccessfulLogin(driver);
    }

    @Then("email required validation message is displayed")
    public void email_required_validation_message_is_displayed() {
        Assert.assertTrue(loginPage.getEmailRequiredValidationMessage().isDisplayed(),
                "Email required validation message is not displayed");
    }

    @Then("password required validation message is displayed")
    public void password_required_validation_message_is_displayed() {
        Assert.assertTrue(loginPage.getPasswordRequiredValidationMessage().isDisplayed(),
                "Password required validation message is not displayed");
    }

    @Then("login button is inactive")
    public void login_button_is_inactive() {
        Assert.assertFalse(loginPage.getLoginButton().isEnabled(),
                "Login button is active");
    }

    @Then("user stays on login page")
    public void user_stays_on_login_page() {
        Assert.assertEquals(driver.getCurrentUrl(), Links.LOGIN_PAGE.getLink(),
                "User is not on the login page");
    }
    @And("click on registration link")
    public void click_on_registration_link() {
        loginPage.checkRegistrationLink();
    }

    @Then("user is redirected to registration page")
    public void user_is_redirected_to_registration_page() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/registration",
                "User is not redirected to the registration page");
    }
    @Then("quit driver")
    public void quit_driver() {
        driver.quit();
    }

}
