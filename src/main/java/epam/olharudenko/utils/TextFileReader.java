package epam.olharudenko.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static epam.olharudenko.utils.Constants.FILE_NOT_FOUND;

/**
 * @author Olha Rudenko
 * @verson 1.0, 20.07.2022
 * TextFileReader -
 * Class methods process text files and iterate line by line
 */
public class TextFileReader implements Iterable {
    private File file;

    public TextFileReader(File file) {
        this.file = file;
    }

    /**
     * The method receives a text file and return Iterator
     *
     * @return Iterator<String>
     */
    @Override
    public Iterator<String> iterator() {
        return new Iterator() {
            Scanner scanner;

            {
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    try {
                        throw new FileNotFoundException(FILE_NOT_FOUND);
                    } catch (FileNotFoundException ex) {
                        System.out.println(FILE_NOT_FOUND);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return scanner.nextLine();
            }
        };
    }
}
