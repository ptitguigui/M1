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
        if (isExist(this.getBooks(), (int) book.getId())) {
            System.out.println("existe");
            books.remove(book);
            Book addBook = new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), book.getQuantity() + 1);
            addBook.setId(book.getId());
            books.add(addBook);
        } else {
            System.out.println("existe pas");
            Book addBook = new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), 1);
            addBook.setId(book.getId());
            books.add(addBook);
        }
    }

    private boolean isExist(List<Book> books, int id) {
        System.out.println("vrai ID = " + id);
        for (Book book : books) {
            System.out.println(book.getId());
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
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
