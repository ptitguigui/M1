package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
@Local
public class BookBean {

    @PersistenceContext(unitName = "book-pu")
    private EntityManager entityManager;

    public void addBook(Book book) {
        entityManager.persist(book);
    }

    public void updateBook(int id, String title, String author, Date date) {
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

    public List<Book> getAllBooks(String title) {
        Query query = !title.equals("") ?
                entityManager.createQuery("SELECT m from Book as m where m.title like '" + title + "%' order by m.date") :
                entityManager.createQuery("SELECT m from Book as m order by m.date");
        return query.getResultList();
    }


    public List<String> getAllAuthor(String title) {
        Query query = !title.equals("") ?
                entityManager.createQuery("SELECT distinct m.author from Book as m where m.title like '" + title + "%'") :
                entityManager.createQuery("SELECT distinct m.author from Book as m");

        return query.getResultList();
    }


    public static Date convertDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}