package sblog.cucumber;

import static org.junit.Assert.*;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.it.Dato;

public class Contraints extends AbstractStepLibrary{
	@Dato("^apro RBlog$")
	public void apro_RBlog() {
		driver.navigate().to(getSblogURL());
	}
	
	@Before
	public void setUpWebDriver(){
		driver = new PhantomJSDriver();
		assertNotNull(driver);
	}
	
	@After
	public void releaseWebDriver(){
		driver.close();
	}

}
