package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.Command;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/command")
public class CommandBookServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        addBookIntoBasket(request);

        request.setAttribute("books", bookBean.getAllBooksAvalaible());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/commandBook.jsp");
        dispatcher.forward(request, response);
    }

    private void addBookIntoBasket(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Book book = bookBean.getBook(id);
            HttpSession session = request.getSession(true);
            Command command;
            if (session.getAttribute("command") != null) {
                command = (Command) session.getAttribute("command");
            } else {
                command = new Command();
                //command.addBook(new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), 1));
            }
            command.addBook(book);
            bookBean.updateBook(id, book.getTitle(), book.getAuthor(), book.getDataDate(), book.getQuantity() - 1);
            session.setAttribute("command", command);
        }
    }

}
