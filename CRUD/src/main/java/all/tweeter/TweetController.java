package all.tweeter;

import all.person.Person;
import all.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TweetController {

    private final TweetService tweetService;
    private final PersonService personService;

    @Autowired
    public TweetController(TweetService tweetService, PersonService personService) {
        this.tweetService = tweetService;
        this.personService = personService;
    }

    @GetMapping("/tweets")
    public String findAll(Model model) {
        List<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweets", tweets);
        return "tweet/tweet-list";
    }

    @GetMapping("tweet-create")
    public String createTweetForm(Model model) {
        model.addAttribute("createTweetRequest", new CreateTweetRequest());
        return "tweet/tweet-create";
    }

    @PostMapping("/tweet-create")
    public String createTweet(CreateTweetRequest createTweetRequest) {
        if (personService.existsByNickname(createTweetRequest.getNickname())) {
            Person person = personService.findByNickname(createTweetRequest.getNickname());
            Tweet tweet = new Tweet();
            tweet.setTweetText(createTweetRequest.getTweetText());
            tweet.setPerson(person);
            tweetService.saveTweet(tweet);
        } else {
            throw new RuntimeException();
        }
        return "redirect:/tweets";
    }

    @GetMapping("tweet-delete/{id}")
    public String deleteTweet(@PathVariable("id") Long id) {
        tweetService.deleteById(id);
        return "redirect:/tweets";
    }

    @GetMapping("/tweet-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        Tweet tweet = tweetService.findById(id);
        model.addAttribute("tweet", tweet);
        return "tweet/tweet-update";
    }

    @PostMapping("/tweet-update")
    public String updateUser(Tweet tweet) {
        tweetService.saveTweet(tweet);
        return "redirect:/tweets";
    }

}

