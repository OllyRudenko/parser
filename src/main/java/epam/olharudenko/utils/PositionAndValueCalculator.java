package epam.olharudenko.utils;

import java.util.Arrays;

import static epam.olharudenko.utils.Constants.COMA;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * PositionAndValueCalculator
 */
public class PositionAndValueCalculator {
    private final static String WRONG_COMMAND = ">> ATTENTION! Wrong command";

    /**
     * method found position by 'name' in text
     *
     * @param name      - parameter whose position in the text is to be found
     * @param firstLine - text, where 'name' position should be found
     * @return int - position by 'name'
     */
    public static int findColumnPointerByName(String name, String firstLine) {
        String[] names = firstLine.split(COMA);
        return Arrays.asList(names).indexOf(name);
    }

    /**
     * method found word-value by 'point' in received text line
     *
     * @param point            - position in text
     * @param receivedTextLine - received text line
     * @param regex            - symbol which split words
     * @return String - word by position
     */
    public static String findWord(Integer point, String receivedTextLine, String regex) {
        String[] words = receivedTextLine.split(regex);
        if (point >= words.length) {
            throw new IndexOutOfBoundsException(WRONG_COMMAND);
        }
        return words[point];
    }
}
