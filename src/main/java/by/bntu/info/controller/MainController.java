package by.bntu.info.controller;

import by.bntu.info.model.entity.Faculty;
import by.bntu.info.model.entity.Message;
import by.bntu.info.model.entity.News;
import by.bntu.info.repository.FacultyRepository;
import by.bntu.info.repository.MessageRepository;
import by.bntu.info.repository.NewsRepository;
import by.bntu.info.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping(value = "/")
    public ModelAndView getIndex(){
        List<News> all = newsRepository.findAllByIdIsNotNullOrderByIdDesc();
        return new ModelAndView("index" , "newsList", all);
    }

    @GetMapping(value = "/contacts")
    public String getContacts(){
        return "contacts";
    }

    @GetMapping(value = "/faculties")
    public ModelAndView getFaculties(){
        List<Faculty> all = facultyRepository.findAll();
        return new ModelAndView("faculties", "faculties", all);
    }

    @GetMapping(value = "/feedback")
    public ModelAndView getFeedBack(){
        return new ModelAndView("feedBack", "message", new Message());
    }

    @PostMapping(value = "/feedback")
    @ResponseBody
    public Message retMessage(Message message){
        messageRepository.save(message);
        return message;
    }

}
