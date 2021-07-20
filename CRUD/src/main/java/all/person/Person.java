package all.person;


import all.tweeter.Tweet;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nickname;

    @OneToMany( orphanRemoval = true, cascade = CascadeType.REMOVE, mappedBy = "person")
    private List<Tweet> tweets;
}

