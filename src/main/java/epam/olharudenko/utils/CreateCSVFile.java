package epam.olharudenko.utils;

import epam.olharudenko.mock.DataMock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static epam.olharudenko.utils.Constants.COMA;
import static epam.olharudenko.utils.Constants.FIFTY;
import static epam.olharudenko.utils.Constants.FILE_NAME;
import static epam.olharudenko.utils.Constants.FIRST_LINE;
import static epam.olharudenko.utils.Constants.NEW_LINE;
import static epam.olharudenko.utils.Constants.ONE;
import static epam.olharudenko.utils.Constants.USER_LIMIT;
import static epam.olharudenko.utils.Constants.ZERO;

public class CreateCSVFile {
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
            for (int i = ZERO; i < USER_LIMIT; i++) {
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
        int generatedTitleDescriptionIndex = RandomNumberGenerator.generateNumber(ZERO, (titleNameRange));
        line.append(dataMock.getNames().get(RandomNumberGenerator.generateNumber(ZERO, (listNameRange))));
        line.append(COMA);
        line.append(RandomNumberGenerator.generateNumber(ZERO, FIFTY));
        line.append(COMA);
        line.append(RandomNumberGenerator.generateNumber(ZERO, FIFTY));
        line.append(COMA);
        line.append(dataMock.getTitles().get(generatedTitleDescriptionIndex));
        line.append(COMA);
        line.append(dataMock.getDescriptions().get(generatedTitleDescriptionIndex));
        line.append(NEW_LINE);
        return line;
    }

    private void initFields(){
        dataMock = new DataMock();
        listNameRange = dataMock.getNames().size() - ONE;
        titleNameRange = dataMock.getTitles().size() - ONE;
    }
}
