package sblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController extends AbstractController{
	@RequestMapping("/")
	public String welcome(Model model) {
    	model.addAttribute("page_title", "RBlog");
    	model.addAttribute("content_template", "/posts/index");
    	
    	this.addDefaultAttributes(model);
		return super.defaultMapping(model);
	}

    @RequestMapping(value = "/abstract")
    public String abstractPage(Model model){
    	model.addAttribute("page_title", "Abstract");
    	model.addAttribute("content_template", "/pages/abstract");
    	
    	this.addDefaultAttributes(model);
        return super.defaultMapping(model);
    }

    @RequestMapping(value = "/author")
    public String authorPage(Model model){
    	model.addAttribute("page_title", "Autore");
    	model.addAttribute("content_template", "/pages/author");
    	
    	this.addDefaultAttributes(model);
        return super.defaultMapping(model);
    }
    
    private void addDefaultAttributes(Model model){
    	model.addAttribute("class_name", "pages");
    }
}
