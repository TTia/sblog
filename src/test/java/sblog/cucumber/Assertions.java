package sblog.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.PendingException;
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
		String xpathExpression = "descendant::body/*[1]";
		WebElement header = findByXPath(xpathExpression);
		assertEquals(page.header, header);
	}

	@Allora("^il piè di pagina è posizionato alla fine$")
	public void il_piè_di_pagina_è_posizionato_alla_fine() throws Throwable {
		String xpathExpression = "descendant::body/div[last()]";
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
		assertNotEquals("rgba(0, 0, 0, 0)", bannerLinkColor);
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
	public void il_post_è_stato_cancellato_con_successo(String postTitle) throws Throwable {
		String noticeMessage = String.format(
				"Il post '%s' è stato cancellato con successo.", postTitle);
		checkNoticeMessage(noticeMessage);
	}
	
	@Allora("^compare l'errore \"(.*?)\"$")
	public void compare_l_errore(String errorMessage) {
	    WebElement pErrorDescription = findById("error_description");
	    assertEquals(errorMessage, pErrorDescription.getText());
	}
}
