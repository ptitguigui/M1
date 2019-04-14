package car.tp4.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Book> books;

    public Command() {
    }

    public Command(List<Book> books) {
        this.books = books;
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
