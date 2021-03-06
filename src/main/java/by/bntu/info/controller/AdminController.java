package by.bntu.info.controller;

import by.bntu.info.model.entity.News;
import by.bntu.info.repository.MessageRepository;
import by.bntu.info.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
//@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "")
    public String getIndex(){
        return "adminIndex";
    }

    @GetMapping(value = "/addNews")
    public ModelAndView addNewsGet(){
        return new ModelAndView("adminAddNews", "news", new News());
    }

    @PostMapping(value = "/addNews")
    public String addNewsPost(News news, MultipartFile file){
        if (news.validate()){
            news.setPath(saveFile(file));
            newsRepository.save(news);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/feedback")
    public ModelAndView findUserFeedback(){
        return new ModelAndView("adminFeedbackList", "messages", messageRepository.findAll());
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editNews(@PathVariable("id")Long id){
        return new ModelAndView("editNews", "news", newsRepository.findNewsById(id));
    }

    @PostMapping(value = "/edit/{id}")
    public String submitChanges(@PathVariable Long id, News news, MultipartFile file){
        News old = newsRepository.findNewsById(id);
        if (news.validate()){
            if (file.getOriginalFilename().equals("")){
                news.setPath(old.getPath());
            }else{
                news.setPath(saveFile(file));
            }
            newsRepository.update(news.getTitle(),news.getContent(),news.getPath(),news.getFacultyId(),news.getType(),news.getId());
        }
        return "redirect:/admin";
    }

    private String saveFile(MultipartFile file) {
        String filePath = "src/main/webapp/resources/newsPics/";
        File javaFile = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
            javaFile = new File(javaFile.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(javaFile));
            stream.write(bytes);
            stream.flush();
            stream.close();
            return "/resources/newsPics/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
