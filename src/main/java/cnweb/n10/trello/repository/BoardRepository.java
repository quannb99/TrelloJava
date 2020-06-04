package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findBoardByNAME(String NAME);
    List<Board> findAllByUSERNAME(String USERNAME);

    @Query("SELECT b from boarddb b")
    Page<Board> findAllBoard(Pageable pageable);
}
