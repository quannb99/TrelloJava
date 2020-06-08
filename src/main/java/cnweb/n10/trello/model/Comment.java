package cnweb.n10.trello.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "commentdb")
@Table(name = "commentdb")
@Data
public class Comment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    private String CONTENT;

    @Column(nullable = false)
    private Integer TID;

}
