package sblog.cucumber;

import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends AbstractStepLibrary{
	@Before
	public void setUpWebDriver(){
		driver = new PhantomJSDriver();
		assertNotNull(driver);
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(2048,2048));
	}
	
	@After
	public void releaseWebDriver(){
		driver.close();
	}
}
