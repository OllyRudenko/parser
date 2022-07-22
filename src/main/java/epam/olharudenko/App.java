package epam.olharudenko;

import epam.olharudenko.command.Command;
import epam.olharudenko.command.CommandContainer;
import epam.olharudenko.command.impl.CloseCommandImpl;
import epam.olharudenko.command.impl.CountCommandImpl;
import epam.olharudenko.command.impl.FindMaxCommandImpl;
import epam.olharudenko.command.impl.StartCommandImpl;
import epam.olharudenko.model.Param;
import epam.olharudenko.utils.PositionAndValueCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static epam.olharudenko.utils.Constants.MAIN_MENU;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.THREE;
import static epam.olharudenko.utils.Constants.TWO;
import static epam.olharudenko.utils.Constants.WHITESPACE;

public class App {
    private static Scanner scanner;
    private static CommandContainer commandContainer;
    private static boolean flag;
    private static String commandName;
    private static Param param;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        init();
        while (flag) {
            try {
                commandContainer.get(commandName).execute();
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
                System.out.println(MAIN_MENU);
            }
            active();
        }
    }

    private static void active() {
        String receivedCommand = scanner.nextLine().trim();
        extractDataFromReceivedCommand(receivedCommand);
        if (commandName.equals(CloseCommandImpl.NAME)) {
            commandContainer.get(commandName).execute();
            flag = false;
        }
    }

    private static void init() {
        param = new Param();
        scanner = new Scanner(System.in);
        commandContainer = initCommandContainer();
        flag = true;
        commandName = StartCommandImpl.NAME;
    }

    private static CommandContainer initCommandContainer() {
        Map<String, Command> commands = new HashMap<>();
        commands.put(StartCommandImpl.NAME, new StartCommandImpl());
        commands.put(CloseCommandImpl.NAME, new CloseCommandImpl());
        commands.put(CountCommandImpl.NAME, new CountCommandImpl(param));
        commands.put(FindMaxCommandImpl.NAME, new FindMaxCommandImpl(param));
        return new CommandContainer(commands);
    }

    private static void extractDataFromReceivedCommand(String receivedCommand) {
        if (receivedCommand.equals(StartCommandImpl.NAME) || receivedCommand.equals(CloseCommandImpl.NAME)) {
            commandName = receivedCommand;
        } else {
            try {
                commandName = PositionAndValueCalculator.findWord(TWO, receivedCommand, WHITESPACE);
                param.setParamName(PositionAndValueCalculator.findWord(THREE, receivedCommand, WHITESPACE));
                param.setFilePath(PositionAndValueCalculator.findWord(ONE, receivedCommand, WHITESPACE));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}