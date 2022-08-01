package epam.olharudenko.command;

import epam.olharudenko.parser.IParser;

public interface ICommand {
    void execute(String paramName, String filePath, IParser parser);
}
