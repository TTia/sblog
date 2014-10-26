package sblog.controller;

import org.springframework.ui.Model;

public abstract class AbstractController{
	String defaultMapping(Model model) {
		return "/layouts/application";
	}
}
