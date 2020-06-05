package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findTaskByNAME(String NAME);
    List<Task> findAllByUSERNAMEAndBIDAndLID(String USERNAME, Integer BID, Integer LID);

}
