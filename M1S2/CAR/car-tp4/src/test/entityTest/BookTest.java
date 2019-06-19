import car.tp4.entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BookTest {
    private Book book;

    @Before
    public void setup() {
        book = new Book("title", "author", new Date(), 1);
    }

    @Test
    public void getId() {
        assertEquals(book.getId(), 0);
    }

    @Test
    public void getTitle() {
        assertEquals(book.getTitle(), "title");
    }


    @Test
    public void getAuthor() {
        assertEquals(book.getAuthor(), "author");
    }


    @Test
    public void getQuantity() {
        assertEquals(book.getQuantity(), 1);
    }
}
