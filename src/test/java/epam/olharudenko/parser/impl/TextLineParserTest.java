package epam.olharudenko.parser.impl;

import epam.olharudenko.parser.IParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static epam.olharudenko.utils.Constants.TEST_WRONG_FILE_PATH;
import static epam.olharudenko.utils.Constants.THREE;
import static epam.olharudenko.utils.Constants.TWO;

public class TextLineParserTest {
    private final static String OLHA = "Olha";
    private final static String DMYTRO = "Dmytro";
    private final static Integer DEFAULT_LIST_SIZE = 13;
    private IParser fieldsIParser;
    private final String TEST_FILE_PATH = "src/test/resources/example.csv";

    @BeforeEach
    public void init() {
        fieldsIParser = new TextLineParser();
    }

    @Test
    public void shouldParseTextFileAndReturnData_CheckDataWithTrue() {
        Optional<List<String>> result = fieldsIParser.returnData(TEST_FILE_PATH);
        Assertions.assertEquals(DEFAULT_LIST_SIZE, result.get().size());
        Assertions.assertTrue(result.get().get(TWO).contains(OLHA));
        Assertions.assertTrue(result.get().get(THREE).contains(DMYTRO));
    }

    @Test
    public void shouldGetWrongFilePathAndReturnNullValue() {
        Optional<List<String>> result = null;
        try {
            result = fieldsIParser.returnData(TEST_WRONG_FILE_PATH);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertNull(result);
    }
}
