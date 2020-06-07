package cnweb.n10.trello.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "taskdb")
@Table(name = "taskdb")
@Data
public class Task implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    private String NAME;

    @Column(nullable = false)
    private Integer LID;

    @Column(nullable = false)
    private Integer BID;

    @Column(nullable = false)
    private String USERNAME;

    @Column
    private String Description;
}
