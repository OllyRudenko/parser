package epam.olharudenko.command;

import java.util.Map;
import java.util.Objects;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * CommandContainer create Command collection
 * to call actions in the application
 */
public class CommandContainer {
    private final static String INCORRECT_COMMAND = ">> ATTENTION! Incorrect command";
    private Map<String, ICommand> commands;

    public CommandContainer() {
    }

    public CommandContainer(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }

    public ICommand get(String commandName) {
        if (Objects.isNull(commandName) || !commands.containsKey(commandName)) {
            throw new IllegalArgumentException(INCORRECT_COMMAND);
        }
        return commands.get(commandName);
    }
}
