package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.Command;
import car.tp4.entity.CommandBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/basket")
public class BasketBookServlet extends HttpServlet {

    @EJB
    private CommandBean commandBean;

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Command command = (Command) session.getAttribute("command");
        if (command != null) {
            request.setAttribute("books", command.getBooks());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/basketBook.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        Command command = (Command) session.getAttribute("command");
        if (command != null) {
            commandBean.addCommand(command);
        }
        session.removeAttribute("command");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/basketBook.jsp");
        dispatcher.forward(request, response);
    }
}
