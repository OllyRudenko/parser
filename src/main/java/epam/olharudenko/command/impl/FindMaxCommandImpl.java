package epam.olharudenko.command.impl;

import epam.olharudenko.command.Command;
import epam.olharudenko.parser.Parser;
import epam.olharudenko.parser.impl.FindNameAndDigitValueFieldsParserImpl;
import epam.olharudenko.model.Param;
import epam.olharudenko.utils.Printer;

import java.util.HashMap;
import java.util.Map;

import static epam.olharudenko.utils.Constants.COUNT_TITLE;
import static epam.olharudenko.utils.Constants.FIND_MAX;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * FindMaxCommandImpl -
 * implemented interface Command
 * method execute() finds the maximum value of the specified field and its owner
 */
public class FindMaxCommandImpl implements Command {
    public final static String NAME = FIND_MAX;
    private final Param param;
    private Parser parser;

    public FindMaxCommandImpl(Param param) {
        this.param = param;
    }

    @Override
    public void execute() {
        parser = new FindNameAndDigitValueFieldsParserImpl(param.getParamName(), param.getFilePath());
        Map<String, Integer> result = parser.returnData();
        Printer.printResult(findMax(result), param.getParamName(), COUNT_TITLE);
    }

    private Map findMax(Map<String, Integer> result) {
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
}
