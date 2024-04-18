import org.junit.Test;
import ua.profitsoft.logic.StatisticsCalculator;
import ua.profitsoft.model.Book;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsCalculatorTest {
    @Test
    public void testGenerateStatistics() {
        // given
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setYearPublished(2000);
        book1.setGenres("Genre1, Genre2");
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");
        book2.setYearPublished(2005);
        book2.setGenres("Genre2, Genre3");
        books.add(book2);

        // when
        Map<String, Integer> stats = StatisticsCalculator.generateStatistics(books, "genre");

        // then
        assertEquals(Integer.valueOf(2), stats.get("Genre2"));
        assertEquals(Integer.valueOf(1), stats.get("Genre1"));
        assertEquals(Integer.valueOf(1), stats.get("Genre3"));
    }
}