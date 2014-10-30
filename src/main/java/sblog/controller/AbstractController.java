package sblog.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractController {
	String defaultMapping(Model model) {
		return "/layouts/application";
	}

	String authorizationNeeded(RedirectAttributes redirectAttributes) {
		redirectAttributes
				.addFlashAttribute("content_template", "/posts/index");
		redirectAttributes.addFlashAttribute("notice",
				"Effettua il login prima.");
		return "redirect:/login";
	}
}
