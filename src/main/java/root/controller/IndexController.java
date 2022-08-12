package root.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://http://54.85.200.45:9050")
public class IndexController implements ErrorController {
    
    private static final String PATH = "/error";
    
    @RequestMapping(value = PATH)
    public ModelAndView saveLeadQuery() {           
        return new ModelAndView("forward:/");
    }

    public String getErrorPath() {
        return PATH;
    }
}
