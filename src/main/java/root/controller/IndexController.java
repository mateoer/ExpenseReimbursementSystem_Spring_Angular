package root.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
public class IndexController implements ErrorController {
    
    private static final String PATH = "/error";
    
    @RequestMapping(value = PATH)
    public ModelAndView saveLeadQuery() {           
        return new ModelAndView("forward:/");
    }

    public String getErrorPath() {
        return PATH;
    }
    
    @RequestMapping("/login")
    public ModelAndView saveLeadQuery2() {
    	return new ModelAndView("forward:/");
    }
    @RequestMapping("/register")
    public ModelAndView saveLeadQuery3() {
    	return new ModelAndView("forward:/");
    }
}
