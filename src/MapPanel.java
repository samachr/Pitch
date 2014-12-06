import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by sam on 11/12/14
 */
public class MapPanel extends JPanel implements KeyListener {
    protected GameMap gameMap;

    public void setOverviewPanel(MapOverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
    }

    protected MapOverviewPanel overviewPanel;
    public MapPanel(int MapSize, GameState theGame) {
        gameMap = new GameMap(MapSize, theGame);
        theGame.setTimeRemaining(MapSize*MapSize + MapSize/2); //1.5 sec/tile
        overviewPanel = null;
    }

    public void paint(Graphics g) {

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        gameMap.paint(buffer);

        g.drawImage(bufferedImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                gameMap.AttemptMovePlayer(GameMap.Direction.LEFT);
                break;
            case 39:
                gameMap.AttemptMovePlayer(GameMap.Direction.RIGHT);
                break;
            case 38:
                gameMap.AttemptMovePlayer(GameMap.Direction.UP);
                break;
            case 40:
                gameMap.AttemptMovePlayer(GameMap.Direction.DOWN);
                break;
        }
        this.repaint();
        if (overviewPanel != null) overviewPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
