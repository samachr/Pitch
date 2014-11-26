import javax.swing.*;
import java.util.Random;

/**
 * Created by sam on 11/21/14.
 */
public class QueryPanel extends JPanel {
    private Random randomGenerator;
    private int pitchNumber;

    private enum PitchType {
        STAFF, NUMBER, PIANO
    }

    private void generatePitch(PitchType type) {

        int oldPitch = pitchNumber;
        do {
            pitchNumber = randomGenerator.nextInt(6) + 1;
        } while (pitchNumber == oldPitch);

        switch (type) {
            case STAFF:
                staffImage.setImage(randomGenerator.nextBoolean() ? "treble" : "bass", randomGenerator.nextBoolean() ? 2 : 1, theGame.scale[pitchNumber]);
                break;
            case NUMBER:
                scaleNumber.setText(Integer.toString(pitchNumber + 1) + " in " + theGame.scale[0]);
                break;
            case PIANO:
                piano.highlightKey(Music.noteLocationOnKeyboard(theGame.scale[pitchNumber]));
                break;
        }
    }
    public void setQuery(GameMap.TileType query) {
        this.query = query;
        switch(query) {
            case EMPTY:
                queryInstructions.setText("Time is ticking...");
                instructions.setText("Move with the arrow keys");
                staffImage.setVisible(false);
                piano.setVisible(false);
                scaleNumber.setVisible(false);
                break;
            case STAFF:
                generatePitch(PitchType.STAFF);
                queryInstructions.setText("What pitch is this?");
                instructions.setText("letter then modifier (s b n)");
                staffImage.setVisible(true);
                break;
            case PIANO:
                generatePitch(PitchType.PIANO);
                queryInstructions.setText("What pitch is this?");
                instructions.setText("letter then modifier (s b n)");
                piano.setVisible(true);
                break;
            case NUMBER:
                generatePitch(PitchType.NUMBER);
                queryInstructions.setText("What pitch is this?");
                instructions.setText("letter then modifier (s b n)");
                scaleNumber.setVisible(true);
                break;
            case WIN:
                break;
            case START:
                break;
        }
    }

    private String Pitch;
    private GameMap.TileType query;
    private NoteStaff staffImage;
    private PianoPanel piano;
    private GameState theGame;
    private JLabel input;
    private JLabel queryInstructions;
    private JLabel instructions;
    private JLabel scaleNumber;

    public void queryInput(char inputChar) {
        switch (Pitch.length()) {
            case 0:
                if (inputChar == 'B') {
                    theGame.querying = false;
                    this.setQuery(GameMap.TileType.EMPTY);
                } else if (inputChar != 'n' && inputChar != 's') {
                    Pitch += inputChar;
                    Pitch = Pitch.toUpperCase();
                    input.setText(Pitch);
                    if (Pitch.equals(theGame.scale[pitchNumber])) {
                        checkAnswer(Pitch);
                        Pitch = "";
                        input.setText(Pitch);
                    } else if (!Pitch.equals(theGame.scale[pitchNumber].substring(0,1))) {
                        theGame.querying = false;
                        this.setQuery(GameMap.TileType.EMPTY);
                        Pitch = "";
                        input.setText(Pitch);
                    }
                }
                break;
            case 1:
                if (inputChar == 'B') {
                    System.out.println("backspace");
                    Pitch = "";
                    input.setText(Pitch);
                } else if (inputChar == 's') {
                    checkAnswer(Pitch + "#");
                } else if (inputChar == 'b') {
                    checkAnswer(Pitch + inputChar);
                } else if (inputChar == 'n') {
                    checkAnswer(Pitch);
                }
                Pitch = "";
                input.setText(Pitch);
                break;
            }
        }

    private void checkAnswer(String Answer) {
        switch (query) {
            case EMPTY:
                break;
            case STAFF:
            case PIANO:
            case NUMBER:
                if (Answer.equals(theGame.scale[pitchNumber])) {
                    theGame.Move(query);
                    this.setQuery(GameMap.TileType.EMPTY);
                } else {
                    theGame.querying = false;
                    this.setQuery(GameMap.TileType.EMPTY);
                }
                break;
            case WIN:
                break;
            case START:
                break;
        }
    }

    public QueryPanel(GameState theGame) {
        Pitch = "";

        randomGenerator = new Random();

        scaleNumber = new JLabel();
        scaleNumber.setSize(50, 20);
        scaleNumber.setLocation(80, 80);
        this.add(scaleNumber);

        instructions = new JLabel("Move with the arrow keys");
        instructions.setSize(200, 20);
        instructions.setLocation(5, 175);
        this.add(instructions);

        queryInstructions = new JLabel("Time is ticking...");
        queryInstructions.setSize(200, 20);
        queryInstructions.setLocation(5, 5);
        this.add(queryInstructions);

        this.theGame = theGame;
        this.setLayout(null);

        staffImage = new NoteStaff("treble", 2, "C");
        staffImage.setLocation(30, 50);
        staffImage.setSize(91, 52);
        staffImage.setVisible(false);
        this.add(staffImage);

        input = new JLabel();
        input.setSize(150, 20);
        input.setLocation(165, 5);
        this.add(input);

        piano = new PianoPanel(10);
        piano.setLocation(30, 40);
        piano.setSize(141, 100);
        piano.setVisible(false);
        this.add(piano);
        //this.addKeyListener(this);
    }
}
