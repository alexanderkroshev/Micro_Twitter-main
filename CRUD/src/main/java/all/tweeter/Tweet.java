package all.tweeter;

import all.person.Person;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tweets_all")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tweetText;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;
}