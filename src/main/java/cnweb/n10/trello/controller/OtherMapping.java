package cnweb.n10.trello.controller;


import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.TList;
import cnweb.n10.trello.model.Task;
import cnweb.n10.trello.repository.TListRepository;
import cnweb.n10.trello.repository.TaskRepository;
import cnweb.n10.trello.service.TListService;
import cnweb.n10.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OtherMapping {
    @Autowired
    TListRepository tListRepository;
    @Autowired
    TListService tListService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;

    @GetMapping("/board")
    public String board(@RequestParam("BID") Integer BID, Model model, Principal principal) {
        model.addAttribute("TList", new TList());
        model.addAttribute("Task", new Task());
        String USERNAME = principal.getName();
        List<TList> ResList = tListRepository.findAllByUSERNAMEAndBID(USERNAME, BID);
        model.addAttribute("ResList", ResList);
        Integer c = 0;
        List<List<Task>> taskList = new ArrayList<>();
//        List<Integer> Li = new ArrayList<>();
        int Li[] = new int[50];
        for(int i = 0; i < 50; i++){
            Li[i] = i;
        }
        model.addAttribute("Li", Li);
        for (TList l : ResList) {
            Integer LID = l.getID();
            List<Task> tasks = taskRepository.findAllByUSERNAMEAndBIDAndLID(USERNAME, BID, LID);
            taskList.add(tasks);
            model.addAttribute("taskList",taskList);
        }

        return "board";
    }

    @PostMapping("/addList")
    public String addList(@ModelAttribute TList tList) {
        return Optional.ofNullable(tListService.add(tList))
                .map(t -> "redirect:/board?BID=" + tList.getBID())
                .orElse("redirect:/board?BID=" + tList.getBID());
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {
        return Optional.ofNullable(taskService.add(task))
                .map(t -> "redirect:/board?BID=" + task.getBID())
                .orElse("redirect:/board?BID=" + task.getBID());
    }
}