package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit") != null && req.getParameter("exit").equals("exit")) {
            req.getSession().removeAttribute("login");
        }

        req.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(req, resp);
    }

}
