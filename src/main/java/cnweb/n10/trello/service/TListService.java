package cnweb.n10.trello.service;


import cnweb.n10.trello.model.TList;
import cnweb.n10.trello.model.TListValidate;
import cnweb.n10.trello.repository.TListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TListService {
    @Autowired
    TListRepository tListRepository;
    @Autowired
    TListValidate tListValidate;
    public TList add(TList tlist){
        if(tListValidate.isValid(tlist)) {
            return tListRepository.save(tlist);
        }
        return null;
    }

}
