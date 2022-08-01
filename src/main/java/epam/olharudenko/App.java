package epam.olharudenko;

import epam.olharudenko.command.CommandContainer;
import epam.olharudenko.command.ICommand;
import epam.olharudenko.command.impl.CountCommand;
import epam.olharudenko.command.impl.FindMaxCommand;
import epam.olharudenko.parser.IParser;
import epam.olharudenko.parser.impl.TextLineParser;
import epam.olharudenko.utils.CreateCSVFile;
import epam.olharudenko.utils.PositionAndValueCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.THREE;
import static epam.olharudenko.utils.Constants.TWO;
import static epam.olharudenko.utils.Constants.WHITESPACE;

public class App {
    private static final String MAIN_MENU = ">> Choose commands:  myparser file_name.csv action column_name \n" +
            ">> like: myparser users.csv find_max Unit \n" +
            ">> or: myparser users.csv count Name \n" +
            ">> write 'close' for escape";
    private static final String CLOSE = "close";
    private static final Logger logger = LogManager.getLogger(App.class);
    private static Scanner scanner;
    private static CommandContainer commandContainer;
    private static boolean flag;
    private static String commandName;
    private static String paramName;
    private static String filePath;
    private static IParser parser;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        init();
        generateCsvFile();
        System.out.println(MAIN_MENU);

        while (flag) {
            if (active()) {
                try {
                    commandContainer.get(commandName).execute(paramName, filePath, parser);
                } catch (IllegalArgumentException | NullPointerException e) {
                    logger.error(e.getMessage());
                    System.out.println(MAIN_MENU);
                } catch (RuntimeException e){
                    logger.error(e.getMessage());
                    System.out.println(MAIN_MENU);
                }
            }
        }
    }

    private static boolean active() {
        String receivedCommand = scanner.nextLine().trim();

        if (receivedCommand.equals(CLOSE)) {
            flag = false;
            return false;
        } else {
            extractDataFromReceivedCommand(receivedCommand);
            return true;
        }
    }

    private static void init() {
        paramName = "";
        filePath = "";
        scanner = new Scanner(System.in);
        commandContainer = initCommandContainer();
        flag = true;
        parser = new TextLineParser();
    }

    private static CommandContainer initCommandContainer() {
        Map<String, ICommand> commands = new HashMap<>();
        commands.put(CountCommand.NAME, new CountCommand());
        commands.put(FindMaxCommand.NAME, new FindMaxCommand());
        return new CommandContainer(commands);
    }

    private static void extractDataFromReceivedCommand(String receivedCommand) {
        if (Objects.nonNull(receivedCommand)) {
            try {
                commandName = PositionAndValueCalculator.findWord(TWO, receivedCommand, WHITESPACE);
                paramName = PositionAndValueCalculator.findWord(THREE, receivedCommand, WHITESPACE);
                filePath = PositionAndValueCalculator.findWord(ONE, receivedCommand, WHITESPACE);
            } catch (IndexOutOfBoundsException e) {
                logger.error(e.getMessage());
                System.out.println(MAIN_MENU);
                active();
            }
        }
    }

    private static void generateCsvFile() {
        CreateCSVFile createCSVFile = new CreateCSVFile();
        createCSVFile.generateFile();
    }
}