import javax.swing.*;
import java.awt.*;

/**
 * Created by sam on 11/13/14
 */
public class MapOverviewPanel extends JPanel {
    private GameMap gameMap;

    public MapOverviewPanel(GameMap map) {
        this.gameMap = map;
    }

    public void paint(Graphics g) {

        //determine best scale size for the current panel size
        int bestSize = 10;
        while (gameMap.getSize() * bestSize > this.getWidth() && bestSize>2) {
            bestSize--;
        }

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        gameMap.paintScaled(buffer, bestSize);

        g.drawImage(bufferedImage, 0, 0, this);
    }
}
