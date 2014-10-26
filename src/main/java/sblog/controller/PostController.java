package sblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sblog.orm.Post;
import sblog.service.AuthorService;
import sblog.service.PostService;

@Controller
@RequestMapping
public class PostController extends AbstractController {
	@Autowired
	PostService postService;
	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/posts")
	public String index(Model model) {
		this.addDefaultAttributes(model);
		model.addAttribute("page_title", "SBlog");
		model.addAttribute("content_template", "/posts/index");
		model.addAttribute("shortener", "true");
		model.addAttribute("posts", postService.findAll());

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Integer id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("page_title", "SBlog");
		model.addAttribute("content_template", "/posts/show");
		model.addAttribute("posts", new Post[] { post });

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/posts/new")
	public String newPost(@Valid Post post, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (post.getAuthor() == null) {
			post.setAuthor(authorService.getTTia());
		}
		this.defaultMapping(model);
		model.addAttribute("page_title", "SBlog");
		model.addAttribute("submit_text", "Scrivi un nuovo post");

		if (postService.findPostByTitle(post.getTitle()) != null) {
			bindingResult.addError(new FieldError("post", "title",
					"Il titolo è già presente."));
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("content_template", "/posts/new");
			return super.defaultMapping(model);
		}
		post = postService.createPost(post);
		redirectAttributes.addFlashAttribute("content_template", "/posts/show");
		redirectAttributes.addFlashAttribute("notice", String.format(
				"Il post '%s' è stato creato con successo.", post.getTitle()));
		redirectAttributes.addAttribute("id", post.getId());
		return "redirect:/posts/{id}";
	}

	@RequestMapping(value = "/posts/{id}/edit")
	public String editPost(@RequestParam Integer id, @Valid Post post,
			BindingResult bindingResult, Model model) {
		throw new RuntimeException();
	}

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
	public String destroy(@PathVariable Integer id,
			RedirectAttributes redirectAttributes) {
		String title = postService.deletePost(id);
		redirectAttributes.addFlashAttribute("notice", String.format(
				"Il post '%s' è stato cancellato con successo.", title));
		return "redirect:/";
	}

	@RequestMapping
	@ResponseBody
	public String[] autocompleteTitle(
			@RequestParam(value = "search", defaultValue = "*") String title) {
		return postService.queryByTitle(title);
	}

	private void addDefaultAttributes(Model model) {
		model.addAttribute("class_name", "posts");
	}

	/*
	 * @RequestMapping public String _update(Model model){ return null; }
	 * 
	 * @RequestMapping public String _destroy(Model model){ return null; }
	 */
}
