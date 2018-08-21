package by.bntu.info.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SectionController {

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/science")
    public ModelAndView getSciencePage(){
        return new ModelAndView();
    }

}
