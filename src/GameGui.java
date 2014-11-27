import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        this.setMinimumSize(new Dimension(610, 211));
        this.setMaximumSize(new Dimension(621, 232));
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

        this.addKeyListener(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
