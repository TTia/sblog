package sblog.cucumber;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		try {
			FileUtils.copyFile(file, new File("/tmp/screenshot.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
	
	String loremIpsumGT500(){
		return new StringBuilder().append("Lorem ipsum dolor sit amet,")
		.append("consectetuer adipiscing elit. Aenean commodo ligula eget dolor.")
		.append("Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,")
		.append("nascetur ridiculus mus. Donec quam felis, ultricies nec,")
		.append("pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.")
		.append("Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.")
		.append("In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo.")
		.append("Nullam dictum felis eu pede mollis pretium. Integer tincidunt.")
		.append("Cras dapibus. Vivamus elementum semper nisi.")
		.append("Aenean vulputate eleifend tellus.")
		.append("Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim.")
		.toString();		
	}

	protected void visitHomePage() {
		driver.navigate().to(getSblogURL());
		page = new Page();
	}

	protected WebElement findPostDivByTitle(String postTitle) {
		String xpathExpression = "//div[@class = 'post'][p/a[text() = '%s']]";
		xpathExpression = String.format(xpathExpression, postTitle);
		return findByXPath(xpathExpression);
	}

	protected void openNewPostPage() {
		takeScreenshot();
		WebElement newPostButton = driver.findElement(By
				.cssSelector("#new_post_action .post_action input"));
		newPostButton.click();
	}

	protected void insertPostTitle(String title) {
		WebElement titleInput = driver.findElement(By.id("post_title"));
		titleInput.clear();
		titleInput.sendKeys(title);
	}

	protected void insertPostBody() {
		WebElement bodyTextArea = driver.findElement(By.name("body"));
		bodyTextArea.clear();
		bodyTextArea.sendKeys(loremIpsumGT500());
	}

	protected void savePost() {
		WebElement titleInput = driver.findElement(By.id("submit"));
		titleInput.click();
	}

	protected void checkNoticeMessage(String noticeMessage) {
		WebElement noticeMessageElement = driver.findElement(By
				.cssSelector("#notice p"));
		assertEquals(noticeMessage, noticeMessageElement.getText());
	}

	protected void assertPostExistsOnSBlog(String postTitle) {
		visitHomePage();
		findPostDivByTitle(postTitle);
	}

	protected void findHeader() {
		page.setHeader(findById(getHeaderId()));
	}
	
	protected void findFooter() {
		page.setFooter(findById(getFooterId()));
	}

	protected WebElement findLogoutLink() {
		findHeader();
		return page.header.findElement(By.id("log_out_link"));
	}

	protected List<WebElement> findPosts() {
		return driver.findElements(By.className(".post"));
	}

	protected void findSearchBarAndInsertText(String query) {
		page.setSearchBar(findById("search_input_text"));
		page.getSearchBar().clear();
	    page.getSearchBar().sendKeys(query);
	}
}
