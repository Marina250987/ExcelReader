package lesson_20;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/feature/LoginPage.feature"},
        glue = {"lesson_20"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}