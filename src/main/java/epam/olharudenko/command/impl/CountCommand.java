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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.DEFAULT_VALUE;
import static epam.olharudenko.utils.Constants.FILE_NOT_FOUND;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.ONE_PARAM_IS_NULL;
import static epam.olharudenko.utils.Constants.START_VALUE;
import static epam.olharudenko.utils.Constants.WRONG_FIELD;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * CountCommand -
 * implemented interface Command
 * method execute() counts the number of unique fields and outputs in reverse order
 */
public class CountCommand implements ICommand {
    private final Logger logger = LogManager.getLogger(CountCommand.class);
    public final static String NAME = "Count";
    private static final String WRONG_ACTION = ">> ATTENTION! Wrong action";

    /**
     * Method call parser file.csv, take data and count unique values received in param 'paramName'
     *
     * @param paramName - name by column for further actions
     * @param filePath  - name and path of file to parsing
     * @param parser    - parser for work with text file
     */
    @Override
    public void execute(String paramName, String filePath, IParser parser) {
        int fieldNamePoint = DEFAULT_VALUE;
        try {
            checkParams(paramName, filePath, parser);
        } catch (NullPointerException | NoSuchFileException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        Optional<List<String>> lines = parser.returnData(filePath);
        Map<String, Integer> result = new TreeMap<>();

        int counter = START_VALUE;
        for (String line : lines.get()) {
            if (counter == START_VALUE) {
                fieldNamePoint = findPosition(line, paramName);
                counter++;
            } else {
                String key = PositionAndValueCalculator.findWord(fieldNamePoint, line, COMA);
                saveToResult(result, key);
            }
        }

        Map<String, Integer> sorted = sortResult(result);
        Printer.printResult(sorted, paramName, NAME);
    }

    private Map<String, Integer> sortResult(Map<String, Integer> result) {
        LinkedHashMap<String, Integer> sorted = result.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
            throw new IllegalStateException(WRONG_ACTION);
        }, LinkedHashMap::new));
        return sorted;
    }

    private void saveToResult(Map<String, Integer> result, String key) {
        if (result.containsKey(key)) {
            int value = result.get(key);
            result.put(key, ++value);
        } else {
            result.put(key, ONE);
        }
    }

    private Integer findPosition(String firstLine, String fieldName) {
        if (Objects.isNull(fieldName) || Objects.isNull(fieldName)) {
            throw new NullPointerException(ONE_PARAM_IS_NULL);
        }
        int fieldNamePoint = PositionAndValueCalculator.findColumnPointerByName(fieldName, firstLine);
        if (fieldNamePoint < 0) {
            throw new IllegalArgumentException(WRONG_FIELD);
        }
        return fieldNamePoint;
    }

    private boolean checkParams(String paramName, String filePath, IParser parser) throws NoSuchFileException {
        if (Objects.isNull(paramName) || Objects.isNull(filePath) || Objects.isNull(parser)) {
            throw new NullPointerException(ONE_PARAM_IS_NULL);
        }
        if (!Files.exists(Paths.get(filePath))) {
            throw new NoSuchFileException(FILE_NOT_FOUND);
        }
        return true;
    }
}
