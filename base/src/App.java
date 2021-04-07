import Data.Data;
import MenuMaker.Menu;

public class App {

    public static void main(String[] args)
    {
        Data data = new Data();
        Menu menu = new Menu(data);

        Menu.mainMenuStarter();
    }

}
