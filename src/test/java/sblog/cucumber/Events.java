package sblog.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.it.Quando;

public class Events extends AbstractStepLibrary {
	@Quando("^navigo verso \"(.*?)\"$")
	public void navigo_verso(String pageName) throws Throwable {
		findByLinkText(pageName).click();
		createNewPageObject();
	}

	@Quando("^il cursore si sposta sui collegamenti$")
	public void il_cursore_si_sposta_sui_collegamenti() {
		Actions action = new Actions(driver);
		WebElement bannerLink = page.getBannerLinks().get(0);
		action.moveToElement(bannerLink).build().perform();
	}

	@Quando("^inserisco \"(.*?)\" come titolo$")
	public void inserisco_come_titolo(String title) {
		insertPostTitle(title);
	}

	@Quando("^inserisco del testo riempitivo come contenuto$")
	public void inserisco_del_testo_riempitivo_come_contenuto() {
		insertPostBody();
	}

	@Quando("^salvo il post$")
	public void salvo_il_post() {
		savePost();
	}

	@Quando("^cancello il post \"(.*?)\"$")
	public void cancello_il_post(String postTitle) {
		WebElement postDiv = findPostDivByTitle(postTitle);
		postDiv.findElement(By.className("remove_post_button")).click();
	}
	
	@Quando("^modifico il post \"(.*?)\"$")
	public void modifico_il_post(String postTitle) {
		WebElement postDiv = findPostDivByTitle(postTitle);
		postDiv.findElement(By.className("edit_post_button")).click();
	}

	@Quando("^inserisco \"(.*?)\" come contenuto$")
	public void inserisco_come_contenuto(String body) throws Throwable {
		WebElement bodyTextArea = driver.findElement(By.name("body"));
		bodyTextArea.clear();
		bodyTextArea.sendKeys(body);
	}

}
