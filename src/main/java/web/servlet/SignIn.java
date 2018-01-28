package web.servlet;

import db.POJO.User;
import org.apache.log4j.Logger;
import web.services.SignInServise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignIn extends HttpServlet {
    private static final Logger log = Logger.getLogger(SignIn.class);
 private SignInServise signInServise = new SignInServise();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/signin.jsp").forward(req,resp);
        } catch (ServletException e) {
            log.error(e.getStackTrace());
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
        if(signInServise.Auth(login,password)){
            req.getSession().setAttribute("login", login);

                req.getRequestDispatcher("/index.jsp").forward(req,resp);

        }else{
            String message = "Введена фигня, попробуй еще раз или свали !!! ";
            req.getRequestDispatcher("/signin.jsp?message=" + message).forward(req, resp);
        }
        } catch (ServletException e) {

            log.error(e.getStackTrace());
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }
}
