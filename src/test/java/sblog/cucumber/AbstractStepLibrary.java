package sblog.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractStepLibrary {
	protected static WebDriver driver;

	String getSblogURL() {
		return "http://localhost:8080/";
	}
	
	WebElement findById(String id){
		return driver.findElement(By.id(id));
	}
	
	WebElement findByLinkText(String linkText){
		return driver.findElement(By.linkText(linkText));
	}
	
	void takeScreenshot(){
		((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}

}
