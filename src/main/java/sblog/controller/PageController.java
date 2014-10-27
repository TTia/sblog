package sblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sblog.service.AuthorService;
import sblog.service.PostService;

@Controller
public class PageController extends AbstractController {
	/***/
	@Autowired
	AuthorService authorService;
	@Autowired
	PostService postService;

	/***/

	@RequestMapping("/")
	public String welcome(
			@RequestParam(value = "title", defaultValue = "*") String title,
			Model model) {
		/***/
		authorService.defineTTia();
		/***/
		this.addDefaultAttributes(model);
		model.addAttribute("page_title", "SBlog");
		model.addAttribute("content_template", "/posts/index");
		model.addAttribute("shortener", "true");
		System.err.println(title);
		model.addAttribute("posts", "*".equals(title) ? postService.findAll()
				: postService.findPostsByTitle(title))	;

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/abstract")
	public String abstractPage(Model model) {
		model.addAttribute("page_title", "Abstract");
		model.addAttribute("content_template", "/pages/abstract");

		this.addDefaultAttributes(model);
		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/author")
	public String authorPage(Model model) {
		model.addAttribute("page_title", "Autore");
		model.addAttribute("content_template", "/pages/author");

		this.addDefaultAttributes(model);
		return super.defaultMapping(model);
	}

	private void addDefaultAttributes(Model model) {
		model.addAttribute("class_name", "pages");
	}
}
