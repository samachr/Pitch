import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by sam on 11/12/14.
 */
public class MapPanel extends JPanel implements KeyListener {
    protected GameMap gameMap;

    public void setOverviewPanel(MapOverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
    }

    private MapOverviewPanel overviewPanel;
    private int mouseX, mouseY;
    public MapPanel(int MapSize) {
        //this.addMouseListener(this);
        this.addKeyListener(this);
        gameMap = new GameMap(MapSize);
        overviewPanel = null;
    }

    public void paint(Graphics g) {

        // use double buffering
        Image bufferedImage = createImage(getWidth(), getHeight());
        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        super.paint(buffer);

        gameMap.paint(buffer);

        //buffer.drawRoundRect(mouseLoc.x - 15, mouseLoc.y - 15, 30, 30, 3, 3);

        g.drawImage(bufferedImage, 0, 0, this);
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getLocationOnScreen());
//        //gameMap.movePlayer(GameMap.Direction.DOWN);
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        gameMap.setView(e.getX() - mouseX, e.getY() - mouseY);
//        this.repaint();
//
//        mouseX = e.getX();
//        mouseY = e.getY();
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 37:
                gameMap.movePlayer(GameMap.Direction.LEFT);
                break;
            case 39:
                gameMap.movePlayer(GameMap.Direction.RIGHT);
                break;
            case 38:
                gameMap.movePlayer(GameMap.Direction.UP);
                break;
            case 40:
                gameMap.movePlayer(GameMap.Direction.DOWN);
                break;
        }
        this.repaint();
        if (overviewPanel != null) overviewPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
