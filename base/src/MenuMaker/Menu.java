package MenuMaker;

import Data.Data;
import User.UserRender;
import MenuMaker.*;

import java.util.Scanner;

/**
 * @serial = 99222042
 * this for menuStarter
 */
public class Menu {

    private static Data data;

    //use constructor method for init and save date to use
    public Menu(Data data)
    {
        Menu.data = data;
    }

    //make menu that have 4 option
    //1.Login : for user have account
    //2.Sign Up : for user to want create account and don't have it
    //3.Exit : for close app
    public static void mainMenuStarter()
    {
        System.out.print("\n\n\n-----------------------++++++++++-----------------------\n");
        System.out.println("Welcome to Tweeter\n");

        System.out.println("Please enter number for option you have:\n" +
                "!!!If you dont know how to sign up type '' ? '' \n\n1. Login\n2. Sign Up" +
                "\t\t?. Help" + "\n3. Exit");
        System.out.print("-----------------------++++++++++-----------------------\n\n");

        Scanner in = new Scanner(System.in);
        char choice = in.next().charAt(0);

        while (choice != '3')
        {
            switch (choice)
            {
                case '1' :
                    clearScreen();
                    loginOption();
                    break;
                case '2' :
                    clearScreen();
                    signUpOption();
                    break;
                case '?' :
                    clearScreen();
                    showHelp();
                default:
                    System.out.println("!!!Please enter a allow number that come in menu.");
                    choice = in.next().charAt(0);
            }

        }


    }

    //this is for nub people that cant sign up
    private static void showHelp()
    {
        System.out.print("\n++-----------------------------++++++++++-----------------------------++\n");
        System.out.print("++                                                                    ++\n");

        System.out.println("++    you shoud sing in to tweeter first for this " +
                "\n++    you shoud enter 1 to save your acount on my tweeter\n++    app for this in first you" +
                "enter your username\n++    that must have atleast 6 caractor\n++    and after that you shoud have atleast 8 " +
                "\n++    " + "digit or caractor after that you can login with enter\n++    number 2 and have some option like\n" +
                "++    1.tweet\n++    2.follow\n++    3.like other tweet\n++    4.see your profile \n++    5.set your bio" +
                "\n++    6.unfollow someone\n++    and some other option");
        System.out.print("++                                                                    ++\n");
        System.out.print("++-----------------------------++++++++++-----------------------------++\n");
        System.out.println("Please Enter 1 to sign up\n");
    }

    public static void loginOption()
    {
        Scanner in = new Scanner(System.in);
        UserRender user = new UserRender();

        System.out.println("Please Enter Your Username :(you can't use space in username)");
        System.out.println("____________");
        user.setUsername( in.next().toLowerCase() );
        System.out.println("____________");


        //for case the user doesn't sign up can't login
        //after check that we got that the user exist
        //we get pass that user entered in sign option
        if (! data.checkUsername(user.getUsername()) )
        {
            System.out.println("Sorry, This Username Doesn't Exist.");
            //after that we found out user doesnt exit we replay app again
            mainMenuStarter();
        }
        else
        {
            System.out.println("Please Enter Your Password :(you enter that in sign option)");
            System.out.println("____________");
            user.setPassword( in.next() );
            System.out.println("____________");

            //this method and after that else check if user exist and pass is correct allow user to login to this app
            if ( !data.checkUser(user) )
            {
                System.out.println("Incorrect Password.");
                mainMenuStarter();

            }
            else
            {
                System.out.println(  "congratulation ***" + user.getUsername() +  "***" + " Welcome to Tweeter");
                options( data.getUser(user.getUsername()) );
            }
        }
    }

    public static void signUpOption()
    {
        Scanner in = new Scanner(System.in);
        UserRender newUser = new UserRender();

        System.out.println("Please Enter Your Username :(you can't use space in username)");
        String username = in.next().toLowerCase();

        //this methods check username have correct style that have some element
        //1.username cant have space or '-' character
        //2.username shoud have at least 4 character
        if ( username.contains("-") || username.contains(" ") )
        {
            System.out.println("Username Cannot Contain the Letter ' - ' or space.");
            mainMenuStarter();
        }
        else if (username.length() < 4)
        {
            System.out.println("Username length shoud at least have 4 character .");
        }
        else
        {
            newUser.setUsername(username);
        }

        //like above if, we shoud have check username that cant be same to other
        if ( data.checkUsername(newUser.getUsername()) )
        {
            System.out.println("Sorry, This Username Has Already Been Taken.");
        }
        else
        {
            System.out.println("Please Enter a Password:");
            newUser.setPassword( in.next() );
            newUser.setJoinDate();

            data.addUser(newUser);
            System.out.println("you sign up SUCCESSFULLY!!!\n please enter ' 0 ' to see Options");
        }
        mainMenuStarter();
    }


    /*
        here we have options that we shoud have in tweeter likes:
                                                                  //0:options
                                                                 //1:show profile
                                                                //2:tweet
                                                               //3:follow
                                                              //4:unfollow
                                                             //5:see followers
                                                            //6:see following
                                                           //7:like tweet
                                                          //8:timeline
                                                         //9:see users
                                                        //10:set bio
                                                       //11:delete user
     */
    public static void options(UserRender U)
    {
        System.out.println("Type ' 0 ' to Display the Options you have.");


        Scanner in = new Scanner(System.in);
        String choice = in.nextLine().toLowerCase();

        while (! choice.equals("12") )
        {
            switch (choice)
            {
                case "0":
                    clearScreen();
                    showOptionsList();
                    choice = in.nextLine().toLowerCase();
                    break;

                case "1":
                    clearScreen();
                    Profile.userProfile(data);
                    choice = in.nextLine().toLowerCase();
                    break;


                case "2":
                    clearScreen();
                    U.newTweet(data);
                    choice = in.nextLine().toLowerCase();
                    break;

                case "3":
                    clearScreen();
                    U.addFollowing(data);
                    choice = in.nextLine().toLowerCase();
                    break;

                case "4":
                    clearScreen();
                    U.unfollow(data);
                    choice = in.nextLine().toLowerCase();
                    break;

                case "5":
                    clearScreen();
                    if (U.getFollowers().size() == 0) {
                        System.out.println("You don't have any followers.");
                    }
                    else for (UserRender u : U.getFollowers()) {
                        System.out.println( u.getUsername() );
                    }
                    choice = in.nextLine().toLowerCase();
                    break;

                case "6":
                    clearScreen();
                    if (U.getFollowing().size() == 0) {
                        System.out.println("You haven't followed anyone yet.");
                    }
                    else for (UserRender u : U.getFollowing()) {
                        System.out.println(u.getUsername());
                    }
                    choice = in.nextLine().toLowerCase();
                    break;

                case "7":
                    clearScreen();
                    data.likeTweet(U);
                    choice = in.nextLine().toLowerCase();
                    break;


                case "8":
                    clearScreen();
                    U.getTimeline();
                    choice = in.nextLine().toLowerCase();
                    break;

                case "9":
                    clearScreen();
                    for (String username : data.getUsernameList() ) {
                        System.out.print(username + " --- ");
                    }
                    System.out.println();

                    choice = in.nextLine().toLowerCase();




                case "10":
                    clearScreen();
                    U.setBio();
                    choice = in.nextLine().toLowerCase();
                    break;

                case "11":
                    clearScreen();
                    System.out.println("Are You Sure You Want to Delete Your Account? Type \"DELETE\" to Confirm.");

                    if ( in.nextLine().equalsIgnoreCase("DELETE") ) {
                        data.deleteUser(U);
                        System.out.println("Your Account Has Been Deleted. You Will Be Logged Out.");
                        //loged out
                        choice = "12";
                        clearScreen();
                    }
                    else {
                        System.out.println("Your Account Was NOT Deleted.");
                        choice = in.nextLine().toLowerCase();
                    }
                    break;

                /*
                case "12" :
                    System.out.println("you loged out out please Type ' 0 ' to see Options");
                    choice = in.nextLine().toLowerCase();
                    break;
                 */
                default:
                    System.out.println("Please Enter a Valid Command Options. Type ' 0 ' to Display the Options List.");
                    choice = in.nextLine().toLowerCase();
            }


        }

        //afte have options we shoud have main menu
        clearScreen();
        mainMenuStarter();
    }

    public static void showOptionsList()
    {
        String line = "---------------------------------------------------------------";

        System.out.print("please choose your choice with Type number(all options have number after -->> 'num' )\n");
        System.out.print("\n**--------------------------------++++++++------------------------------**\n");
        System.out.print(    "see your profile -->> '1'\t |\t  logout -->>'11'\n"
                + line + "\n"
                + "tweet -->> '2'\t\t\t\t " + "|"
                + "\t  follow -->> '3'\n"
                + line + "\n"
                + "unfollow -->> '4'\t\t\t "  + "|"
                + "\t  see followers -->> '5'\n"
                + line + "\n"
                + "see following -->> '6'\t\t " + "|"
                + "\t  like -->> '7'\n"
                + line + "\n"
                + "timeline -->> '8'\t\t\t " + "|"
                + "\t  all users -->> '9'\n"
                + line + "\n"
                + "see your bio -->> '10'\t\t " + "|" + "\t  delete account -->> '11'\n");
        System.out.print("**--------------------------------++++++++------------------------------**\n");
    }

    public static void showFirstOptions()
    {
        //this method its optional not complete
    }

    public static void clearScreen()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
}