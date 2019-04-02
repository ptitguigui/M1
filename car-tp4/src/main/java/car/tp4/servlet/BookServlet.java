package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	@Override
	public void init() throws ServletException {
		bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("books", bookBean.getAllBooks());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
		dispatcher.forward(request, response);
	}
}
