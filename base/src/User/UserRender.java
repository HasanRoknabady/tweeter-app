package User;

import Data.Data;
import Tweet.TweetRender;
import TimeLine.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserRender
{
    /**
     * @serial = 99222042
     * this shoud have installization with setter and
     * getter in UserRender Class
     */
    private Integer tweetCount;
    private LocalDate joinDate;
    private String username;
    private String password;
    private String bio;


    //make list for users that tweet and followers and following
    //of account that sign up to their accounts
    private List<TweetRender> userTweets;
    private List<UserRender> following;
    private List<UserRender> followers;


    public UserRender()
    {
        userTweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        tweetCount = 0;
        bio = "";
    }

    //getter and setter OF PRIVATES
    public void setJoinDate()
    {
        this.joinDate = LocalDate.now();
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public Integer getTweetCount()
    {
        return tweetCount;
    }
    public String getUsername()
    {
        return username;
    }
    public void getTimeline() {
        TimeLine.generateTimeline(this);
    }

    public String getBio() {
        return this.bio;
    }
    public void setBio()
    {
        System.out.println("Your Current Bio Is:" + this.bio + "\nEnter Your New Bio Text: ");
        Scanner in = new Scanner(System.in);
        this.bio =  in.nextLine();
        System.out.println("Done!");
    }

    //for Lists
    public List<UserRender> getFollowers()
    {
        return followers;
    }
    public List<UserRender> getFollowing()
    {
        return following;
    }

    public void newTweet(Data data)
    {
        this.tweetCount++;
        TweetRender tweet = new TweetRender (this.username, this.tweetCount);

        System.out.println("Enter Your Tweet Text (must be less than 140 characters)");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        if (text.length() <= 140)
        {
            tweet.setText(text);
            userTweets.add(tweet);
            data.getTweetList().add( tweet.getID() );

            System.out.println("Done!");
        }

        else {
            this.tweetCount--;
            System.out.println("Your Tweet MUST Be Less Than 140 Characters Long.");
        }
    }


    ///////////////////////////////////////
    //adding followers that we increace followers List
    public void addFollower(UserRender follower)
    {
        this.followers.add(follower);
    }
    //adding following that have some Exception :
    //1 - if the user name was equal with main entry user that sign in, in tweet app that cant be allowed
    //2 - you cant follow more than 1 that not be allowed
    //3 - if username doesnt exist you received error you cant do this

    public void addFollowing(Data data)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a username:");
        String username = in.next().toLowerCase();

        if ( data.checkUsername(username) )
        {
            if ( this.following.contains(data.getUser(username)) ) {
                System.out.println("You're Already Following " + username + " .");
            }

            else if ( this.username.equals(username) ) {
                System.out.println("You Can't Follow Yourself.");
            }

            else {
                this.following.add(data.getUser(username));
                data.getUser(username).addFollower(this);

                System.out.println("You're Now Following " + username + " !");
            }
        }

        else {
            System.out.println("This User Does Not Exist.");
        }

    }



    public void unfollow(Data data)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a Username:");
        String username = in.next().toLowerCase();

        if ( data.checkUsername(username) )
        {
            if ( username.equals(this.username) ) {
                System.out.println("You Can't Unfollow Yourself.");
            }

            else if (! this.following.contains(data.getUser(username)) ) {
                System.out.println("You're Not Following " + username + " .");
            }

            else {
                this.following.remove(data.getUser(username));
                data.getUser(username).followers.remove(this);

                System.out.println("User " + username + " Has Been Unfollowed.");
            }
        }

        else {
            System.out.println("This User Does Not Exist.");
        }

    }


    public boolean equals(UserRender user)
    {
        boolean flag = false;

        if (this.username.equals(user.username) || this.password.equals(user.password)) {
            flag = true;
        }
        return flag;
    }

    public void likeUserTweet(String ID, String likedBy)
    {

        for (int i = 0; i < userTweets.size(); i++)
        {
            if ( userTweets.get(i).getID().equals(ID) )
            {
                if ( userTweets.get(i).getLikes().equals(likedBy) ) {
                    System.out.println("You Have Already Likes This Tweet.");
                    break;
                }

                else {
                    userTweets.get(i).addLike(likedBy);
                    System.out.println("Done!");
                    break;
                }
            }

            else if (i == userTweets.size() - 1) {
                System.out.println("This Tweet ID does not exist.");
            }
        }

    }

    public List<TweetRender> getUserTweets()
    {
        return userTweets;
    }

    public LocalDate getJoinDate()
    {
        return joinDate;
    }
}
