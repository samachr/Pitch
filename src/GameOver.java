import javax.swing.*;
/**
 * Created by sam on 11/26/14.
 */
public class GameOver extends JFrame {
    public GameOver(boolean win, int score) {
        this.setTitle(((win) ? "Victory! " : "Defeat... ") + score);
        this.setSize(300, 200);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setVisible(true);
    }
}
