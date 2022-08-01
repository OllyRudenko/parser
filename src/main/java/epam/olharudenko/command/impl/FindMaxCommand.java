package epam.olharudenko.command.impl;

import epam.olharudenko.command.ICommand;
import epam.olharudenko.parser.IParser;
import epam.olharudenko.utils.PositionAndValueCalculator;
import epam.olharudenko.utils.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.DEFAULT_VALUE;
import static epam.olharudenko.utils.Constants.FILE_NOT_FOUND;
import static epam.olharudenko.utils.Constants.NAME_TITLE;
import static epam.olharudenko.utils.Constants.ONE_PARAM_IS_NULL;
import static epam.olharudenko.utils.Constants.START_VALUE;
import static epam.olharudenko.utils.Constants.WRONG_FIELD;
import static epam.olharudenko.utils.Constants.WRONG_TYPE_OF_FIELD;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * FindMaxCommand -
 * implemented interface Command
 * method execute() finds the maximum value of the specified field and its owner
 */
public class FindMaxCommand implements ICommand {
    private final Logger logger = LogManager.getLogger(FindMaxCommand.class);
    public final static String NAME = "find_max";

    /**
     * Method call parser file.csv, take data and found max value by field received in param 'paramName'
     *
     * @param paramName - name by column for further actions
     * @param filePath  - name and path of file to parsing
     * @param parser    - parser for work with text file
     */
    @Override
    public void execute(String paramName, String filePath, IParser parser) {
        try {
            checkParams(paramName, filePath, parser);
        } catch (NullPointerException | NoSuchFileException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        Integer valuePoint = DEFAULT_VALUE;
        Integer namePoint = DEFAULT_VALUE;
        Optional<List<String>> lines = parser.returnData(filePath);
        Map<String, Integer> result = new HashMap<>();
        if (lines.isPresent()) {
            String firstLine = null;

            int counter = START_VALUE;
            for (String line : lines.get()) {
                if (counter == START_VALUE) {
                    firstLine = line;
                    valuePoint = findValuePosition(firstLine, paramName);
                    namePoint = findNamePosition(firstLine, NAME_TITLE);
                    counter++;
                } else {
                    String key = PositionAndValueCalculator.findWord(namePoint, line, COMA);
                    Integer value = setValue(line, valuePoint);
                    saveToResult(result, key, value);
                }
            }
        }
        Printer.printResult(findMax(result), NAME_TITLE, paramName);
    }

    private Map<String, Integer> findMax(Map<String, Integer> result) {
        Map<String, Integer> maxValues = new HashMap<>();
        int maxValue = findMaxValue(result);
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (entry.getValue() == maxValue) {
                maxValues.put(entry.getKey(), entry.getValue());
            }
        }
        return maxValues;
    }

    private Integer findMaxValue(Map<String, Integer> result) {
        int maxValue = 0;
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }
        return maxValue;
    }

    private boolean checkKey(Map<String, Integer> result, String key, Integer value) {
        return (result.containsKey(key) && result.get(key) < value) || !(result.containsKey(key));
    }

    private void saveToResult(Map<String, Integer> result, String key, Integer value) {
        if (checkKey(result, key, value)) {
            result.put(key, value);
        }
    }

    private Integer findNamePosition(String firstLine, String fieldName) {
        Integer namePoint = PositionAndValueCalculator.findColumnPointerByName(NAME_TITLE, firstLine);
        if (namePoint < START_VALUE) {
            throw new IllegalArgumentException(WRONG_FIELD);
        }
        return namePoint;
    }

    private Integer findValuePosition(String firstLine, String fieldName) {
        Integer valuePoint = PositionAndValueCalculator.findColumnPointerByName(fieldName, firstLine);
        if (valuePoint < START_VALUE) {
            throw new IllegalArgumentException(WRONG_FIELD);
        }
        return valuePoint;
    }

    private Integer setValue(String line, Integer valuePoint) {
        try {
            return Integer.valueOf(PositionAndValueCalculator.findWord(valuePoint, line, COMA));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(WRONG_TYPE_OF_FIELD);
        }
    }

    private boolean checkParams(String paramName, String filePath, IParser parser) throws NoSuchFileException {
        if (Objects.isNull(paramName) || Objects.isNull(filePath) || Objects.isNull(parser)) {
            throw new NullPointerException(ONE_PARAM_IS_NULL);
        }
        if (!Files.exists(Paths.get(filePath))) {
            throw new RuntimeException(FILE_NOT_FOUND);
        }
        return true;
    }
}
