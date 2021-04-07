package MenuMaker;


import Data.Data;
import TimeLine.TimeLine;
import Tweet.TweetRender;
import User.UserRender;

import java.util.Scanner;


public class Profile {

    public static void showProfile (UserRender user)
    {
        Menu.clearScreen();
        System.out.print("\n\n\n----------------------------++++++++++----------------------------\n");
        System.out.println("\nUsername: " + user.getUsername() + "\nDate Joined: " + user.getJoinDate() + "\n" +
                user.getTweetCount() + " Tweet(s)   " + user.getFollowers().size() + " Followers   " +
                user.getFollowing().size() + " Following");
        System.out.print("\n----------------------------++++++++++----------------------------\n");
        Menu.clearScreen();



        System.out.println("\n");


        if (user.getTweetCount() == 0) {
            System.out.println("user dont have any tweet.");
        }
        else for (int i = user.getTweetCount() - 1; i >= 0; i--)
        {
            TweetRender tweet = user.getUserTweets().get(i);

            System.out.println( tweet.getID() + "  " + tweet.getDateTime() + "\n" + tweet.getText() +
                    "\nLikes: " + tweet.getLikes() );



            TimeLine.printLine();
        }
    }
    public static void userProfile (Data data)
    {
        System.out.println("Enter a Username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        if ( data.checkUsername(username) )
        {
            showProfile( data.getUser(username) );
        }
        else System.out.println("This User does not exist.");

    }
}
