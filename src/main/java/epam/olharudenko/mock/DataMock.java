package epam.olharudenko.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * DataMock
 * Class contain data for fill file.csv
 */
public class DataMock {
    List<String> names;
    List<String> titles;
    List<String> descriptions;

    public DataMock() {
        initNames();
        initTitles();
        initDescriptions();
    }

    private void initNames() {
        names = new ArrayList<>(Arrays.asList("Tamara", "Dmytro", "Olexander", "Mykyta", "Ilona", "Tom",
                "Olha", "Ivan", "Serhiy", "Kyril", "Liudmyla", "Svitlana", "Evgeniy", "Anatoliy", "Antonina",
                "Qwentin", "Fedir", "Ivanna", "Tamriko", "Yuliya", "Ihor", "Maksym", "Mihail", "Mariya",
                "Natalia", "Alla", "Linda", "Borys", "Sandra", "Emilya", "Kateryna", "Oksana", "Olesia",
                "Oleksa", "Makar", "Mark", "Bogdan", "Bogdana", "Stiv", "Georgiy", "Ganna", "Rostislav",
                "Adelina", "Hrystyna", "Yana", "Larysa", "Mika", "Semen", "Artem", "Denis", "Karp", "Elizaveta"));
    }

    private void initTitles() {
        titles = new ArrayList<>(Arrays.asList(
                "Student", "QA", "Dev", "Junior", "Lead", "SA", "BA", "Mid"
        ));
    }

    private void initDescriptions() {
        descriptions = new ArrayList<>(Arrays.asList(
                "Resource Development Lab Student",
                "Quality Assurance Engineer Level 1",
                "Software Engineer Level 2",
                "Software Engineer Level 1",
                "Development Team Lead",
                "Solution Architect",
                "Business Analytic",
                "Software Engineer Level 3"
        ));
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }
}
