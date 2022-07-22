package epam.olharudenko.command.impl;

import epam.olharudenko.command.Command;
import epam.olharudenko.utils.CreateCSVFile;

import static epam.olharudenko.utils.Constants.MAIN_MENU;
import static epam.olharudenko.utils.Constants.START;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * StartCommandImpl -
 * implemented interface Command
 * method execute() run application
 * create and fill file.csv
 */
public class StartCommandImpl implements Command {
    public final static String NAME = START;

    @Override
    public void execute() {
        CreateCSVFile createCSVFile = new CreateCSVFile();
        createCSVFile.generateFile();
        System.out.println(MAIN_MENU);
    }
}
