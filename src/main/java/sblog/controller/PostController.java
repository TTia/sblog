package sblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sblog.orm.Author;
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
		model.addAttribute("notice", "!!!");
		model.addAttribute("posts", postService.queryForAll());

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/posts/{id}")
	public String show(@RequestParam(required = true, value = "id") Integer id,
			Model model) {
		Post post = postService.getPost(id);
		this.defaultMapping(model);
		model.addAttribute("page_title", post.getTitle());
		model.addAttribute("content_template", "/posts/show");

		return super.defaultMapping(model);
	}

	@RequestMapping(value = "/posts/new")
	public String newPost(@Valid Post post, BindingResult bindingResult,
			Model model) {
		this.defaultMapping(model);
		if(post.getAuthor() == null){
			post.setAuthor(authorService.getTTia());
		}
		model.addAttribute("page_title", "SBlog");
		model.addAttribute("form_class", "new_post");
		model.addAttribute("form_id", "new_post");
		model.addAttribute("submit_text", "Scrivi un nuovo post");
		if (bindingResult.hasErrors()) {
			model.addAttribute("content_template", "/posts/new");
		} else {
			/**
			 * Save
			 * */
			model.addAttribute("content_template", "/posts/index");
		}
		return super.defaultMapping(model);
	}

	private void addDefaultAttributes(Model model) {
		model.addAttribute("class_name", "posts");
	}

	/*
	 * @RequestMapping public String edit(Model model){ return null; }
	 * 
	 * @RequestMapping public String _autocompleteTitle(Model model){ return
	 * null; }
	 * 
	 * @RequestMapping public String _update(Model model){ return null; }
	 * 
	 * @RequestMapping public String _destroy(Model model){ return null; }
	 */
}
