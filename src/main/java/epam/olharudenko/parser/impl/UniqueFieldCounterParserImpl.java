package epam.olharudenko.parser.impl;

import epam.olharudenko.parser.Parser;
import epam.olharudenko.utils.PositionAndValueCalculator;
import epam.olharudenko.utils.TextFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.WRONG_FIELD;
import static epam.olharudenko.utils.Constants.ZERO;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * UniqueFieldCounterParserImpl
 * Class implements interface Parser
 * parsing file.csv by set params
 */
public class UniqueFieldCounterParserImpl implements Parser {
    private String fieldName;
    private String filePath;
    private int fieldNamePoint;

    /**
     * Constructor
     *
     * @param fieldName - input in command line for parsing file.csv
     * @param filePath  - input in command line path with name by file.csv
     */
    public UniqueFieldCounterParserImpl(String fieldName, String filePath) {
        this.fieldName = fieldName;
        this.filePath = filePath;
    }

    /**
     * Method parse file.csv by file path and count unique values received in param 'fieldName'
     *
     * @return map with result: String - first param (value of field), Integer - counted param, how many times it repeats in file
     */
    @Override
    public Map<String, Integer> returnData() {
        Map<String, Integer> result = new HashMap<>();
        TextFileReader fileReader = new TextFileReader(new File(filePath));

        int counter = ZERO;
        for (Object line : fileReader) {
            if (counter == ZERO) {
                findPosition((String) line);
                counter++;
            } else {
                String key = PositionAndValueCalculator.findWord(fieldNamePoint, (String) line, COMA);
                saveToResult(result, key);
            }
        }
        return result;
    }

    private void saveToResult(Map<String, Integer> result, String key) {
        if (result.containsKey(key)) {
            int value = result.get(key);
            result.put(key, ++value);
        } else {
            result.put(key, ONE);
        }
    }

    private void findPosition(String firstLine) {
        fieldNamePoint = PositionAndValueCalculator.findColumnPointerByName(fieldName, firstLine);
        if (fieldNamePoint < 0) {
            throw new IllegalArgumentException(WRONG_FIELD);
        }
    }
}
