package epam.olharudenko.parser.impl;

import epam.olharudenko.parser.IParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epam.olharudenko.utils.Constants.NEW_LINE;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * TextLineParser
 * Class implements interface Parser
 * parsing file.csv
 */
public class TextLineParser implements IParser {
    private final static String EXCEPTION_MESSAGE = "file isn't exist or wrong format";

    /**
     * Method take file, parse and return Optional<List<String>> with text-lines
     *
     * @param filePath - name and path of file to parsing
     * @return Optional<List < String>> result
     */
    @Override
    public Optional<List<String>> returnData(String filePath) {
        Optional<List<String>> s = null;
        if (checkParams(filePath)) {
            try (Stream<String> streamFromFiles = Files.lines(Paths.get(filePath))) {
                s = Optional.of(streamFromFiles.flatMap(p -> Arrays.asList(p.split(NEW_LINE)).stream())
                        .collect(Collectors.toList()));
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_MESSAGE);
            }
        }
        return s;
    }

    private boolean checkParams(String filePath) {
        return Objects.nonNull(filePath) && Files.exists(Paths.get(filePath));
    }

}