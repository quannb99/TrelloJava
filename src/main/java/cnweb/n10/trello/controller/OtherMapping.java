package cnweb.n10.trello.controller;

import cnweb.n10.trello.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class OtherMapping {

//    @GetMapping(value = {"/index"})
//    public String index(Model model, Principal principal){
//        if(principal != null){
//            User loginedUser = (User)((Authentication) principal).getPrincipal();
//            String userInfo = WebUtils.toString(loginedUser);
//            model.addAttribute("userInfo", userInfo);
//            String msg = "Hello " + principal.getName() + " !";
//            model.addAttribute("msg", msg);
//        }
//        return "index";
//    }
    @GetMapping("/board")
    public String board(){
        return "board";
    }
}
