package sblog.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.it.Allora;

public class Assertions extends AbstractStepLibrary {
	@Allora("^posso visitare la pagina dell'autore$")
	public void posso_visitare_la_pagina_dell_autore() {
		findByLinkText("Autore");
	}

	@Allora("^posso visitare la pagina dell'abstract$")
	public void posso_visitare_la_pagina_dell_abstract() {
		findByLinkText("Abstract");
	}

	@Allora("^la pagina è intitolata \"(.*?)\"$")
	public void la_pagina_è_intitolata(String pageTitle) throws Throwable {
		assertEquals(driver.getTitle(), pageTitle);
	}

	@Allora("^posso tornare alla pagina iniziale$")
	public void posso_tornare_alla_pagina_iniziale() throws Throwable {
		WebElement logo = driver.findElement(By.id("logo"));
		assertEquals(logo.getAttribute("href"), getSblogURL());
	}

	@Allora("^l'intestazione è posizionata all'inizio$")
	public void l_intestazione_è_posizionata_all_inizio() throws Throwable {
		String xpathExpression = "//body/*[1]";
		WebElement header = findByXPath(xpathExpression);
		assertEquals(page.header, header);
	}

	@Allora("^il piè di pagina è posizionato alla fine$")
	public void il_piè_di_pagina_è_posizionato_alla_fine() throws Throwable {
		String xpathExpression = "//body/div[last()]";
		WebElement footer = findByXPath(xpathExpression);
		assertEquals(page.footer, footer);
	}

	@Allora("^intestazione e pié di pagina hanno lo stesso colore di sfondo$")
	public void intestazione_e_pié_di_pagina_hanno_lo_stesso_colore_di_sfondo()
			throws Throwable {
		String headerBackgroundColor, footerBackgroundColor;
		headerBackgroundColor = page.header.getCssValue("background-color");
		footerBackgroundColor = page.footer.getCssValue("background-color");
		assertEquals(headerBackgroundColor, footerBackgroundColor);
	}

	@Allora("^posso navigare verso \"(.*?)\"$")
	public void posso_navigare_verso(String linkText) {
		page.header.findElements(By.linkText(linkText));
	}

	@Allora("^il titolo della pagina è uguale a \"(.*?)\"$")
	public void il_titolo_della_pagina_è_uguale_a(String pageTitle) {
		assertEquals(pageTitle, driver.getTitle());
	}

	@Allora("^lo sfondo del collegamento cambia$")
	public void lo_sfondo_del_collegamento_cambia() {
		WebElement bannerLink = page.getBannerLinks().get(0);
		String bannerLinkColor = bannerLink.getCssValue("background-color");
		assertEquals("rgba(91, 168, 42, 1)", bannerLinkColor);
	}

	@Allora("^ogni collegamento ha una descrizione testuale$")
	public void ogni_collegamento_ha_una_descrizione_testuale()
			throws Throwable {
		List<WebElement> linkedImages = driver.findElements(By
				.cssSelector("a img"));
		for (WebElement linkedImage : linkedImages) {
			String altDescription = linkedImage.getAttribute("alt");
			assertNotNull(altDescription);
			assertNotEquals("", altDescription.trim());
		}
	}

	@Allora("^il post \"(.*?)\" è stato creato con successo$")
	public void il_post_è_stato_creato_con_successo(String postTitle) {
		String noticeMessage = String.format(
				"Il post '%s' è stato creato con successo.", postTitle);
		checkNoticeMessage(noticeMessage);
	}

	@Allora("^il post \"(.*?)\" è leggibile su SBlog$")
	public void il_post_è_leggibile_su_SBlog(String postTitle) {
		assertPostExistsOnSBlog(postTitle);
	}

	@Allora("^il post \"(.*?)\" è stato cancellato con successo$")
	public void il_post_è_stato_cancellato_con_successo(String postTitle)
			throws Throwable {
		String noticeMessage = String.format(
				"Il post '%s' è stato cancellato con successo.", postTitle);
		checkNoticeMessage(noticeMessage);
	}

	@Allora("^compare l'errore \"(.*?)\"$")
	public void compare_l_errore(String errorMessage) {
		WebElement pErrorDescription = findById("error_description");
		assertEquals(errorMessage, pErrorDescription.getText());
	}

	@Allora("^tramite l'intestazione posso autenticarmi$")
	public void tramite_l_intestazione_posso_autenticarmi() {
		findHeader();
		page.header.findElement(By.id("log_in_link"));
	}

	@Allora("^l'utente è autenticato$")
	public void l_utente_è_autenticato() {
		findById("log_out_link");
	}

	@Allora("^tramite l'intestazione non posso autenticarmi$")
	public void tramite_l_intestazione_non_posso_autenticarmi() {
		findHeader();
		try {
			page.header.findElement(By.id("log_in_link"));
			fail();
		} catch (NoSuchElementException e) {
			//
		}
	}

	@Allora("^tramite l'intestazione posso disconnettermi$")
	public void tramite_l_intestazione_posso_disconnettermi() {
		findLogoutLink();
	}

	@Allora("^posso navigare verso la pagina per la creazione di un nuovo post$")
	public void posso_navigare_verso_la_pagina_per_la_creazione_di_un_nuovo_post() {
		driver.findElement(By
				.cssSelector("#new_post_action .post_action input"));
	}

	@Allora("^compare l'errore di autenticazione \"(.*?)\"$")
	public void compare_l_errore_di_autenticazione(String errorMessage) {
		WebElement errorMessageNotice = findById("flashnotice");
		assertEquals(errorMessage, errorMessageNotice.getText());
	}

	@Allora("^ogni post ha un titolo$")
	public void ogni_post_ha_un_titolo() {
		List<WebElement> posts = findPosts();
		for (WebElement post : posts) {
			post.findElement(By.className("post_title"));
		}
	}

	@Allora("^ogni post ha dei dettagli$")
	public void ogni_post_ha_dei_dettagli() {
		List<WebElement> posts = findPosts();
		for (WebElement post : posts) {
			List<WebElement> details = post.findElements(By
					.className("post_detail"));
			assertTrue(details.size() > 1);
		}
	}

	@Allora("^ogni post ha del contenuto$")
	public void ogni_post_ha_del_contenuto() throws Throwable {
		List<WebElement> posts = findPosts();
		for (WebElement post : posts) {
			post.findElement(By.className("post_content"));
		}
	}

	@Allora("^il contenuto del post \"(.*?)\" è un'anteprima dell'intero post$")
	public void il_contenuto_del_post_è_un_anteprima_dell_intero_post(
			String postTitle) throws Throwable {
		WebElement postDiv = findPostDivByTitle(postTitle);
		WebElement postContent = postDiv.findElement(By
				.className("post_content"));
		int postLength = postContent.getText().length();
		assertTrue(postLength < 520);
		postDiv.findElement(By.linkText("Leggi il resto"));
	}

	@Allora("^il contenuto del post \"(.*?)\" rappresenta l'intero post$")
	public void il_contenuto_del_post_rappresenta_l_intero_post(String postTitle)
			throws Throwable {
		WebElement postDiv = findPostDivByTitle(postTitle);
		WebElement postContent = postDiv.findElement(By
				.className("post_content"));
		int postLength = postContent.getText().length();
		assertTrue(postLength > 520);
	}

	@Allora("^il titolo del post è \"(.*?)\"$")
	public void il_titolo_del_post_è(String postTitle) {
		WebElement postDiv = driver.findElement(By.className("post"));
		WebElement postTitleParagraph = postDiv.findElement(By
				.className("post_title"));
		assertEquals(postTitle, postTitleParagraph.getText());
	}

	@Allora("^il contenuto del titolo include \"(.*?)\"$")
	public void il_contenuto_del_titolo_include(String partialPostContent) {
		WebElement postDiv = driver.findElement(By.className("post"));
		WebElement postTitleContent = postDiv.findElement(By
				.className("post_content"));
		assertTrue(postTitleContent.getText().contains(partialPostContent));
	}

	@Allora("^è presente il logo$")
	public void è_presente_il_logo() {
		new WebDriverWait(driver, 2).until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("img")));
		page.footer.findElement(By.id("woodstock"));
	}

	@Allora("^viene proposto il post \"(.*?)\"$")
	public void viene_proposto_il_post(String postTitle) throws Throwable {
		String xpathExpression = String.format("//li[@class = 'ui-menu-item']",
				postTitle);

		List<WebElement> lis = new WebDriverWait(driver, 4)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath(xpathExpression)));
		boolean isPostProposed = false;
		for (WebElement li : lis) {
			isPostProposed |= li.getText().equals(postTitle)
					&& li.isDisplayed();
		}
		assertTrue(isPostProposed);
	}

	@Allora("^non è proposto alcun post$")
	public void non_è_proposto_alcun_post() throws Throwable {
		try {
			WebElement li = new WebDriverWait(driver, 4)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.cssSelector(".ui-menu-item")));
			takeScreenshot();
			assertFalse(li.isDisplayed());
		} catch (TimeoutException e) {
		}
	}

	@Allora("^il post \"(.*?)\" è leggibile$")
	public void il_post_è_leggibile(String postTitle) throws Throwable {
		findPostDivByTitle(postTitle);
	}

	@Allora("^il post \"(.*?)\" non è leggibile$")
	public void il_post_non_è_leggibile(String postTitle) throws Throwable {
		try {
			findPostDivByTitle(postTitle);
			fail();
		} catch (NoSuchElementException e) {
			//
		}
	}
}
