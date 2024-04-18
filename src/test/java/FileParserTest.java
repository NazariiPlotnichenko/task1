import ua.profitsoft.model.Book;
import ua.profitsoft.utils.FileParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParserTest {
    @Test
    public void testParseBooks() {
        String filePath = "D:/ProfitSoft/task/src/main/resources/bookTest.json"; //Should be added as a value in application.properties

        // given
        List<Book> books = new ArrayList<>();
        FileParser fileParser = new FileParser(new File(filePath), books);

        // when
        fileParser.run();

        // then
        assertEquals(2, books.size());

        Book firstBook = books.get(0);
        assertEquals("1984", firstBook.getTitle());
        assertEquals("George Orwell", firstBook.getAuthor());
        assertEquals(1949, firstBook.getYearPublished());
        assertEquals(Arrays.asList("Dystopian", "Political Fiction"), firstBook.getGenre());

        Book secondBook = books.get(1);
        assertEquals("Pride and Prejudice", secondBook.getTitle());
        assertEquals("Jane Austen", secondBook.getAuthor());
        assertEquals(1813, secondBook.getYearPublished());
        assertEquals(Arrays.asList("Satire", "Romance"), secondBook.getGenre());
    }
}
