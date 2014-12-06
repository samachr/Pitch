import java.awt.*;
import java.util.Random;

/**
 * Created by sam on 11/12/14
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
    private Point playerLocation;
    
    private int size;
    private int scale;
    private int viewX;
    private int viewY;
    
    private GameMap.Direction moveAttempt;

    public GameMap(int size, GameState theGame) {
        this.theGame = theGame;

        viewX = 0;
        viewY = 0;

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

    public int getSize() {
        return size;
    }

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

    //note: directions are not necessarily accurate here, because drawing routines start
    //at the real origin (bottom left) and swing library and I start at top left. The game functions
    //correctly, just know that if you use this code for anything else... Yep, unexpected.
    public void movePlayer() {
        switch (moveAttempt) {
            case RIGHT:
                if(playerLocation.x > 0) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.x--;
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case LEFT:
                if(playerLocation.x < size-1) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.x++;
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case UP:
                if(playerLocation.y < size-1) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.y++;
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
            case DOWN:
                if(playerLocation.y > 0) {
                    map[playerLocation.x][playerLocation.y] = TileType.EMPTY;
                    playerLocation.y--;
                    map[playerLocation.x][playerLocation.y] = TileType.START;
                    setViewToPlayer();
                }
                break;
        }
        makeVisibleAroundPlayer();
    }

    //used with ensuring that start and end are far enough apart
    private int distance(int x, int y) {
        int dx = x/size - y/size;
        int dy = x%size - y%size;
        return (int)Math.sqrt(dx*dx + dy*dy);
    }

    //makes sure that the viewport always stays the same size. Again, directions are weird because
    //drawing and positioning and map are not all the same xy plane. That's what the comments are for
    public void setViewToPlayer() {
        if (playerLocation.x > size - 4) {
            viewX = size-1;
            //System.out.println("Near the left");
        } else if (playerLocation.x < 3){
            viewX = 4;
            //System.out.println("Near the right");
        } else {
            viewX = (playerLocation.x+2);
        }

        if (playerLocation.y > size - 4) {
            viewY = size-1;
            //System.out.println("Near the top");
        } else if (playerLocation.y < 3){
            viewY = 4;
            //System.out.println("Near the bottom");
        } else {
            viewY = (playerLocation.y+2);
        }
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
                int currentX = (viewX - x) * scale;
                int currentY = (viewY - y) * scale;
                g.setColor(Color.GRAY);
                if (visible[x][y]) {
                    g.setColor(Color.black);
                    switch (map[x][y]) {
                        case EMPTY:
                            g.setColor(Color.darkGray);
                            g.fillRect(currentX, currentY, scale, scale);
                            break;
                        case STAFF:
                            g.setColor(Color.blue.darker());
                            g.fillRect(currentX, currentY, scale, scale);
                            g.setColor(Color.lightGray);
                            g.drawString("$", currentX + scale/2 - 10, currentY + scale/2 + 2);
                            g.drawLine(currentX+ 10, currentY + 14, currentX + 30, currentY + 14);
                            g.drawLine(currentX+ 10, currentY + 16, currentX + 30, currentY + 16);
                            g.drawLine(currentX+ 10, currentY + 18, currentX + 30, currentY + 18);
                            g.drawLine(currentX+ 10, currentY + 20, currentX + 30, currentY + 20);
                            g.drawLine(currentX+ 10, currentY + 22, currentX + 30, currentY + 22);
                            g.setColor(Color.darkGray);
                            g.drawString("$", currentX + scale/2 - 10, currentY + scale/2 + 2);
                            break;
                        case PIANO:
                            g.setColor(Color.green.darker());
                            g.fillRect(currentX, currentY, scale, scale);
                            g.setColor(Color.lightGray);
                            //g.drawLine(currentX + 10, currentY + 22, currentX + 30, currentY + 22);
                            g.fillRect(currentX + 10, currentY + 14, 20, 10);
                            g.setColor(Color.black.brighter());
                            //g.drawRect(currentX + 10, currentY + 14, 20, 10);
                            g.drawLine(currentX+ 12, currentY + 14, currentX + 12, currentY + 19);
                            g.drawLine(currentX+ 14, currentY + 14, currentX + 14, currentY + 19);
                            g.drawLine(currentX+ 18, currentY + 14, currentX + 18, currentY + 19);
                            g.drawLine(currentX+ 20, currentY + 14, currentX + 20, currentY + 19);
                            g.drawLine(currentX+ 22, currentY + 14, currentX + 22, currentY + 19);
                            g.drawLine(currentX+ 26, currentY + 14, currentX + 26, currentY + 19);
                            g.drawLine(currentX+ 28, currentY + 14, currentX + 28, currentY + 19);
                            break;
                        case NUMBER:
                            g.setColor(Color.MAGENTA.darker());
                            g.fillRect(currentX, currentY, scale, scale);
                            g.setColor(Color.lightGray);
                            g.drawString("1", currentX + scale/2 - 3, currentY + scale/2 + 2);
                            g.drawString("^", currentX + scale/2 - 5, currentY + scale/2 -3);
                            break;
                        case WIN:
                            g.setColor(Color.YELLOW);
                            g.fillRect(currentX, currentY, scale, scale);
                            break;
                        case START:
                            g.setColor(Color.WHITE);
                            g.fillRect(currentX, currentY, scale, scale);
                            break;
                    }
                } else {
                    g.fillRect(currentX, currentY, scale, scale);
                }

                    g.setColor(Color.black);
                    g.drawRect(currentX, currentY, scale, scale);
            }
        }
    }

    //for the minimap
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
}