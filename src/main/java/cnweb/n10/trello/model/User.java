package cnweb.n10.trello.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "userdb")
@Table(name = "userdb")
@Data
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String FULLNAME;
    private String EMAIL;
    private String USERNAME;
    private String PASSWORD;
}
