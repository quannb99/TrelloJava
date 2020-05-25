package cnweb.n10.trello.repository;

import cnweb.n10.trello.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUSERNAME(String username);
    User findByEMAIL(String email);

    @Query("SELECT u from userdb u")
    Page<User> findAllUser(Pageable pageable);
}

