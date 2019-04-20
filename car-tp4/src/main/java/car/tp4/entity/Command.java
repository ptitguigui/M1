package car.tp4.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private List<Book> books;

    public Command() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        int index;
        if ((index = isExist(this.getBooks(), (int) book.getId())) >= 0) {
            this.getBooks().get(index).setQuantity(this.getBooks().get(index).getQuantity() + 1);
        } else {
            Book addBook = new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), 1);
            addBook.setId(book.getId());
            books.add(addBook);
        }
    }

    private int isExist(List<Book> books, int id) {
        int cpt = 0;
        for (Book book : books) {
            if (book.getId() == id) {
                return cpt;
            }
            cpt++;
        }
        return -1;
    }

    /**
     * Return id of Command
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter of books
     *
     * @return list of Book
     */
    public List<Book> getBooks() {
        return books;
    }

}
