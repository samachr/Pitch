import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sam on 11/26/14.
 */
public class GameOver extends JFrame implements ActionListener{
    private JButton btnNewGame, btnExit;
    public GameOver(boolean win, int numPiano, int numStaff, int numNumber, int timeRemaining) {
        this.setTitle(((win) ? "Victory! " : "Defeat... "));
        this.setSize(315, 220);

//        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setLayout(null);
        JLabel score = new JLabel("Total Score: " + (numNumber + numStaff + numPiano + timeRemaining));
        score.setSize(150, 20);
        score.setLocation(15, 120);
        this.add(score);

        JLabel piano = new JLabel("Piano: " + numPiano);
        piano.setSize(150, 20);
        piano.setLocation(20, 140);
        this.add(piano);

        JLabel staff = new JLabel("Staff: " + numStaff);
        staff.setSize(150, 20);
        staff.setLocation(20, 160);
        this.add(staff);

        JLabel number = new JLabel("Number: " + numNumber);
        number.setSize(150, 20);
        number.setLocation(20, 180);
        this.add(number);

        JLabel time = new JLabel("Time: " + timeRemaining);
        time.setSize(150, 20);
        time.setLocation(20, 200);
        this.add(time);


//        JPanel pnlNewGameExit = new JPanel();
//        pnlNewGameExit.setLayout(new FlowLayout());
//        pnlNewGameExit.setSize(400,60);

        btnNewGame = new JButton("NewGame");
        btnNewGame.setSize(150, 30);
        btnNewGame.setLocation(5,5);
        btnNewGame.addActionListener(this);

        btnExit = new JButton("Exit");
        btnExit.setSize(150, 30);
        btnExit.setLocation(160, 5);
        btnExit.addActionListener(this);

        this.getContentPane().add(btnExit);
        this.getContentPane().add(btnNewGame);
//        pnlNewGameExit.add(btnNewGame);
//        pnlNewGameExit.add(btnExit);

//        this.getContentPane().add(pnlNewGameExit);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnNewGame)) {
            new GameStarter();
            this.dispose();
        } else if (e.getSource().equals(btnExit)) {
            System.exit(0);
        }
    }
}

