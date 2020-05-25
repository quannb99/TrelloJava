package cnweb.n10.trello.controller;

import cnweb.n10.trello.model.User;
import cnweb.n10.trello.repository.UserRepository;
import cnweb.n10.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String addUser(@ModelAttribute User user){
        return Optional.ofNullable(userService.add(user))
                .map(t -> "login")
                .orElse("register");
    }
    @GetMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }
}
