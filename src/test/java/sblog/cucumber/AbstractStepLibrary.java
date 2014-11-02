package sblog.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public abstract class AbstractStepLibrary {
	protected static WebDriver driver;
	void setUpWebDriver(){
		driver = new PhantomJSDriver();
	}

}
