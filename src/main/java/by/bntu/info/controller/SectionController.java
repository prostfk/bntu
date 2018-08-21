package by.bntu.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SectionController {

    @GetMapping(value = "/science")
    public ModelAndView getSciencePage(){
        return new ModelAndView();
    }

}
