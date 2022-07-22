package epam.olharudenko.command.impl;

import epam.olharudenko.command.Command;

import static epam.olharudenko.utils.Constants.CLOSE;
import static epam.olharudenko.utils.Constants.PROGRAM_CLOSED;

/**
 * @author Olha Rudenko
 * @verson 1.0, 20.07.2022
 * CloseCommandImpl -
 * implemented interface Command
 * method execute() help close program
 */
public class CloseCommandImpl implements Command {
    public final static String NAME = CLOSE;

    @Override
    public void execute() {
        System.out.println(PROGRAM_CLOSED);
    }
}
