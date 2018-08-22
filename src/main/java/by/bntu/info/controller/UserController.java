package by.bntu.info.controller;

import by.bntu.info.model.entity.User;
import by.bntu.info.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/auth")
    public String getAuth(Model model, @RequestParam(value = "error", defaultValue = "")String error){
        if (!error.equals("")){
            model.addAttribute("alert", "Incorrect data");
        }
        return "auth";

    }

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage(){
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public @ResponseBody String postRegistration(User user){
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Success";
    }




//    AJAX
    @GetMapping(value = "/checkUsername")
    @ResponseBody
    public String processUsername(String username){
        return userRepository.findUserByUsername(username) == null ? "" : "User with this username already exists";
    }

}
