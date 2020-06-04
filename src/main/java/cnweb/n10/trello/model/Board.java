package cnweb.n10.trello.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity(name = "boarddb")
@Table(name = "boarddb")
@Data
public class Board implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String NAME;
    private Integer STAR;
    private String BGR;
    private String USERNAME;
}
