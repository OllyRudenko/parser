package epam.olharudenko.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.DESCRIPTION_TITLE;
import static epam.olharudenko.utils.Constants.FIRST_LINE;
import static epam.olharudenko.utils.Constants.FOUR;
import static epam.olharudenko.utils.Constants.NAME_TITLE;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.TEST_COMMAND;
import static epam.olharudenko.utils.Constants.THREE;
import static epam.olharudenko.utils.Constants.UNIT_TITLE;
import static epam.olharudenko.utils.Constants.WHITESPACE;
import static epam.olharudenko.utils.Constants.ZERO;

public class PositionAndValueCalculatorTest {

    @Test
    public void shouldFindColumnPointerByNameAndReturnTrue() {
        Assertions.assertEquals(ZERO, PositionAndValueCalculator.findColumnPointerByName(NAME_TITLE, FIRST_LINE));
        Assertions.assertEquals(ONE, PositionAndValueCalculator.findColumnPointerByName(UNIT_TITLE, FIRST_LINE));
        Assertions.assertEquals(FOUR, PositionAndValueCalculator.findColumnPointerByName(DESCRIPTION_TITLE, FIRST_LINE));
    }

    @Test
    public void shouldFindColumnPointerByNameAndReturnFalse() {
        Assertions.assertFalse(PositionAndValueCalculator.findColumnPointerByName("FalseTitle", FIRST_LINE) >= ZERO);
    }

    @Test
    public void shouldFindWordByPointAndReturnTrue(){
        Assertions.assertEquals(NAME_TITLE, PositionAndValueCalculator.findWord(ZERO, FIRST_LINE, COMA));
        Assertions.assertEquals(UNIT_TITLE, PositionAndValueCalculator.findWord(ONE, FIRST_LINE, COMA));
        Assertions.assertEquals(NAME_TITLE, PositionAndValueCalculator.findWord(THREE, TEST_COMMAND, WHITESPACE));
    }

    @Test
    public void shouldFindWordByPointAndThrowException(){
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> PositionAndValueCalculator.findWord(FOUR, TEST_COMMAND, WHITESPACE));
    }
}
