package sblog.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public abstract class AbstractController extends WebMvcConfigurerAdapter{
	String defaultMapping(Model model) {
		return "/layouts/application";
	}
}
