/**
 * Created by sam on 11/12/14.
 */
public class UnitTesting {
    public static void main (String args[]) {
        GameMap testMap = new GameMap(50);
        testMap.printMaptoConsole();

        new UnitTestGui();
    }
}
