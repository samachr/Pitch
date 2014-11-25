import java.awt.event.KeyEvent;

/**
* Created by sam on 11/21/14.
*/
public class GameState {
    private int squaresCollectedPiano;
    private int squaresCollectedStaff;
    private int squaresCollectedNumber;
    private int score;
    protected boolean querying;
    protected String scale[] = Music.GetMajorScale("G");
    public void setInfoPanel(InformationPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public void setQueryPanel(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;
    }

    public void setMapPanel(MapPanel theMap) {
        this.mapPanel = theMap;
    }
    private MapPanel mapPanel;

    private QueryPanel queryPanel;
    private InformationPanel infoPanel;

    public GameState() {
        this.squaresCollectedPiano = 0;
        this.squaresCollectedStaff = 0;
        this.squaresCollectedNumber = 0;
        this.score = 0;
        this.querying = false;
    }

//    public GameState(InformationPanel infoPanel) {
//        this.infoPanel = infoPanel;
//        this.squaresCollectedPiano = 0;
//        this.squaresCollectedStaff = 0;
//        this.squaresCollectedNumber = 0;
//    }


    public void tryMove(GameMap.TileType type) {
        if (type == GameMap.TileType.EMPTY) {
            Move(GameMap.TileType.EMPTY);
        } else {
            queryPanel.setQuery(type);
            querying = true;
        }
        //queryPanel.requestFocus();
    }

    public void Move(GameMap.TileType type) {
        switch(type) {
            case EMPTY:
                break;
            case STAFF:
                squaresCollectedStaff++;
                infoPanel.setStaffCount(squaresCollectedStaff);
                System.out.println("Collected a Staff, total Staffs: " + squaresCollectedStaff);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case PIANO:
                squaresCollectedPiano++;
                infoPanel.setPianoCount(squaresCollectedPiano);
                System.out.println("Collected a Piano, total Piano: " + squaresCollectedPiano);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case NUMBER:
                squaresCollectedNumber++;
                infoPanel.setNumberCount(squaresCollectedNumber);
                System.out.println("Collected a Number, total Number: " + squaresCollectedNumber);
                score+=5;
                infoPanel.setScoreCount(score);
                break;
            case WIN:
                score+=10000;
                infoPanel.setScoreCount(score);
                System.out.println("You won!");
                break;
            case START:
                break;
        }
       mapPanel.gameMap.movePlayer();
       querying = false;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
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
                case 38:
                    queryPanel.queryInput('s');
                    break;
                case 40:
                    queryPanel.queryInput('b');
                    break;
                case 27: //escape
                    querying = false;
                    queryPanel.setQuery(GameMap.TileType.EMPTY);
                    break;
                case 8: //backspace
                    queryPanel.queryInput('B'); //B for backspace this is sloppy, but it works
                    break;
                case 65:
                    //break;
                case 66:
                    //break;
                case 67:
                    //break;
                case 68:
                    //break;
                case 69:
                    //break;
                case 70:
                    //break;
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
}
