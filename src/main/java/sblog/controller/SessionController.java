package sblog.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class SessionController {

    @RequestMapping
    public String create(Model model){
        return null;
    }

    @RequestMapping
    public String destroy(Model model){
        return null;
    }
}
