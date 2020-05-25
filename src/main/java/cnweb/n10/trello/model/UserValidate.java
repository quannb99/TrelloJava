package cnweb.n10.trello.model;

import cnweb.n10.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class UserValidate {
    @Autowired
    UserRepository userRepository;

    public boolean isValid(User user) {
        return Optional.ofNullable(user)
                .filter(t -> userRepository.findByUSERNAME(user.getUSERNAME()) == null)
                .filter(t -> userRepository.findByEMAIL(user.getEMAIL()) == null)
                .filter(t -> !StringUtils.isEmpty(t.getFULLNAME()))
                .filter(t -> !StringUtils.isEmpty(t.getEMAIL()))
                .filter(t -> !StringUtils.isEmpty(t.getUSERNAME()))
                .filter(t -> !StringUtils.isEmpty(t.getPASSWORD()))
                .isPresent();
    }
}
