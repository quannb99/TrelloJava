package cnweb.n10.trello.service;


import cnweb.n10.trello.model.Comment;
import cnweb.n10.trello.model.Task;
import cnweb.n10.trello.model.TaskValidate;
import cnweb.n10.trello.repository.CommentRepository;
import cnweb.n10.trello.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskValidate TaskValidate;
    @Autowired
    CommentRepository commentRepository;
    public Task add(Task task){
        if(TaskValidate.isValid(task)) {
            return taskRepository.save(task);
        }
        return null;
    }
    @Transactional
    public void delete(Integer ID){
        taskRepository.deleteByID(ID);
    }
    public void add(Comment comment){
        if (comment.getCONTENT() != null){
            commentRepository.save(comment);
        }
    }
    @Transactional
    public void deleteComment(Integer ID){
        commentRepository.deleteByID(ID);
    }
}
