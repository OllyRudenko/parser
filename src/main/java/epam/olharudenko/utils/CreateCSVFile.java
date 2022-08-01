package epam.olharudenko.utils;

import epam.olharudenko.mock.DataMock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.FILE_NAME;
import static epam.olharudenko.utils.Constants.FIRST_LINE;
import static epam.olharudenko.utils.Constants.NEW_LINE;
import static epam.olharudenko.utils.Constants.ONE;

/**
 * @author Olha Rudenko
 * @verson 1.1, 01.08.2022
 * CreateCSVFile -
 * class create file.csv and generate data for it
 */
public class CreateCSVFile {
    private static final Integer START_RANGE = 0;
    private static final Integer MIN_RANDOM_VALUE = 0;
    private static final Integer MAX_RANDOM_VALUE = 50;
    private static final Integer USER_LIMIT = 2500000;
    private DataMock dataMock;
    private int listNameRange;
    private int titleNameRange;

    public CreateCSVFile() {
        initFields();
    }

    public void generateFile() {
        try (PrintWriter writer = new PrintWriter(new File(FILE_NAME))) {

            StringBuilder sb = new StringBuilder();
            sb.append(FIRST_LINE);
            sb.append(NEW_LINE);
            for (int i = START_RANGE; i < USER_LIMIT; i++) {
                sb.append(generateLine());
            }

            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private StringBuilder generateLine() {
        StringBuilder line = new StringBuilder();
        int generatedTitleDescriptionIndex = RandomNumberGenerator.generateNumber(MIN_RANDOM_VALUE, (titleNameRange));
        line.append(dataMock.getNames().get(RandomNumberGenerator.generateNumber(MIN_RANDOM_VALUE, (listNameRange))));
        line.append(COMA);
        line.append(RandomNumberGenerator.generateNumber(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE));
        line.append(COMA);
        line.append(RandomNumberGenerator.generateNumber(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE));
        line.append(COMA);
        line.append(dataMock.getTitles().get(generatedTitleDescriptionIndex));
        line.append(COMA);
        line.append(dataMock.getDescriptions().get(generatedTitleDescriptionIndex));
        line.append(NEW_LINE);
        return line;
    }

    private void initFields() {
        dataMock = new DataMock();
        listNameRange = dataMock.getNames().size() - ONE;
        titleNameRange = dataMock.getTitles().size() - ONE;
    }
}
