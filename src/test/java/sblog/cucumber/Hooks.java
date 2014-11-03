package sblog.cucumber;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends AbstractStepLibrary {
	@Before
	public void setUpWebDriver() {
		driver = new PhantomJSDriver();
		assertNotNull(driver);
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(2048, 2048));
	}

	@After(order = 0)
	public void releaseWebDriver() {
		driver.close();
	}

	@After(value = "@clear")
	public void clearIpsums() {
		driver.navigate().to(getSblogURL());
		String loremIpsumPostTitle = "Lorem Ipsum";
		try {
			String xpathExpression = String.format(
					"//div[@class = 'post'][p/a[contains(text(),'%s')]]",
					loremIpsumPostTitle);
			List<WebElement> postDivs = driver.findElements(By
					.xpath(xpathExpression));
			for (WebElement postDiv : postDivs) {
				postDiv.findElement(By.className("remove_post_button")).click();
			}
		} catch (NoSuchElementException e) {
			//
		}
	}
}
