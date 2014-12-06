import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by sam on 11/12/14
 */
public class GameGui extends JFrame implements KeyListener, ActionListener{
    private GameState theGame;

    private JMenuItem quit, restart;

    public GameGui(int mapSize, String Key) {
        this.setTitle("Pitch: in " + Key + " Major");
        theGame = new GameState(Key);
        theGame.setGameGui(this);


        //this.setSize(620, 232);
        this.setMinimumSize(new Dimension(620, 261));

        this.setLayout(new GridLayout(1, 3));
        Container pane = this.getContentPane();

        MapPanel map = new MapPanel(mapSize, theGame);
        map.setSize(201, 201);
        theGame.setMapPanel(map);

        InformationPanel infoPanel = new InformationPanel(map.gameMap);
        infoPanel.setSize(201, 201);
        theGame.setInfoPanel(infoPanel);

        QueryPanel queryPanel = new QueryPanel(theGame);
        queryPanel.setSize(201, 201);
        theGame.setQueryPanel(queryPanel);
        map.setOverviewPanel(infoPanel.getOverViewMap());

        restart = new JMenuItem("New Game");
        restart.addActionListener(this);
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        JMenu fileMenu = new JMenu("File");
        JMenuBar mbrMenuBar = new JMenuBar();
        fileMenu.add(restart);
        fileMenu.add(quit);
        mbrMenuBar.add(fileMenu);
        this.setJMenuBar(mbrMenuBar);

        pane.add(queryPanel);
        pane.add(map);
        pane.add(infoPanel);

        this.addKeyListener(this);
//        this.pack();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(restart)) {
            new GameStarter();
            this.dispose();
        } else if (e.getSource().equals(quit)) {
            System.exit(0);
        }
    }
}
