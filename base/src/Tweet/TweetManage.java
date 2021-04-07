package Tweet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetManage
{
    public static List<String> likedBy = new ArrayList<>();
    public static Integer tweetNum, likes = 0;
    public static String ID;

    private String text;
    private Date DateTime;

    public TweetManage()
    {
        this.DateTime = new Date();
    }


    //generate getter and setter of
    //1-ID  2-text  3-likes  4-tweet numbera
    //5-datetime
    public String getID()
    {
        return ID;
    }
    public void setID(String ID)
    {
        this.ID = ID;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setLikes(Integer likes)
    {
        this.likes = likes;
    }
    public Integer getTweetNum()
    {
        return tweetNum;
    }
    public void setTweetNum(Integer tweetNum)
    {
        this.tweetNum = tweetNum;
    }

    public Date getDateTime() {
        return DateTime;
    }


}


