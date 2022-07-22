package epam.olharudenko.parser.impl;

import epam.olharudenko.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static epam.olharudenko.utils.Constants.DMYTRO;
import static epam.olharudenko.utils.Constants.NAME_TITLE;
import static epam.olharudenko.utils.Constants.NINE;
import static epam.olharudenko.utils.Constants.OLHA;
import static epam.olharudenko.utils.Constants.SEVEN;
import static epam.olharudenko.utils.Constants.TEST_FILE_PATH;
import static epam.olharudenko.utils.Constants.THREE;
import static epam.olharudenko.utils.Constants.UNIT_TITLE;

public class FindNameAndDigitValueFieldsParserImplTest {
    private Parser fieldsParser;

    @BeforeEach
    public void init(){
        fieldsParser = new FindNameAndDigitValueFieldsParserImpl(UNIT_TITLE, TEST_FILE_PATH);
    }

    @Test
    public void shouldReturnDataWithTrue(){
        Map<String, Integer> result = fieldsParser.returnData();
        Assertions.assertEquals(NINE, result.size());
        Assertions.assertEquals(SEVEN, result.get(OLHA));
        Assertions.assertEquals(THREE, result.get(DMYTRO));
    }

    @Test
    public void shouldReturnDataWithAskedStringFieldValue_AndThrowNumberFormatException() {
        fieldsParser = new FindNameAndDigitValueFieldsParserImpl(NAME_TITLE, TEST_FILE_PATH);
        Assertions.assertThrows(NumberFormatException.class, () -> fieldsParser.returnData());
    }

}
