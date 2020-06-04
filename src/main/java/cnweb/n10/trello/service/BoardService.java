package cnweb.n10.trello.service;

import cnweb.n10.trello.model.Board;
import cnweb.n10.trello.model.BoardValidate;
import cnweb.n10.trello.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardValidate boardValidate;
    public Board add(Board board){
        if(boardValidate.isValid(board)) {
            return boardRepository.save(board);
        }
        return null;
    }

}
