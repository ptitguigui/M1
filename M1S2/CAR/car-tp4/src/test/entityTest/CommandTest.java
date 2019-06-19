import car.tp4.entity.Book;
import car.tp4.entity.Command;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandTest {
    private Command commmand;

    @Before
    public void setup() {
        commmand = new Command();
    }

    @Test
    public void addBook() {
        Book book = new Book("title", "author", new Date(), 1);
        commmand.addBook(book);
        assertEquals(commmand.getBooks().get(0).getQuantity(), 1);
        assertEquals(commmand.getBooks().get(0).getAuthor(), "author");
        assertEquals(commmand.getBooks().get(0).getTitle(), "title");
    }

    @Test
    public void getId() {
        assertEquals(commmand.getId(), 0);
    }

    @Test
    public void getBooks() {
        Book book = new Book("title", "author", new Date(), 1);
        commmand.addBook(book);
        assertTrue(commmand.getBooks() != null);
    }
}
