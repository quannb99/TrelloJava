package cnweb.n10.trello.service;


import cnweb.n10.trello.model.Task;
import cnweb.n10.trello.model.TaskValidate;
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
}
