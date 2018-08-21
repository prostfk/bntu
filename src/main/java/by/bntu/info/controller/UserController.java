package by.bntu.info.controller;

import by.bntu.info.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping(value = "/auth")
    public String getAuth(){
        return "auth";
    }

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage(){
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public @ResponseBody String postRegistration(User user){
        return "WORKS";
    }

}
