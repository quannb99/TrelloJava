package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.TList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TListRepository extends JpaRepository<TList, Integer> {
    TList findByID(Integer ID);
    void deleteByID(Integer ID);
    List<TList> findAllByUSERNAMEAndBID(String USERNAME, Integer BID);

}
