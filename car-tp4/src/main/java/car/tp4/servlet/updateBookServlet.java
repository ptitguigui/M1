package car.tp4.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

@WebServlet("/updateBook")
public class updateBookServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookBean.getBook(id);

        request.setAttribute("book", book);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/updateBook.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("titleBook");
        String author = request.getParameter("authorBook");
        String dateInString = request.getParameter("dateBook");

        int id = Integer.parseInt(request.getParameter("id"));
        Date date = bookBean.convertDate(dateInString);
        bookBean.updateBook(id, title, author, date);

        Book book = bookBean.getBook(id);
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/updateBook.jsp");
        dispatcher.forward(request, response);
    }
}
