package lesson_18;
import lesson_15.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class Homework_18Test_task2 {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void checkLogo_test1() {
        loginPage.open().checkLogo();
    }

    @Test
    public void checkRegistrationLink_test2() {
        loginPage.open().checkRegistrationLink();
    }
    @Test
    public void checkLoginPageElements_test3() {
        loginPage.open()
                .checkPageName("Sign In")
                .checkLogo()
                .checkEmailField()
                .checkPasswordField()
                .checkLoginButton()
                .checkRegistrationLink();
    }
    @Test
    public void checkEmptyFieldsValidation_test4() {
        loginPage.open()
                .checkEmptyFieldsValidation();
    }

    @Test
    public void testSuccessfulLogin_test5() {
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("1234567R")
                .clickLoginButton();
        UserCabinetPage.verifySuccessfulLogin(driver);
    }
    @Test
    public void chekInvalidPasswordLogin_test6() {
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("12345670")
                .clickLoginButton();

    }
    @Test
    public void chekInvalidEmail_Test7(){
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("1234567R")
                .clickLoginButton();
    }
    @Test
    public void checkLoginButtonActiveState_test8() {
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("1234567R")
                .checkLoginButtonActiveState();
    }

    @Test
    public void checkLoginButtonInactiveState_test9() {
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .enterPassword("0")
                .checkLoginButtonInactiveState();
    }
    @DataProvider(name = "invalidPasswordLengthProvider")
    public Object[][] invalidPasswordLengthProvider() {
        return new Object[][] {
                {"123456789012345678901"}, // 21 characters
                {"1234567"} // 7 characters
        };
    }

    @Test(dataProvider = "invalidPasswordLengthProvider")
    public void checkPasswordLengthValidationMessages_test10(String password) {
        loginPage.open()
                .enterEmail("testovyiivan@mail.ru")
                .checkPasswordLengthValidationMessages(password);
    }

    @AfterClass
    public void close() {
        driver.quit();
        }
    }


