package epam.olharudenko.command.impl;

import epam.olharudenko.command.Command;
import epam.olharudenko.model.Param;
import epam.olharudenko.parser.Parser;
import epam.olharudenko.parser.impl.UniqueFieldCounterParserImpl;
import epam.olharudenko.utils.Printer;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static epam.olharudenko.utils.Constants.COUNT;
import static epam.olharudenko.utils.Constants.COUNT_TITLE;
import static epam.olharudenko.utils.Constants.WRONG_ACTION;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * CountCommandImpl -
 * implemented interface Command
 * method execute() counts the number of unique fields and outputs in reverse order
 */
public class CountCommandImpl implements Command {
    public final static String NAME = COUNT;
    private final Param param;
    private Parser parser;

    public CountCommandImpl(Param param) {
        this.param = param;
    }

    @Override
    public void execute() {
        parser = new UniqueFieldCounterParserImpl(param.getParamName(), param.getFilePath());
        Map<String, Integer> result = parser.returnData();
        Map<String, Integer> sorted = sortResult(result);
        Printer.printResult(sorted, param.getParamName(), COUNT_TITLE);
    }

    private Map<String, Integer> sortResult(Map<String, Integer> result) {
        LinkedHashMap<String, Integer> sorted = result.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                    throw new IllegalStateException(WRONG_ACTION);
                }, LinkedHashMap::new));
        return sorted;
    }

}
