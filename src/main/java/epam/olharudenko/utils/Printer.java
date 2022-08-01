package epam.olharudenko.utils;

import java.util.Map;

import static epam.olharudenko.utils.Constants.NEW_LINE;
import static epam.olharudenko.utils.Constants.WHITESPACE;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * Printer
 */
public class Printer {
    private static final Integer WHITESPACE_VALUE = 20;

    /**
     * Method print result from map to console
     *
     * @param result      - map with result of parsing
     * @param firstTitle  - Title word for key from Map result
     * @param secondTitle - Title word for value from Map result
     */
    public static void printResult(Map<String, Integer> result, String firstTitle, String secondTitle) {
        StringBuilder printResult = new StringBuilder();
        printResult.append(firstTitle);
        printResult.append(generateSpace(firstTitle.length()));
        printResult.append(secondTitle);
        printResult.append(NEW_LINE);
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            printResult.append(entry.getKey().concat(generateSpace(entry.getKey().length())));
            printResult.append(entry.getValue());
            printResult.append(NEW_LINE);
        }
        System.out.println(printResult);
    }

    private static String generateSpace(int size) {
        return WHITESPACE.repeat(WHITESPACE_VALUE - size);
    }
}
