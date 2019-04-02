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

  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }
}