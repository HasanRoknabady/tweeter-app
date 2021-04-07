package TimeLine;



import Tweet.TweetRender;
import User.UserRender;
import Data.Data;
import TimeLine.TimeLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TimeLine
{

    public static void generateTimeline(UserRender user)
    {
        List<TweetRender> tweets = new ArrayList<>();

        for ( UserRender following : user.getFollowing() ) {
            for ( TweetRender tweet : following.getUserTweets()) {
                tweets.add(tweet);
            }
        }

        for ( TweetRender tweet : user.getUserTweets()) {
            tweets.add(tweet);
        }

        tweets = sortTweets(tweets);

        if (tweets.size() == 0) {
            System.out.println("Your Timeline Is Empty!");
        }
        else
        {
            System.out.println();

            for (TweetRender tweet : tweets)
            {
                System.out.println(tweet.getID()
                        + "  " + tweet.getDateTime()
                        + "\n" + tweet.getText()
                        + "\nLikes: " + tweet.getLikes() );

                printLine();
            }
        }

    }

    public static List<TweetRender> sortTweets (List<TweetRender> tweets)
    {
        TweetRender tmp;
        int size = tweets.size();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size - 1 - i; j++)
            {
                if (tweets.get(j).compareTo(tweets.get(j + 1)) < 0 )
                {
                    tmp = tweets.get(j);
                    tweets.set( j , tweets.get(j + 1) );
                    tweets.set( j + 1 , tmp );
                }
            }
        }

        return tweets;
    }

    public static void printLine () {
        System.out.println("_______________________________________________________________________________________\n");
    }

}
