package cnweb.n10.trello.controller;


import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.User;
import cnweb.n10.trello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;


@Controller
public class BoardController {

//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    private BoardService boardService;

    @GetMapping("/index")
    public String addBoard(Principal principal, Model model){
        model.addAttribute("board", new Board());
        model.addAttribute("username", principal.getName());
        return "index";
    }
    @PostMapping("addBoard")
    public String addBoard(@ModelAttribute Board board){
        return Optional.ofNullable(boardService.add(board))
                .map(t -> "index")
                .orElse("index");
    }
}
