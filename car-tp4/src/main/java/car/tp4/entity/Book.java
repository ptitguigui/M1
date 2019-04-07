package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String author;
    private String title;
    private String date;

    public Book() {
    }

    public Book(String author, String title, String date) {
        this.author = author;
        this.title = title;
        this.date = date;
    }

    /**
     * Getter of attribute id
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter of attribute author
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter of attribute author
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter of attribute title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter of attribute title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter of attribute date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter of attribute date
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Test if its same stwo books
     *
     * @param o Book
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Book book = (Book) o;

        if (id != book.id)
            return false;
        if (!author.equals(book.author))
            return false;
        return title.equals(book.title);
    }

    /**
     * Return a hashcode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    /**
     * Modify to string method of a book
     *
     * @return
     */
    @Override
    public String toString() {
        return "Book{" + "author='" + author + '\'' + ", title='" + title + '\'' + '}';
    }
}
