package epam.olharudenko.utils;

public interface Constants {

    String FIRST_LINE = "Name,Unit,Previous,Title,Description";
    String FILE_NAME = "users.csv";
    String NAME_TITLE = "Name";
    String NEW_LINE = "\n";
    String COMA = ",";
    String WHITESPACE = " ";

    /**
     * Exceptions
     */
    String WRONG_FIELD = ">> ATTENTION! Field which you write in command doesn't exist";
    String WRONG_TYPE_OF_FIELD = ">> ATTENTION! For this command you choose field with String-value, please, choose with digits";
    String FILE_NOT_FOUND = ">> ATTENTION! File not found. Wrong path.";
    String ONE_PARAM_IS_NULL = ">> One of method params is null";

    /**
     * Digits
     */
    Integer DEFAULT_VALUE = -1;
    Integer START_VALUE = 0;
    Integer ONE = 1;
    Integer TWO = 2;
    Integer THREE = 3;

    /**
     * Tests
     */
    String TEST_FILE_PATH = "src/test/resources/example.csv";
    String TEST_WRONG_FILE_PATH = "wrong.csv";
}
