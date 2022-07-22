package epam.olharudenko.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static epam.olharudenko.utils.Constants.BYTES_FOR_MEGABYTE;
import static epam.olharudenko.utils.Constants.FILE_NAME;
import static epam.olharudenko.utils.Constants.ONE_HUNDRED;

public class CreateCSVFileTest {
    private CreateCSVFile createCSVFile;
    private long megabyte;

    @BeforeEach
    public void init() {
        createCSVFile = new CreateCSVFile();
        megabyte = BYTES_FOR_MEGABYTE;
    }

    @Test
    public void shouldCreateCSVFileWithSizeOneHundredMb_CheckSizeAndReturnTrue() throws IOException {
        createCSVFile.generateFile();
        Assertions.assertTrue(Files.size(Path.of(FILE_NAME)) / megabyte >= ONE_HUNDRED);
    }
}
