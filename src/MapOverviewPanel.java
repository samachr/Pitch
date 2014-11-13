import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by sam on 11/13/14.
 */
public class MapOverviewPanel extends JPanel {
    private GameMap gameMap;

    public MapOverviewPanel(GameMap map) {
        this.gameMap = map;
    }

    public void paint(Graphics g) {

        //determine best size
        int bestSize = 10;
        while (gameMap.getSize() * bestSize > this.getWidth() && bestSize>2) {
            bestSize--;
            //System.out.println("the current best size is: " + bestSize);
        }

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        gameMap.paintScaled(buffer, bestSize);

        //buffer.drawRoundRect(mouseLoc.x - 15, mouseLoc.y - 15, 30, 30, 3, 3);

        g.drawImage(bufferedImage, 0, 0, this);
    }
}
