package epam.olharudenko.utils;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * RandomNumberGenerator
 */
public class RandomNumberGenerator {
    public static Integer generateNumber(Integer minimum, Integer maximum) {
        return (int) Math.floor(Math.random() * (maximum - minimum + 1) + minimum);
    }
}
