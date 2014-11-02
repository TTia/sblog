package sblog.cucumber;

import cucumber.api.java.it.Quando;

public class Events extends AbstractStepLibrary{
	@Quando("^navigo verso \"(.*?)\"$")
	public void navigo_verso(String pageName) throws Throwable {
	    findByLinkText(pageName).click();
	}

}
