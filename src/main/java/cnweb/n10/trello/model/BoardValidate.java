package cnweb.n10.trello.model;

import cnweb.n10.trello.repository.BoardRepository;
import cnweb.n10.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class BoardValidate {
    @Autowired
    BoardRepository boardRepository;

    public boolean isValid(Board board) {
        return Optional.ofNullable(board)
                .filter(t -> !StringUtils.isEmpty(t.getNAME()))
                .filter(t -> !StringUtils.isEmpty(t.getBGR()))
                .filter(t -> !StringUtils.isEmpty(t.getUSERNAME()))
                .isPresent();
    }
}
