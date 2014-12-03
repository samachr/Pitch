import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
* Created by sam on 11/21/14.
*/
public class GameState implements ActionListener{
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
    private GameGui gameGui;
    private Timer timeTicker;
    private int timeRemaining;
    private int squaresCollectedPiano;
    private int squaresCollectedStaff;
    private int squaresCollectedNumber;
    private int score;
    protected boolean querying;
    protected String scale[];

    private MapPanel mapPanel;

    private QueryPanel queryPanel;
    private InformationPanel infoPanel;

    public GameState(String Key) {
        this.squaresCollectedPiano = 0;
        this.squaresCollectedStaff = 0;
        this.squaresCollectedNumber = 0;
        this.score = 0;
        this.querying = false;
        this.scale = Music.GetMajorScale(Key);
        this.timeTicker = new Timer(1000, this);
        timeTicker.start();
        this.timeRemaining = 30;
    }

    public void setInfoPanel(InformationPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
    public void setQueryPanel(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;
    }
    public void setMapPanel(MapPanel theMap) {
        this.mapPanel = theMap;
    }
    public void setGameGui(GameGui gameGui) {
        this.gameGui = gameGui;
    }

    public void tryMove(GameMap.TileType type) {
        if (type == GameMap.TileType.EMPTY) {
            Move(GameMap.TileType.EMPTY);
        } else {
            queryPanel.setQuery(type);
            querying = true;
        }
    }

    public void Move(GameMap.TileType type) {
        switch(type) {
            case EMPTY:
                break;
            case STAFF:
                squaresCollectedStaff++;
                infoPanel.setStaffCount(squaresCollectedStaff);
//                System.out.println("Collected a Staff, total Staffs: " + squaresCollectedStaff);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case PIANO:
                squaresCollectedPiano++;
                infoPanel.setPianoCount(squaresCollectedPiano);
//                System.out.println("Collected a Piano, total Piano: " + squaresCollectedPiano);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case NUMBER:
                squaresCollectedNumber++;
                infoPanel.setNumberCount(squaresCollectedNumber);
//                System.out.println("Collected a Number, total Number: " + squaresCollectedNumber);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case WIN:
                infoPanel.setScoreCount(score);
//                System.out.println("You won!");
                this.timeTicker.stop();
                new GameOver(true, score + timeRemaining);
                gameGui.dispose();
                break;
            case START:
                break;
        }
       mapPanel.gameMap.movePlayer();
       querying = false;
    }

    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyCode());
        if (!querying) {
            switch (e.getKeyCode()) {
                case 37:
                    mapPanel.gameMap.AttemptMovePlayer(GameMap.Direction.LEFT);
                    break;
                case 39:
                    mapPanel.gameMap.AttemptMovePlayer(GameMap.Direction.RIGHT);
                    break;
                case 38:
                    mapPanel.gameMap.AttemptMovePlayer(GameMap.Direction.UP);
                    break;
                case 40:
                    mapPanel.gameMap.AttemptMovePlayer(GameMap.Direction.DOWN);
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case 38: //up for sharp, (this seems more intuitive to me, but b for flat and s for sharp also works)
                    queryPanel.queryInput('s');
                    break;
                case 40: //down for flat
                    queryPanel.queryInput('b');
                    break;
                case 27: //escape
                    querying = false;
                    queryPanel.setQuery(GameMap.TileType.EMPTY);
                    break;
                case 8: //backspace
                    queryPanel.queryInput('B'); //B for backspace. this is sloppy, but it works
                    break;
                case 65: //input keys a-g, n, s
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 78:
                case 83:
                    queryPanel.queryInput(e.getKeyChar());
                    break;
        }
    }
        mapPanel.repaint();
        if (mapPanel.overviewPanel != null) mapPanel.overviewPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        infoPanel.setTimeRemaining(timeRemaining--);
        if (timeRemaining == 0) {
            timeTicker.stop();
            new GameOver(false, score);
            gameGui.dispose();
        }
    }
}
