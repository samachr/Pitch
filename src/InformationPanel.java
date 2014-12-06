import javax.swing.*;

/**
 * Created by sam on 11/21/14
 */
public class InformationPanel extends JPanel {

    private MapOverviewPanel miniMap;
    private JLabel score;
    private JLabel piano;
    private JLabel staff;
    private JLabel number;
    private JLabel time;

    public InformationPanel(GameMap gamemap) {
        this.setLayout(null);
        miniMap = new MapOverviewPanel(gamemap);
        miniMap.setSize(111, 111);
        miniMap.setLocation(10, 10);
        this.add(miniMap);

        score = new JLabel("Score: ");
        score.setSize(150, 20);
        score.setLocation(15, 120);
        this.add(score);

        piano = new JLabel("Piano: 0");
        piano.setSize(150, 20);
        piano.setLocation(20, 140);
        this.add(piano);

        staff = new JLabel("Staff: 0");
        staff.setSize(150, 20);
        staff.setLocation(20, 160);
        this.add(staff);

        number = new JLabel("Number: 0");
        number.setSize(150, 20);
        number.setLocation(20, 180);
        this.add(number);

        time = new JLabel("Time: ...");
        time.setSize(150, 20);
        time.setLocation(123, 20);
        this.add(time);
    }

    public void setNumberCount(int numberCount) {
        this.number.setText("Scale: " + numberCount);
    }
    public void setScoreCount(int scoreCount) {
        this.score.setText("Score: " + scoreCount);
    }
    public void setStaffCount(int staffCount) {
        this.staff.setText("Staff: " + staffCount);
    }
    public void setPianoCount(int pianoCount) {
        this.piano.setText("Piano: " + pianoCount);
    }
    public void setTimeRemaining(int TimeRemaining) {
        this.time.setText("Time: " + TimeRemaining);
    }

    public MapOverviewPanel getOverViewMap() {
        return miniMap;
    }
}
