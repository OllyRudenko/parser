package epam.olharudenko.command.impl;

import epam.olharudenko.command.ICommand;
import epam.olharudenko.parser.IParser;
import epam.olharudenko.parser.impl.TextLineParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.TEST_FILE_PATH;
import static epam.olharudenko.utils.Constants.TEST_WRONG_FILE_PATH;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountCommandTest {
    private final static String TEST_PARAM_NAME = "Name";
    private final static String TEST_PARAM_WRONG_NAME = "Wrong Name";
    private ICommand command;
    private IParser parser;
    private Optional<List<String>> lines;

    private IParser parserMock = mock(TextLineParser.class);

    @BeforeEach
    public void init() {
        command = new CountCommand();
        parser = new TextLineParser();
    }

    @Test
    public void shouldGetExecuteWithWrongPathParam_andThrowRunTimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> command.execute(TEST_PARAM_NAME, TEST_WRONG_FILE_PATH, parser));
    }

    @Test
    public void shouldGetExecuteWithParamNameValueNull_andThrowRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> command.execute(null, TEST_FILE_PATH, parser));
    }

    @Test
    public void shouldGetExecuteWithWrongParamNameValue_andThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(TEST_PARAM_WRONG_NAME, TEST_FILE_PATH, parser));
    }

    @Test
    public void shouldGetExecuteWithMockParser_AndGetVerifyTrue() {
        lines = parser.returnData(TEST_FILE_PATH);
        when(parserMock.returnData(TEST_FILE_PATH)).thenReturn(lines);
        command.execute(TEST_PARAM_NAME, TEST_FILE_PATH, parserMock);

        verify(parserMock, times(ONE)).returnData(TEST_FILE_PATH);
    }
}
