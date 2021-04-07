package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @serial = 99222042
 * this shoud have installization with setter and
 * getter in UserRender Class
 */
public class UserManager
{
    public static List<UserRender> UserList = new ArrayList<>();
    public static List<String> TweetList = new ArrayList<>();
    private static List<String> UsernameList = new ArrayList<>();

    //check methods for use in other class
    public static boolean checkUsername(String username)
    {
        boolean check = false;
        if (UsernameList.contains(username))
        {
            check = true;
        }
        return check;
    }
    public boolean checkUser(UserRender user)
    {
        boolean check = false;
        for (UserRender x : UserList)
        {
            if ( x.equals(user) )
            {
                check = true;
                break;
            }
        }
        return check;
    }
    ////////////////////////////////////////////

    //this method find username that scanner get in input
    //and if username exist return that
    //and if doesnt exist return null
    public static UserRender getUser(String username)
    {
        for (UserRender user : UserList)
        {
            if (user.getUsername().equals(username))
            {
                return(user);
            }
        }
        return null;
    }
    ///////////////////////////////////////////////////
    //adding user with check method
    public boolean addUser(UserRender user)
    {
        boolean check = false;
        if (  !checkUsername(  user.getUsername())  )
        {
            UsernameList.add(  user.getUsername()   );
            UserList.add(user);
            check = true;
        }
        return check;
    }

    //////////////////////////////////////////////////
    public static void likeTweet(UserRender user)
    {
        System.out.println("Enter the ID of the Tweet You Want to Like.");
        Scanner scanner = new Scanner(System.in);

        String ID = scanner.next();
        String[] str = ID.split("-");

        if ( UsernameList.contains(str[0]) )
        {
            if ( str[0].equals(user.getUsername()) ) {
                System.out.println("You are not allow to Like Your Own Tweets you cant " +
                        "do this please try another id.");
            }
            else if ( getUser(str[0]).getTweetCount() != 0 )
            {
                getUser(str[0]).likeUserTweet(ID, user.getUsername());
            }
        }
        else System.out.println("This Tweet ID does not exist.");
    }




}
