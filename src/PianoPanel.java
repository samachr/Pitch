import javax.swing.*;
import java.awt.*;

/**
 * Created by sam on 11/18/14
 */
public class PianoPanel extends JPanel {

    private int highlightedKey;
    private final int[] WHITEKEYS = {0, 2,  4, 5, 7, 9, 11};
    private final int[] BLACKKEYS = {1, 3, -1, 6, 8, 10};

    public PianoPanel(int highlightedKey) {
        this.highlightedKey = highlightedKey;
    }

    private boolean isWhite(int keyNum) {
        for (int i = 0; i < 7; i++) {
            if (keyNum == WHITEKEYS[i]) {
                return true;
            }
        }
        return false;
    }



    public void highlightKey(int keyNum) {
        if (keyNum > -1 && keyNum < 12) {
            highlightedKey = keyNum;
            this.repaint();
        }
    }

    public void paint(Graphics g) {

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, this.getWidth(), this.getHeight());

        buffer.setColor(Color.WHITE);
        for (int i = 0; i < 7; i++) {
            buffer.fillRoundRect(1 + i * 20, 0, 19, 99, 3, 5);
        }

        if (isWhite(highlightedKey)) {
            for (int i = 0; i < 7; i++) {
                if (highlightedKey == WHITEKEYS[i]) {
                    buffer.setColor(Color.BLUE);
                    buffer.fillRoundRect(1 + i * 20, 0, 19, 98, 3, 5);
                }

            }
        }

        buffer.setColor(Color.BLACK);
        for (int i = 0; i < 7; i++) {
            if (!(i == 2 || i == 6)) {
                buffer.fillRoundRect(15 + i * 20, 0, 12, 62, 3, 3);
                buffer.setColor(Color.WHITE);
                buffer.drawLine(18 + i * 20, 60, 20 + i * 20, 60);
                buffer.setColor(Color.BLACK);
            }
        }

        if (!isWhite(highlightedKey)) {
            for (int i = 0; i < 6; i++) {
                if (highlightedKey == BLACKKEYS[i]) {
                    buffer.setColor(Color.BLUE);
                    buffer.fillRoundRect(15 + i * 20, 0, 12, 62, 3, 3);
                }

            }
        }

        buffer.setColor(Color.RED);
        buffer.drawLine(0, 0, 140, 0);
        buffer.drawLine(1, 1, 140, 1);

        g.drawImage(bufferedImage, 0, 0, this);
    }
}
