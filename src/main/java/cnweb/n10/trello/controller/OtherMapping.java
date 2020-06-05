package cnweb.n10.trello.controller;


import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.TList;
import cnweb.n10.trello.repository.TListRepository;
import cnweb.n10.trello.service.TListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class OtherMapping {
@Autowired
    TListRepository tListRepository;
@Autowired
    TListService tListService;

    @GetMapping("/board")
    public String board(@RequestParam("BID") Integer BID, Model model, Principal principal){
        model.addAttribute("TList", new TList());
        String USERNAME = principal.getName();
        List<TList> ResList = tListRepository.findAllByUSERNAMEAndBID(USERNAME, BID);
        model.addAttribute("ResList", ResList);
        return "board";
    }
    @PostMapping("/addList")
    public String addList(@ModelAttribute TList tList){
        return Optional.ofNullable(tListService.add(tList))
                .map(t -> "redirect:/board?BID=" + tList.getBID())
                .orElse("redirect:/board?BID=" + tList.getBID());
    }
}
