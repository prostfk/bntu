package by.bntu.info.controller;

import by.bntu.info.model.entity.News;
import by.bntu.info.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SectionController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping(value = "/science")
    public ModelAndView getSciencePage(){
        return new ModelAndView();
    }

    @GetMapping(value = "/news/{id}")
    public ModelAndView getSingleNews(@PathVariable Long id){
        return new ModelAndView("singleNews", "news", newsRepository.findNewsById(id));
    }


}
