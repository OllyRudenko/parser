package epam.olharudenko.utils;

import static epam.olharudenko.utils.Constants.ONE;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * RandomNumberGenerator
 */
public class RandomNumberGenerator {
    public static Integer generateNumber(Integer minimum, Integer maximum) {
        return (int) Math.floor(Math.random() * (maximum - minimum + ONE) + minimum);
    }
}
