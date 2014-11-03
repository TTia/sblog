package sblog.cucumber;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.it.Dato;

public class Contraints extends AbstractStepLibrary {
	// @Rule
	// public ExpectedException exception = ExpectedException.none();

	@Dato("^apro SBlog$")
	public void apro_SBlog() {
		visitHomePage();
	}

	@Dato("^è presente l'intestazione$")
	public void è_presente_l_intestazione() throws Throwable {
		findHeader();
	}

	@Dato("^è presente il pié di pagina$")
	public void è_presente_il_pié_di_pagina() throws Throwable {
		page.setFooter(findById(getFooterId()));
	}

	@Dato("^l'intestazione ha un colore di sfondo$")
	public void l_intestazione_ha_un_colore_di_sfondo() throws Throwable {
		String rgb = page.header.getCssValue("background-color");
		assertNotNull(rgb);
		assertNotEquals("rgba(0, 0, 0, 1)", rgb);
	}

	@Dato("^il pié di pagina ha un colore di sfondo$")
	public void il_pié_di_pagina_ha_un_colore_di_sfondo() throws Throwable {
		String rgb = page.footer.getCssValue("background-color");
		assertNotNull(rgb);
		assertNotEquals("rgba(0, 0, 0, 1)", rgb);
	}

	@Dato("^l'intestazione permette la navigazione$")
	public void l_intestazione_permette_la_navigazione() throws Throwable {
		List<WebElement> links = page.header.findElements(By.cssSelector("a"));
		assertNotNull(links);
		assertEquals(5, links.size());
	}

	@Dato("^la pagina ha un titolo$")
	public void la_pagina_ha_un_titolo() {
		assertNotNull(driver.getTitle());
		assertNotEquals("", driver.getTitle().trim());
	}

	@Dato("^i collegamenti non hanno sfondo$")
	public void i_collegamenti_non_hanno_sfondo() {
		List<WebElement> bannerLinks = page.header.findElements(By
				.className("banner_link"));
		for (WebElement banner_link : bannerLinks) {
			String rgb = banner_link.getCssValue("background-color");
			assertNotNull(rgb);
			assertEquals("rgba(0, 0, 0, 0)", rgb);
		}
		page.setBannerLinks(bannerLinks);
	}

	@Dato("^sono presenti dei collegamenti raffigurati tramite immagini$")
	public void sono_presenti_dei_collegamenti_raffigurati_tramite_immagini() {
		List<WebElement> linkedImages = driver.findElements(By
				.cssSelector("a img"));
		assertNotNull(linkedImages);
		assertNotEquals(0, linkedImages.size());
	}

	@Dato("^mi autentico come \"(.*?)\"$")
	public void mi_autentico_come(String email) {
		WebElement loginLink = findById("log_in_link");
		loginLink.click();

		WebElement emailInputElement = driver.findElement(By.name("email"));
		WebElement passwordInputElement = driver.findElement(By
				.name("password"));

		emailInputElement.sendKeys(email);
		passwordInputElement.sendKeys("password");

		emailInputElement.submit();		
	}

	@Dato("^il post \"(.*?)\" non è leggibile su SBlog$")
	public void il_post_non_è_leggibile_su_SBlog(String postTitle) {
		try {
			assertPostExistsOnSBlog(postTitle);
			fail();
		} catch (NoSuchElementException e) {
			//
		}
	}

	@Dato("^apro la pagina per la creazione di un nuovo post$")
	public void apro_la_pagina_per_la_creazione_di_un_nuovo_post() {
		openNewPostPage();
	}

	@Dato("^il post \"(.*?)\" esiste$")
	public void il_post_esiste(String postTitle) throws Throwable {
		openNewPostPage();
		insertPostTitle(postTitle);
		insertPostBody();
		savePost();
		String noticeMessage = String.format(
				"Il post '%s' è stato creato con successo.", postTitle);
		checkNoticeMessage(noticeMessage);
		assertPostExistsOnSBlog(postTitle);
	}

	@Dato("^l'utente non è autenticato$")
	public void l_utente_non_è_autenticato() {
		findById("log_in_link");
		try {
			findById("log_out_link");
			fail();
		} catch (NoSuchElementException e) {
			//
		}
	}
}
