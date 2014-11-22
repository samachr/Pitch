import java.awt.*;
import java.util.Random;

/**
 * Created by sam on 11/12/14.
 */
public class GameMap {
    private GameState theGame;
    public enum TileType {
        EMPTY, STAFF, PIANO, NUMBER, WIN, START
    }
    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    private TileType[][] map;
    private boolean[][] visible;
    private Point winLocation;

    public Point getPlayerLocation() {
        return playerLocation;
    }

    private Point playerLocation;
    private int size;

    public int getSize() {
        return size;
    }

    private int scale;

    private int viewx;
    private int viewy;

    private GameMap.Direction moveAttempt;

//    private void squareCollected(TileType type) {
//        switch (type) {
//            case EMPTY:
//                break;
//            case STAFF:
//                squaresCollectedStaff++;
//                System.out.println("Collected a Staff, total Staffs: " + squaresCollectedStaff);
//                theGame.updateScore();
//                break;
//            case PIANO:
//                squaresCollectedPiano++;
//                System.out.println("Collected a Piano, total Piano: " + squaresCollectedPiano);
//                theGame.updateScore();
//                break;
//            case NUMBER:
//                squaresCollectedNumber++;
//                System.out.println("Collected a Number, total Number: " + squaresCollectedNumber);
//                theGame.updateScore();
//                break;
//            case WIN:
//                System.out.println("You won!");
//                theGame.updateScore();
//                break;
//            case START:
//                break;
//        }
//    }

    public void AttemptMovePlayer(GameMap.Direction direction) {
        moveAttempt = direction;
        switch (direction) {
            case RIGHT:
                if(playerLocation.x > 0) {
                    theGame.tryMove(map[playerLocation.x - 1][playerLocation.y]);
                }
                break;
            case LEFT:
                if(playerLocation.x < size-1) {
                  theGame.tryMove(map[playerLocation.x + 1][playerLocation.y]);
                }
                break;
            case UP:
                if(playerLocation.y < size-1) {
                    theGame.tryMove(map[playerLocation.x][playerLocation.y + 1]);
                }
                break;
            case DOWN:
                if(playerLocation.y > 0) {
                    theGame.tryMove(map[playerLocation.x][playerLocation.y - 1]);
                }
                break;
        }
    }

    public void movePlayer() {
        switch (moveAttempt) {
            case RIGHT:
                if(playerLocation.x > 0) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.x--;
                    //squareCollected(map[playerLocation.x][playerLocation.y]);
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case LEFT:
                if(playerLocation.x < size-1) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.x++;
                    //squareCollected(map[playerLocation.x][playerLocation.y]);
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case UP:
                if(playerLocation.y < size-1) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.y++;
                    //squareCollected(map[playerLocation.x][playerLocation.y]);
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case DOWN:
                if(playerLocation.y > 0) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.y--;
                   // squareCollected(map[playerLocation.x][playerLocation.y]);
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
        }
        makeVisibleAroundPlayer();
    }

//    public void setView(int changeInX, int changeInY) {
//        viewx += changeInX/scale;
//        viewy += changeInY/scale;
//    }

    private int distance(int x, int y) {
        int dx = x/size - y/size;
        int dy = x%size - y%size;
        return (int)Math.sqrt(dx*dx + dy*dy);
    }

    public void setViewToPlayer() {
        if (playerLocation.x > size - 4) {
            viewx = size-1;
            //System.out.println("Near the left");
        } else if (playerLocation.x < 3){
            viewx = 4;
            //System.out.println("Near the right");
        } else {
            viewx = (playerLocation.x+2);
        }

        if (playerLocation.y > size - 4) {
            viewy = size-1;
            //System.out.println("Near the top");
        } else if (playerLocation.y < 3){
            viewy = 4;
            //System.out.println("Near the bottom");
        } else {
            viewy = (playerLocation.y+2);
        }
    }

    GameMap(int size, GameState theGame) {
        this.theGame = theGame;
        viewx = 0;
        viewy = 0;
//        squaresCollectedStaff = 0;
//        squaresCollectedPiano = 0;
//        squaresCollectedNumber = 0;
        this.scale = 40;
        this.size = size;
        playerLocation = new Point();
        map = new TileType[size][size];
        visible = new boolean[size][size];

        Random rand = new Random();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                switch (rand.nextInt(4)) {
                    case 0:
                        map[x][y] = TileType.STAFF;
                        break;
                    case 1:
                        map[x][y] = TileType.PIANO;
                        break;
                    case 2:
                        map[x][y] = TileType.NUMBER;
                        break;
                    default:
                        map[x][y] = TileType.EMPTY;
                        break;
                }
//                map[x][y] = (rand.nextInt() % 3 == 0 + 1) ? TileType.STAFF : TileType.PIANO;
                visible[x][y] = false;
            }
        }

        int win = rand.nextInt(size*size-1);
        map[win / size][win % size] = TileType.WIN;

        int start = rand.nextInt(size*size-1);

        //ensure that the start and end are at least half the map apart
        while (distance(win, start) < size/2) {
            start = rand.nextInt(size*size-1);
        }

        map[start / size][start % size] = TileType.START;
        visible[start / size][start % size] = true;

        playerLocation.x = start / size;
        playerLocation.y = start % size;
        setViewToPlayer();
        makeVisibleAroundPlayer();
    }

    private void makeVisibleAroundPlayer() {

        if (playerLocation.x + 1 < size) {
            visible[playerLocation.x+1][playerLocation.y] = true;
        }

        if (playerLocation.x + 1 < size && playerLocation.y + 1 < size) {
            visible[playerLocation.x+1][playerLocation.y + 1] = true;
        }

        if (playerLocation.y + 1 < size) {
            visible[playerLocation.x][playerLocation.y+1] = true;
        }

        if (playerLocation.y + 1 < size && playerLocation.x - 1 >= 0) {
            visible[playerLocation.x-1][playerLocation.y+1] = true;
        }

        if (playerLocation.x - 1 >= 0) {
            visible[playerLocation.x-1][playerLocation.y] = true;
        }

        if (playerLocation.x - 1 >= 0 && playerLocation.y - 1 >= 0) {
            visible[playerLocation.x-1][playerLocation.y - 1] = true;
        }

        if (playerLocation.y - 1 >= 0) {
            visible[playerLocation.x][playerLocation.y-1] = true;
        }

        if (playerLocation.y - 1 >= 0 && playerLocation.x + 1 < size) {
            visible[playerLocation.x+1][playerLocation.y-1] = true;
        }

    }

    void paint(Graphics g) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                g.setColor(Color.GRAY);
                if (visible[x][y]) {
                    g.setColor(Color.black);
                    switch (map[x][y]) {
                        case EMPTY:
                            g.setColor(Color.darkGray);
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            break;
                        case STAFF:
                            g.setColor(Color.blue.darker());
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            g.setColor(Color.lightGray);
                            g.drawString("$", (viewx - x) * scale + scale/2 - 10, (viewy - y) * scale + scale/2 + 2);
                            g.drawLine((viewx - x) * scale+ 10, (viewy - y) * scale + 14, (viewx - x) * scale + 30, (viewy - y) * scale + 14);
                            g.drawLine((viewx - x) * scale+ 10, (viewy - y) * scale + 16, (viewx - x) * scale + 30, (viewy - y) * scale + 16);
                            g.drawLine((viewx - x) * scale+ 10, (viewy - y) * scale + 18, (viewx - x) * scale + 30, (viewy - y) * scale + 18);
                            g.drawLine((viewx - x) * scale+ 10, (viewy - y) * scale + 20, (viewx - x) * scale + 30, (viewy - y) * scale + 20);
                            g.drawLine((viewx - x) * scale+ 10, (viewy - y) * scale + 22, (viewx - x) * scale + 30, (viewy - y) * scale + 22);
                            g.setColor(Color.darkGray);
                            g.drawString("$", (viewx - x) * scale + scale/2 - 10, (viewy - y) * scale + scale/2 + 2);
                            break;
                        case PIANO:
                            g.setColor(Color.green.darker());
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            g.setColor(Color.lightGray);
                            //g.drawLine((viewx - x) * scale + 10, (viewy - y) * scale + 22, (viewx - x) * scale + 30, (viewy - y) * scale + 22);
                            g.fillRect((viewx - x) * scale + 10, (viewy - y) * scale + 14, 20, 10);
                            g.setColor(Color.black.brighter());
                            //g.drawRect((viewx - x) * scale + 10, (viewy - y) * scale + 14, 20, 10);
                            g.drawLine((viewx - x) * scale+ 12, (viewy - y) * scale + 14, (viewx - x) * scale + 12, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 14, (viewy - y) * scale + 14, (viewx - x) * scale + 14, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 18, (viewy - y) * scale + 14, (viewx - x) * scale + 18, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 20, (viewy - y) * scale + 14, (viewx - x) * scale + 20, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 22, (viewy - y) * scale + 14, (viewx - x) * scale + 22, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 26, (viewy - y) * scale + 14, (viewx - x) * scale + 26, (viewy - y) * scale + 19);
                            g.drawLine((viewx - x) * scale+ 28, (viewy - y) * scale + 14, (viewx - x) * scale + 28, (viewy - y) * scale + 19);
                            break;
                        case NUMBER:
                            g.setColor(Color.MAGENTA.darker());
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            g.setColor(Color.lightGray);
                            g.drawString("1", (viewx - x) * scale + scale/2 - 3, (viewy - y) * scale + scale/2 + 2);
                            g.drawString("^", (viewx - x) * scale + scale/2 - 5, (viewy - y) * scale + scale/2 -3);
                            break;
                        case WIN:
                            g.setColor(Color.YELLOW);
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            break;
                        case START:
                            g.setColor(Color.WHITE);
                            g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                            break;
                    }
                } else {
                    g.fillRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
                }

                    g.setColor(Color.black);
                    g.drawRect((viewx - x) * scale, (viewy - y) * scale, scale, scale);
            }
        }
    }

    void paintScaled(Graphics g, int scale) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (visible[x][y]) {
                    switch (map[x][y]) {
                        case EMPTY:
                            g.setColor(Color.darkGray);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                        case STAFF:
                            g.setColor(Color.blue);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                        case PIANO:
                            g.setColor(Color.green);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                        case NUMBER:
                            g.setColor(Color.magenta);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                        case WIN:
                            g.setColor(Color.YELLOW);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                        case START:
                            g.setColor(Color.WHITE);
                            g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                            break;
                    }
                    g.setColor(Color.black.brighter());
                    g.drawRect((size - x) * scale, (size - y) * scale, scale, scale);
                } else {
                    g.setColor(Color.GRAY);
                    g.fillRect((size - x) * scale, (size - y) * scale, scale, scale);
                }
            }
        }
        g.setColor(Color.WHITE);
        g.drawRect((size - playerLocation.x - 2) * scale, (size - playerLocation.y - 2) * scale, scale*5, scale*5);
    }

    void printMaptoConsole() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(map[x][y].ordinal() + " ");
            }
            System.out.println();
        }
    }
}


//         0 1 2

//      0  0 1 2
//      1  3 4 5
//      2  6 7 8