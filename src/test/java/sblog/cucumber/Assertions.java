package sblog.cucumber;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.it.Allora;

public class Assertions extends AbstractStepLibrary{
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
}
