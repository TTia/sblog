/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController extends AbstractController{
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
