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
public class CommandBean {

    @PersistenceContext(unitName = "command-pu")
    private EntityManager entityManager;

    /**
     * Add a command in persitence
     * @param command
     */
    public void addCommand(Command command) {
        entityManager.persist(command);
    }

    /**
     * Query to get all commands
     * @return
     */
    public List<Command> getAllCommands() {
        Query query = entityManager.createQuery("SELECT m from Command as m");
        return query.getResultList();
    }

}