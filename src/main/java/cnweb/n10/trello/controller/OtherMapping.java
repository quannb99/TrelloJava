package cnweb.n10.trello.controller;

import cnweb.n10.trello.WebUtils;
import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@Controller
public class OtherMapping {
@Autowired
    BoardRepository boardRepository;
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
    public String board(Model model, Principal principal){
        String USERNAME = principal.getName();
        List<Board> boards = boardRepository.findAllByUSERNAME(USERNAME);
        model.addAttribute("boards", boards);
        return "board";
    }
}
