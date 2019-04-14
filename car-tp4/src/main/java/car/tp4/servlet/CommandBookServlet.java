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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            List<Book> books;
            if (session.getAttribute("books") != null) {
                books = (List<Book>) session.getAttribute("books");
                bookBean.updateBook(id, book.getTitle(), book.getAuthor(), book.getDataDate(), book.getQuantity() - 1);
                
                if (isExist(books, id)) {
                    System.out.println("vrai");
                    books.remove(book);
                    //Book addBook = new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), book.getQuantity() + 1);
                    //addBook.setId(id);
                    books.add(book);
                } else {
                    System.out.println("faux");
                    //Book addBook =new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), 1);
                    //addBook.setId(id);
                    books.add(book);
                }
            } else {
                books = new ArrayList<Book>();
                //Book addBook = new Book(book.getTitle(), book.getAuthor(), book.getDataDate(), 1);
                //addBook.setId(id);
                books.add(book);
            }
            session.setAttribute("books", books);
        }
    }

    private boolean isExist(List<Book> books, int id) {
        System.out.println("vrai ID = "+id);
        for (Book book : books) {
            System.out.println(book.getId());
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
