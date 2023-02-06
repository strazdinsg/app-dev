package no.ntnu;

import java.util.LinkedList;
import java.util.List;

/**
 * A helper utility class
 */
public class Converter {
    /**
     * Convert Iterable to List
     * @param iterable An Iterable collection
     * @return List of the same type
     */
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> list = new LinkedList<>();
        iterable.forEach(list::add);
        return list;
    }
}
