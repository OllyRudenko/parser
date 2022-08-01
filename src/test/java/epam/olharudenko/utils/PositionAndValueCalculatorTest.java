package epam.olharudenko.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.FIRST_LINE;
import static epam.olharudenko.utils.Constants.NAME_TITLE;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.THREE;

import static epam.olharudenko.utils.Constants.WHITESPACE;
import static epam.olharudenko.utils.Constants.START_VALUE;

public class PositionAndValueCalculatorTest {
    private static final String TEST_COMMAND = "myparser users.csv count Name";
    private static final String UNIT_TITLE = "Unit";
    private static final String DESCRIPTION_TITLE = "Description";
    private static final Integer FOUR = 4;

    @Test
    public void shouldFindColumnPointerByNameAndReturnTrue() {
        Assertions.assertEquals(START_VALUE, PositionAndValueCalculator.findColumnPointerByName(NAME_TITLE, FIRST_LINE));
        Assertions.assertEquals(ONE, PositionAndValueCalculator.findColumnPointerByName(UNIT_TITLE, FIRST_LINE));
        Assertions.assertEquals(FOUR, PositionAndValueCalculator.findColumnPointerByName(DESCRIPTION_TITLE, FIRST_LINE));
    }

    @Test
    public void shouldFindColumnPointerByNameAndReturnFalse() {
        Assertions.assertFalse(PositionAndValueCalculator.findColumnPointerByName("FalseTitle", FIRST_LINE) >= START_VALUE);
    }

    @Test
    public void shouldFindWordByPointAndReturnTrue() {
        Assertions.assertEquals(NAME_TITLE, PositionAndValueCalculator.findWord(START_VALUE, FIRST_LINE, COMA));
        Assertions.assertEquals(UNIT_TITLE, PositionAndValueCalculator.findWord(ONE, FIRST_LINE, COMA));
        Assertions.assertEquals(NAME_TITLE, PositionAndValueCalculator.findWord(THREE, TEST_COMMAND, WHITESPACE));
    }

    @Test
    public void shouldFindWordByPointAndThrowException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> PositionAndValueCalculator.findWord(FOUR, TEST_COMMAND, WHITESPACE));
    }
}
