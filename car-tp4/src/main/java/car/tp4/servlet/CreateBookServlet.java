package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/createBook.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("titleBook");
        String author = request.getParameter("authorBook");
        String date = request.getParameter("dateBook");

        Book book = new Book(title, author, date);
        bookBean.addBook(book);

        request.setAttribute("book", book);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/updateBook.jsp");
        dispatcher.forward(request, response);
    }
}
