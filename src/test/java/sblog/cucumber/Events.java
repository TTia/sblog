package sblog.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.it.Quando;

public class Events extends AbstractStepLibrary {
	@Quando("^navigo verso \"(.*?)\"$")
	public void navigo_verso(String pageName) {
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

	@Quando("^quando mi disconnetto$")
	public void quando_mi_disconnetto() {
		findLogoutLink().click();
	}

	@Quando("^espando il post \"(.*?)\"$")
	public void espando_il_post(String postTitle) {
		WebElement postDiv = findPostDivByTitle(postTitle);
		postDiv.findElement(By.linkText(postTitle)).click();
	}

	@Quando("^clicco sull'area del pié di pagina$")
	public void clicco_sull_area_del_pié_di_pagina() throws Throwable {
		page.footer.click();
	}
	
	@Quando("^inserisco il testo \"(.*?)\" da ricercare$")
	public void inserisco_il_testo_da_ricercare(String query) throws Throwable {
		findSearchBarAndInsertText(query);
	}

	@Quando("^ricerco \"(.*?)\"$")
	public void ricerco(String query) throws Throwable {
		findSearchBarAndInsertText(query);
		page.searchBar.submit();
	}
}
