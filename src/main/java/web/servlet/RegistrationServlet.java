package web.servlet;

import web.services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Servlet created register page
 */
public class RegistrationServlet extends HttpServlet {
   // private static final Logger log = Logger.getLogger(RegistrationServlet.class);
    private static RegisterService registerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("CP1251");
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        resp.setCharacterEncoding("CP1251");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
        //    log.error(e.getStackTrace());
        }


        String login = req.getParameter("userLogin");
        String password = req.getParameter("userPassword");

        String firstName = req.getParameter("user_name");
        String lastName = req.getParameter("user_surname");
        String secondName = req.getParameter("userSecond");
        String birthday = req.getParameter("birthday");
        String gender = req.getParameter("gender");
        String mail = req.getParameter("email");
        System.out.println(login + " " + password + " " + firstName + " " + lastName + " " + secondName + " " + birthday + " " + gender);
        String sex = "";

        if (gender.equals("male") || gender.equals("female")) {
            sex = String.valueOf(gender.charAt(0));
        }
        registerService = new RegisterService(login, password, firstName, lastName, secondName, birthday, sex, mail);
        String message = null;

            if (registerService.createdNewUser()) {
                req.getSession().setAttribute("login", login);
                message = "Регистрация прошла успешно. Добро пожаловать в наши ряды!!!";
            } else {
                message = "Введена фигня, попробуй еще раз или свали !!! ";
            }

        try {
            req.getRequestDispatcher("WEB-INF/pages/register.jsp?message=" + message).forward(req, resp);
        } catch (ServletException e) {
       //     log.error(e.getStackTrace());
        } catch (IOException e) {
         //   log.error(e.getStackTrace());
        }
    }
}
