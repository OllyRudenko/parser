package epam.olharudenko.utils;

public interface Constants {
    String MAIN_MENU =  ">> Choose commands:  myparser file_name.csv action column_name \n" +
                        ">> like: myparser users.csv find_max Unit \n" +
                        ">> or: myparser users.csv count Name \n" +
                        ">> write 'close' for escape";
    String FIRST_LINE = "Name,Unit,Prevoius,Title,Description";
    String FILE_NAME = "users.csv";
    String COUNT_TITLE = "Count";
    String NAME_TITLE = "Name";
    String UNIT_TITLE = "Unit";
    String DESCRIPTION_TITLE = "Description";
    String NEW_LINE = "\n";
    String COMA = ",";
    String WHITESPACE = " ";
    String PROGRAM_CLOSED = "Program closed";

    /**
     * Exceptions
     */
    String WRONG_ACTION = ">> ATTENTION! Wrong action";
    String WRONG_COMMAND = ">> ATTENTION! Wrong command";
    String INCORRECT_COMMAND = ">> ATTENTION! Incorrect command";
    String WRONG_FIELD = ">> ATTENTION! Field which you write in command doesn't exist";
    String WRONG_TYPE_OF_FIELD = ">> ATTENTION! For this command you choose field with String-value, please, choose with digits";
    String FILE_NOT_FOUND = ">> ATTENTION! File not found. Wrong path.";

    /**
     * Command names
     */
    String COUNT = "count";
    String FIND_MAX = "find_max";
    String START = "start";
    String CLOSE = "close";

    /**
     * Digits
     */
    Integer ZERO = 0;
    Integer ONE = 1;
    Integer TWO = 2;
    Integer THREE = 3;
    Integer FOUR = 4;
    Integer SEVEN = 7;
    Integer NINE = 9;
    Integer TWENTY = 20;
    Integer FIFTY = 50;
    Integer ONE_HUNDRED = 100;
    Integer USER_LIMIT = 2500000;
    Long BYTES_FOR_MEGABYTE = 1024L * 1024L;

    /**
     * Tests
     */
    String TEST_COMMAND = "myparser users.csv count Name";
    String TEST_FILE_PATH = "src/test/resources/example.csv";
    String TEST_WRONG_FILE_PATH = "wrong.csv";
    String OLHA = "Olha";
    String DMYTRO = "Dmytro";
    String TEST = "Test";
}
