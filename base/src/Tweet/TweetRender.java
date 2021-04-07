package Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetRender extends TweetManage
{
    public static List<String> likedBy = new ArrayList<>();
    public static Integer tweetNum, likes = 0;
    public static String ID;


    public String getID()
    {
        return ID;
    }

    public TweetRender(String username, Integer tweetNum)
    {
        TweetManage.tweetNum = tweetNum;
        TweetManage.ID = generateID(username, tweetNum);
    }

    public static String generateID (String username, Integer tweetNum)
    {
        String num = "" + tweetNum.toString();
        return username + "-" + num;
    }

    public void addLike(String likedBy) {
        TweetManage.likes++;
        TweetManage.likedBy.add(likedBy);
    }

    public String getLikes()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.likes);

        if (this.likes != 0)
        {
            str.append("  Liked By:");

            for (String username : this.likedBy) {
                str.append(" ").append(username).append(",");
            }
            str.deleteCharAt( str.length() - 1 );
            str.append(" .");
        }

        return str.toString();
    }

    public int compareTo(TweetManage tweet)
    {
        return getDateTime().compareTo(tweet.getDateTime());
    }

}
