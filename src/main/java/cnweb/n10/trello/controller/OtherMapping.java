package cnweb.n10.trello.controller;


import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.TList;
import cnweb.n10.trello.model.Task;
import cnweb.n10.trello.repository.BoardRepository;
import cnweb.n10.trello.repository.TListRepository;
import cnweb.n10.trello.repository.TaskRepository;
import cnweb.n10.trello.service.TListService;
import cnweb.n10.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/board")
    public String board(@RequestParam("BID") Integer BID, Model model, Principal principal) {
        model.addAttribute("TList", new TList());
        model.addAttribute("Task", new Task());
        String USERNAME = principal.getName();
        List<TList> ResList = tListRepository.findAllByUSERNAMEAndBID(USERNAME, BID);
        model.addAttribute("ResList", ResList);
        List<List<Task>> taskList = new ArrayList<>();
        int Li[] = new int[50];
        for(int i = 0; i < 50; i++){
            Li[i] = i;
        }
        model.addAttribute("Li", Li);
        for (TList l : ResList) {
            Integer lID = l.getID();
            List<Task> tasks = taskRepository.findAllByUSERNAMEAndBIDAndLID(USERNAME, BID, lID);
            taskList.add(tasks);
            model.addAttribute("taskList",taskList);
        }
        return "board";
    }

    @GetMapping("/task")
    public String Task(@RequestParam("BID") Integer BID,@RequestParam("TID") Integer TID,Model model,Principal principal){
        Task task = taskRepository.findByID(TID);
        model.addAttribute("task",task);
        String USERNAME = principal.getName();
        List<TList> ResList = tListRepository.findAllByUSERNAMEAndBID(USERNAME, BID);
        model.addAttribute("ResList", ResList);
        List<List<Task>> taskList = new ArrayList<>();
        int Li[] = new int[50];
        for(int i = 0; i < 50; i++){
            Li[i] = i;
        }
        model.addAttribute("Li", Li);
        for (TList l : ResList) {
            Integer lID = l.getID();
            List<Task> tasks = taskRepository.findAllByUSERNAMEAndBIDAndLID(USERNAME, BID, lID);
            taskList.add(tasks);
            model.addAttribute("taskList",taskList);
        }
        TList tl = tListRepository.findByID(task.getLID());
        model.addAttribute("tl",tl);
        return "task";
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
    @PostMapping("/save")
    public String save(@ModelAttribute Task task){
        return Optional.ofNullable(taskService.add(task))
                .map(t -> "redirect:/task?BID=" + task.getBID() + "&TID=" + task.getID())
                .orElse("redirect:/task?BID=" + task.getBID() + "&TID=" + task.getID());
    }
    @GetMapping("/deleteL")
    public String deleteL(@RequestParam("ID") Integer ID){
        TList tList = tListRepository.findByID(ID);
        tListService.delete(ID);
        return "redirect:/board?BID=" + tList.getBID();
    }
    @GetMapping("/deleteT")
    public String deleteT(@RequestParam("ID") Integer ID){
        Task task = taskRepository.findByID(ID);
        taskService.delete(ID);
        return "redirect:/board?BID=" + task.getBID();
    }

    @RequestMapping("/searchBoard")
    public String search(@RequestParam("pattern") String pattern, Model model,Principal principal){
        String username = principal.getName();
        List<Board> boards = boardRepository.findBoardsByNameAndUSERNAME("%" + pattern + '%',username);
        model.addAttribute("boards",boards);
        return "searchBoard";
    }
}