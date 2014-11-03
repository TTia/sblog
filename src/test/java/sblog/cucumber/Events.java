package sblog.cucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.it.Quando;

public class Events extends AbstractStepLibrary{
	@Quando("^navigo verso \"(.*?)\"$")
	public void navigo_verso(String pageName) throws Throwable {
	    findByLinkText(pageName).click();
	    createNewPageObject();
	}
	
	@Quando("^il cursore si sposta sui collegamenti$")
	public void il_cursore_si_sposta_sui_collegamenti() {
		Actions action = new Actions(driver);
		WebElement bannerLink = page.getBannerLinks().get(0);
		action.moveToElement(bannerLink).build().perform();;
	}

}
