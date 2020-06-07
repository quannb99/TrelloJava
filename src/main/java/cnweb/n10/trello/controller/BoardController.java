package cnweb.n10.trello.controller;


import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.User;
import cnweb.n10.trello.repository.BoardRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class BoardController {

//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    private BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/index")
    public String addBoard(Principal principal, Model model){
        model.addAttribute("board", new Board());
        String USERNAME = principal.getName();
        List<Board> boards = boardRepository.findAllByUSERNAMEAndSTAR(USERNAME,0);
        model.addAttribute("boards", boards);
        List<Board> sboards = boardRepository.findAllByUSERNAMEAndSTAR(USERNAME,1);
        model.addAttribute("starboards", sboards);
        return "index";
    }
    @PostMapping("/index")
    public String addBoard(@ModelAttribute Board board){
        return Optional.ofNullable(boardService.add(board))
                .map(t -> "redirect:/index")
                .orElse("redirect:/index");
    }
    @GetMapping("/deleteB")
    public String deleteBoard(@RequestParam("ID") Integer ID){
        boardService.delete(ID);
        return "redirect:/index";
    }
    @GetMapping("/star")
    public String starBoard(@RequestParam("ID") Integer ID){
        Board board = boardRepository.findByID(ID);
        board.setSTAR(1);
        boardService.add(board);
        return "redirect:/index";
    }
    @GetMapping("/unstar")
    public String unstarBoard(@RequestParam("ID") Integer ID){
        Board board = boardRepository.findByID(ID);
        board.setSTAR(0);
        boardService.add(board);
        return "redirect:/index";
    }
}
