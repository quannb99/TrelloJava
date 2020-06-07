package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByUSERNAMEAndSTAR(String USERNAME,Integer STAR);
    List<Board> findAllByUSERNAME(String USERNAME);
    Board findByID(Integer ID);
    void deleteByID(Integer ID);
    @Query("SELECT b from boarddb b")
    Page<Board> findAllBoard(Pageable pageable);

    @Query("SELECT b FROM boarddb b WHERE b.NAME like :pattern and b.USERNAME = :username")
    List<Board> findBoardsByNameAndUSERNAME(@Param("pattern") String pattern,@Param("username") String username);
}
