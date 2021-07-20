package all.tweeter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Tweet findById(Long id) {
        return tweetRepository.getOne(id);
    }

    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public void deleteById(long id) {
        tweetRepository.deleteById(id);
    }

}
