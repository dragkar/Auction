package web.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Profile extends HttpServlet{
    private static final Logger log = Logger.getLogger(Profile.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/profile.jsp").forward(req,resp);
        } catch (ServletException e) {
            log.error(e.getStackTrace());
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
