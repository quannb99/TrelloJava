package cnweb.n10.trello.model;

import org.springframework.util.StringUtils;

import java.util.Optional;

public class TListValidate {

    public boolean isValid(TList list) {
        return Optional.ofNullable(list)
                .filter(t -> !StringUtils.isEmpty(t.getNAME()))
                .filter(t -> !StringUtils.isEmpty(t.getBID()))
                .filter(t -> !StringUtils.isEmpty(t.getUSERNAME()))
                .isPresent();
    }
}
