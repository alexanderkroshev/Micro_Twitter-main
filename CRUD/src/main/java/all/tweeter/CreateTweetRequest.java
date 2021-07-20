package all.tweeter;

import lombok.Data;

@Data
public class CreateTweetRequest {

    private String nickname;
    private String tweetText;

}
