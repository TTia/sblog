package sblog.cucumber;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractStepLibrary {
	protected static WebDriver driver;
	protected static Page page;

	String getSblogURL() {
		return "http://localhost:8080/";
	}

	WebElement findById(String id) {
		return driver.findElement(By.id(id));
	}

	WebElement findByLinkText(String linkText) {
		return driver.findElement(By.linkText(linkText));
	}
	
	WebElement findByXPath(String xpathExpression){
		return driver.findElement(By.xpath(xpathExpression));
	}

	void takeScreenshot() {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		System.err.println(file.getAbsolutePath());
	}

	String getHeaderId() {
		return "header";
	}

	String getFooterId() {
		return "footer";
	}
	
	void createNewPageObject(){
		page = new Page();
	}
}
