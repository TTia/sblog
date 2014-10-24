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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController extends AbstractController{
	@RequestMapping("/")
	public String welcome(Model model) {
		addCssLinksToPath(model);
		return "/posts/index";
	}
	/*
    @RequestMapping(method = GET, value = "/index")
    public String index(Model model) {
        System.err.println("Index");
        return "index.html";
    }

    private static final String abstract_mapping = "abstract";
    @RequestMapping(value = abstract_mapping)
    public String abstractPage(Model model){
        return abstract_mapping;
    }

    private static final String author_page_mapping = "author";
    @RequestMapping(value = author_page_mapping)
    public String authorPage(Model model){
        return author_page_mapping;
    }
    */

}
