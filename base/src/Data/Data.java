package Data;

import User.UserRender;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Data
{

    private final java.util.List<UserRender> UserList;
    private final java.util.List<String> TweetList;
    private final java.util.List<String> UsernameList;

    public Data() {
        UserList     = new ArrayList<>();
        TweetList    = new ArrayList<>();
        UsernameList = new ArrayList<>();
    }

    public void addUser(UserRender user)
    {
        UsernameList.add( user.getUsername() );
        UserList.add(user);
    }

    public void deleteUser(UserRender user)
    {
        UserList.remove(user);
        UsernameList.remove(user.getUsername());
    }

    public boolean checkUser(UserRender user)
    {
        boolean check = false;

        for (UserRender x : UserList) {
            if ( x.equals(user) ) {
                check = true;
                break;
            }
        }

        return check;
    }

    public boolean checkUsername(String username)
    {
        boolean check = false;

        if (UsernameList.contains(username))
        {
            check = true;
        }
        return check;
    }

    public java.util.List<String> getUsernameList() {
        return this.UsernameList;
    }

    public UserRender getUser(String username)
    {
        for (UserRender user : UserList)
        {
            if (user.getUsername().equals(username)) {
                return(user);
            }
        }

        return null;
    }

    public boolean checkIfTweetExists(String ID)
    {
        boolean check = false;

        if ( ID.matches("\\S+-[0-9]+")  )
        {
            String[] str = ID.split("-");
            String username = str[0];
            int tweetNum = Integer.parseInt(str[1]) - 1;

            if ( UsernameList.contains(username) ) {
                check = ( tweetNum < getUser(username).getTweetCount() && tweetNum >= 0 );
            }
        }

        return check;
    }

    public void likeTweet(UserRender user)
    {
        System.out.println("Enter the ID of the Tweet You Want to Like.");
        Scanner in = new Scanner(System.in);
        String ID = in.next();

        if ( checkIfTweetExists(ID) )
        {
            String[] str = ID.split("-");
            String username = str[0];
            int tweetNum1 = Integer.parseInt(str[1]) - 1;
            String tweetNum = Integer.toString(tweetNum1);

            getUser(username).likeUserTweet(tweetNum, user.getUsername());
        }
        else System.out.println("This Tweet ID Does Not Exist.");
    }

    public java.util.List<String> getTweetList() {
        return TweetList;
    }
}
