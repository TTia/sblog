package sblog.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.it.Dato;

public class Contraints extends AbstractStepLibrary {
	@Dato("^apro RBlog$")
	public void apro_RBlog() {
		driver.navigate().to(getSblogURL());
		page = new Page();
	}

	@Dato("^è presente l'intestazione$")
	public void è_presente_l_intestazione() throws Throwable {
		page.setHeader(findById(getHeaderId()));
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
		List<WebElement> linkedImages = driver.findElements(By.cssSelector("a img"));
		assertNotNull(linkedImages);
		assertNotEquals(0, linkedImages.size());
	}

}
