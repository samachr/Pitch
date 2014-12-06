import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sam on 11/18/14
 */
public class NoteStaff extends JLabel {

    private String noteName;
    private String clef;
    private int octave;

    private void setImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("./data/images/" + clef + "-" + octave + "-" + noteName + ".png"));
            this.setIcon(new ImageIcon(image));
        } catch(Exception e) {
            //this happens because not every octave has all the notes in it randomly generated. Quick fix.
            try {
                image = ImageIO.read(new File("./data/images/" + clef + "-" + (octave == 1 ? 2 : 1) + "-" + noteName + ".png"));
                this.setIcon(new ImageIcon(image));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setImage(String clef, int octave, String noteName) {
        this.noteName = noteName;
        this.octave = octave;
        this.clef = clef;
        setImage();
    }

    public NoteStaff(String clef, int octave, String noteName) {
        this.noteName = noteName;
        this.octave = octave;
        this.clef = clef;
        setImage();
    }
}
