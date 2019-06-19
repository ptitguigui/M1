package car.tp4.servlet;

import car.tp4.entity.BookBean;
import car.tp4.entity.CommandBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commands")
public class CommandServlet extends HttpServlet {

    @EJB
    private CommandBean commandBean;

    @Override
    public void init() {    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("commands", commandBean.getAllCommands());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/commands.jsp");
        dispatcher.forward(request, response);
    }

}
