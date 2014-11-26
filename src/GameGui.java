import com.sun.corba.se.spi.orbutil.fsm.Input;

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
public class GameGui extends JFrame implements KeyListener{
    private MapPanel map;
    private InformationPanel infoPanel;
    private QueryPanel queryPanel;
    private GameState theGame;
    public GameGui(int mapSize, String Key) {
        this.setTitle("Pitch: in " + Key + " Major");
        theGame = new GameState(Key);

        this.setSize(600, 201);
        this.setMinimumSize(new Dimension(600, 201));
        this.setMaximumSize(new Dimension(601, 202));
        this.setLayout(new GridLayout(1, 3));
        Container pane = this.getContentPane();

        map = new MapPanel(mapSize, theGame);
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

       // InputPanel inputPane = new InputPanel(theGame);

        this.addKeyListener(this);
        //this.setLayout(null);
        this.pack();
        this.setLocationRelativeTo(null);
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
