package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        request.setAttribute("titleBook", request.getParameter("titleBook"));
        request.setAttribute("authorBook", request.getParameter("authorBook"));
        request.setAttribute("dateBook", request.getParameter("dateBook"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/updateBook.jsp");
        dispatcher.forward(request, response);
    }
}
