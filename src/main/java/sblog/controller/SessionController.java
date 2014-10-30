package sblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sblog.service.SessionService;

@Controller
public class SessionController extends AbstractController {
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String login(
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			Model model, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		if(sessionService.isLogged(httpSession)){
			redirectAttributes.addFlashAttribute("content_template",
					"/posts/index");
			redirectAttributes.addFlashAttribute("notice",
					"Hai già effettuato l'accesso.");
			return "redirect:/";			
		}
		if (password != null && password != null) {
			if (sessionService.accessGranted(email, password, httpSession)) {
				redirectAttributes.addFlashAttribute("content_template",
						"/posts/index");
				redirectAttributes.addFlashAttribute("notice",
						"Login effettuato, benvenuto!");
				return "redirect:/";
			} else {
				model.addAttribute("notice", "Credenziali invalide.");
			}
		}
		model.addAttribute("class_name", "sessions");
		model.addAttribute("page_title", "Login");
		model.addAttribute("content_template", "/sessions/login");

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String destroy(Model model, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		if (sessionService.isLogged(httpSession)) {
			redirectAttributes.addFlashAttribute("content_template",
					"/posts/index");
			redirectAttributes.addFlashAttribute("notice", "Arrivederci!");
			httpSession.invalidate();
		}
		return "redirect:/";
	}
}
