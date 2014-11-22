import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by sam on 11/12/14.
 */
public class UnitTestGui extends JFrame implements KeyListener{
    private MapPanel map;
    private InformationPanel infoPanel;
    private QueryPanel queryPanel;
    private GameState theGame;
    public UnitTestGui() {

        theGame = new GameState();

        this.setSize(600, 201);
        this.setMinimumSize(new Dimension(600, 201));
        this.setMaximumSize(new Dimension(601, 202));
        this.setLayout(new GridLayout(1, 3));
        Container pane = this.getContentPane();

        map = new MapPanel(20, theGame);
        map.setSize(201, 201);
        theGame.setMapPanel(map);

        infoPanel = new InformationPanel(map.gameMap, theGame);
        infoPanel.setSize(201, 201);

        theGame.setInfoPanel(infoPanel);

        queryPanel = new QueryPanel(theGame);
        queryPanel.setSize(201, 201);
        theGame.setQueryPanel(queryPanel);
        map.setOverviewPanel(infoPanel.getOverViewMap());

        pane.add(queryPanel);
        pane.add(map);
        pane.add(infoPanel);

        this.addKeyListener(this);
        //this.setLayout(null);
        this.pack();
        this.setVisible(true);
        //this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       theGame.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
