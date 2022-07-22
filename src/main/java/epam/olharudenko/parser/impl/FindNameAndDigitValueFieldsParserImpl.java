package epam.olharudenko.parser.impl;

import epam.olharudenko.parser.Parser;
import epam.olharudenko.utils.PositionAndValueCalculator;
import epam.olharudenko.utils.TextFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.NAME_TITLE;
import static epam.olharudenko.utils.Constants.WRONG_FIELD;
import static epam.olharudenko.utils.Constants.WRONG_TYPE_OF_FIELD;
import static epam.olharudenko.utils.Constants.ZERO;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * FindNameAndDigitValueFieldsParserImpl
 * Class implements interface Parser
 * parsing file.csv by set params
 */
public class FindNameAndDigitValueFieldsParserImpl implements Parser {
    private String fieldName;
    private String filePath;
    private int valueFieldPoint;
    private int nameFieldPoint;

    /**
     * Constructor
     *
     * @param fieldName - input in command line for parsing file.csv
     * @param filePath  - input in command line path with name by file.csv
     */
    public FindNameAndDigitValueFieldsParserImpl(String fieldName, String filePath) {
        this.fieldName = fieldName;
        this.filePath = filePath;
    }

    /**
     * Method parse file.csv by file path and found max value by field received in param 'fieldName'
     *
     * @return map with result: String - first param 'name', Integer - max value in set field
     */
    @Override
    public Map<String, Integer> returnData() {
        Map<String, Integer> result = new HashMap<>();
        TextFileReader fileReader = new TextFileReader(new File(filePath));
        String firstLine = null;

        int counter = ZERO;
        for (Object line : fileReader) {
            if (counter == ZERO) {
                firstLine = (String) line;
                findPosition(firstLine);
                counter++;
            } else {
                String key = PositionAndValueCalculator.findWord(nameFieldPoint, (String) line, COMA);
                Integer value = setValue((String) line);
                saveToResult(result, key, value);
            }
        }
        return result;
    }

    private boolean checkKey(Map<String, Integer> result, String key, Integer value) {
        return (result.containsKey(key) && result.get(key) < value) || !(result.containsKey(key));
    }

    private void saveToResult(Map<String, Integer> result, String key, Integer value) {
        if (checkKey(result, key, value)) {
            result.put(key, value);
        }
    }

    private void findPosition(String firstLine) {
        nameFieldPoint = PositionAndValueCalculator.findColumnPointerByName(NAME_TITLE, firstLine);
        valueFieldPoint = PositionAndValueCalculator.findColumnPointerByName(fieldName, firstLine);
        if (nameFieldPoint < 0 || valueFieldPoint < 0) {
            throw new IllegalArgumentException(WRONG_FIELD);
        }
    }

    private Integer setValue(String line) {
        try {
            return Integer.valueOf(PositionAndValueCalculator.findWord(valueFieldPoint, line, COMA));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(WRONG_TYPE_OF_FIELD);
        }
    }
}
