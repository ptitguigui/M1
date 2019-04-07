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
        this.books = new ArrayList<Book>();
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
