package epam.olharudenko.command;

import java.util.Map;
import java.util.Objects;

import static epam.olharudenko.utils.Constants.INCORRECT_COMMAND;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * CommandContainer create Command collection
 * to call actions in the application
 */
public class CommandContainer {
    private Map<String, Command> commands;

    public CommandContainer() {
    }

    public CommandContainer(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public Command get(String commandName) {
        if (Objects.isNull(commandName) || !commands.containsKey(commandName)) {
            throw new IllegalArgumentException(INCORRECT_COMMAND);
        }
        return commands.get(commandName);
    }
}
