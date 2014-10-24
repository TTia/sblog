package sblog.controller;

import org.springframework.ui.Model;


public abstract class AbstractController {
	String postIndexView(){
		return "/posts/index";
	}
	
	void addCssLinksToPath(Model model){
		model.addAttribute("application_css", "/assets/stylesheets/application.css");
	}

}
