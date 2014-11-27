import javax.swing.*;
import java.awt.*;

/**
 * Created by sam on 11/26/14.
 */
public class InputPanel extends JFrame {

    GameState theGame;

    private JButton btnC, btnD, btnE, btnF, btnG, btnA, btnB;
    private JButton btnSharp, btnFlat;

    public InputPanel(GameState theGame) throws HeadlessException {
        this.theGame = theGame;

        this.setSize(300, 100);

        this.setVisible(true);
    }
}
