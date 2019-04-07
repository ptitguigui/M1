package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class BookBean {

    @PersistenceContext(unitName = "book-pu")
    private EntityManager entityManager;

    public void addBook(Book book) {
        entityManager.persist(book);
    }

    public void updateBook(int id, String title, String author, String date) {
        try {
            Query query = entityManager.createQuery(
                    "UPDATE Book SET title = :myTitle, author = :myAuthor, date = :myDate"
                            + " WHERE id = :myID");
            query.setParameter("myTitle", title);
            query.setParameter("myAuthor", author);
            query.setParameter("myDate", date);
            query.setParameter("myID", id);

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Book getBook(int id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> getAllBooks() {
        Query query = entityManager.createQuery("SELECT m from Book as m");
        return query.getResultList();
    }
}