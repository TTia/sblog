package sblog.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty"}, tags = {"@cap2", "~@ignore"})
public class RunCukesTest {
}
