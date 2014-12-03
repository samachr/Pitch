/**
 * Created by sam on 11/24/14.
 */
public class Music {
    public static final int[] majorScalePattern = {0,2,4,5,7,9,11};
    //private static int[] whiteKeyList = {0,2,4,5,7,9,11,12,14,16,17,19,21,23};
    //private static int[] blackKeyList = {1,3,6,8,10,13,15,18,20,22};
    private static String[] noteLettersS = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
    private static String[] noteLettersB = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
    private static int[] sharpKeys    = {2,4,6,7,9,11};
    private static int[] flatKeys     = {0,1,3,5,8,10};
    //private static String[] noteNames    = {"C","D","E","F","G","A","B"};

    public static int noteLocationOnKeyboard(String Note) {
        for (int i = 0; i< 12; i++) {
            if (Note.equals(noteLettersS[i])) {
                return i;
            } else if (Note.equals(noteLettersB[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String[] getCommonKeys() {
        String[] result = new String[12];
        for (int i = 0; i < 6; i++) {
            result[i] = noteLettersS[sharpKeys[i]];
            result[i+6] = noteLettersB[flatKeys[i]];
        }
        return result;
    }

    public static String[] GetMajorScale(String key) {
        String theScale[] = new String[7];
        int startNote = -1;
        boolean isSharp = false;
        for (int i = 0; i < 12; i++) {
            if (noteLettersS[i].equals(key)) {
                startNote = i;
                break;
            } else if (noteLettersB[i].equals(key)){
                startNote = i;
                break;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (startNote == sharpKeys[i]) {
                isSharp = true;
            }
        }

        for (int i = 0; i < 7; i++) {
            theScale[i] = (isSharp) ? noteLettersS[startNote + majorScalePattern[i]] : noteLettersB[startNote + majorScalePattern[i]];
        }

        if (key.equals("F#")) {
            theScale[6] = "E#";
        }

        return theScale;
    }
}
