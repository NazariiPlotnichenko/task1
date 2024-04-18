package ua.profitsoft.logic;

import ua.profitsoft.model.Book;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StatisticsCalculator {
    public static Map<String, Integer> generateStatistics(List<Book> books, String attribute) {
        Map<String, Integer> attributeCounts = new HashMap<>();

        for (Book book : books) {
            if ("genre".equals(attribute)) {
                for (String genre : book.getGenre()) {
                    attributeCounts.put(genre, attributeCounts.getOrDefault(genre, 0) + 1);
                }
            } else if ("year_published".equals(attribute)) {
                String year = String.valueOf(book.getYearPublished());
                attributeCounts.put(year, attributeCounts.getOrDefault(year, 0) + 1);
            } else if ("author".equals(attribute)) {
                String author = book.getAuthor();
                attributeCounts.put(author, attributeCounts.getOrDefault(author, 0) + 1);
            }
        }
        return sortMapByValueDescending(attributeCounts);
    }

    private static Map<String, Integer> sortMapByValueDescending(Map<String, Integer> statistics) {
        LinkedHashMap<String, Integer> sortedMap = statistics.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortedMap;
    }
}