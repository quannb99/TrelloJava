package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findByID(Integer ID);
    List<Comment> findAllByTID(Integer TID);
    void deleteByID(Integer ID);
}
