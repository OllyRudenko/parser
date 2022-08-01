package epam.olharudenko.parser;

import java.util.List;
import java.util.Optional;

public interface IParser {
    Optional<List<String>> returnData(String filePath);
}
