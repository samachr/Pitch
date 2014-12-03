import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by sam on 11/26/14.
 */
public class GameOver extends JFrame {
    public GameOver(boolean win, int score) {
        this.setTitle(((win) ? "Victory! " : "Defeat... ") + score);
        this.setSize(300, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new GameStarter();
            }
        });


    }
}

