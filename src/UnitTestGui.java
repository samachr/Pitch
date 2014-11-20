import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by sam on 11/12/14.
 */
public class UnitTestGui extends JFrame{
    private MapPanel anim;
    private JLabel staffImage;
    private PianoPanel piano;

    public UnitTestGui() {
        Container pane = this.getContentPane();

        anim = new MapPanel(20);
        anim.setSize(201, 201);
        anim.setLocation(10,10);
        pane.add(anim);

        MapOverviewPanel anim2 = new MapOverviewPanel(anim.gameMap);
        anim2.setSize(111, 111);
        anim2.setLocation(221, 5);
        pane.add(anim2);
        anim.setOverviewPanel(anim2);
        staffImage = new NoteStaff("treble", 2, "c");
        staffImage.setLocation(10, 221);
        staffImage.setSize(91,52);
        pane.add(staffImage);

        piano = new PianoPanel(10);
        piano.setLocation(201, 201);
        piano.setSize(141, 100);

        pane.add(piano);

        this.setLayout(null);
        this.setSize(600, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        anim.requestFocus();
    }
}
