import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by sam on 11/12/14.
 */
public class MapViewer extends JScrollPane implements MouseListener{
    private MapPanel mapPanel;

    public MapViewer(int MapSize) {
        this.addMouseListener(this);
        mapPanel = new MapPanel(MapSize);
        mapPanel.setPreferredSize(new Dimension(MapSize*40 + 1, MapSize*40+1));
        this.scrollRectToVisible(new Rectangle((int)mapPanel.gameMap.getPlayerLocation().getX(),
                (int)mapPanel.gameMap.getPlayerLocation().getY(),
                10, 10));

        this.setAutoscrolls(true);
        this.setViewportView(mapPanel);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.scrollRectToVisible(new Rectangle((int)mapPanel.gameMap.getPlayerLocation().getX(),
                (int)mapPanel.gameMap.getPlayerLocation().getY(),
                10, 10));
        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
