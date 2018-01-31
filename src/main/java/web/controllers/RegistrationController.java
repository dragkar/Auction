package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.services.RegisterService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@SessionAttributes(names = "login")
public class RegistrationController {

    private static RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(Model ui, @RequestParam Map<String, String> map){

        String login = map.get("userLogin");
        String password = map.get("userPassword");

        String firstName = map.get("user_name");
        String lastName = map.get("user_surname");
        String secondName = map.get("userSecond");
        String birthday = map.get("birthday");
        String gender = map.get("gender");
        String mail = map.get("email");
        System.out.println(login + " " + password + " " + firstName + " " + lastName + " " + secondName + " " + birthday + " " + gender);
        String sex = "";

        if (gender.equals("male") || gender.equals("female")) {
            sex = String.valueOf(gender.charAt(0));
        }
        registerService = new RegisterService(login, password, firstName, lastName, secondName, birthday, sex, mail);
        String message = null;

        if (registerService.createdNewUser()) {
               ui.addAttribute("login", login);
           message = "Регистрация прошла успешно. Добро пожаловать в наши ряды!!!";

        } else {
            message = "Введена фигня, попробуй еще раз или свали !!! ";
      }
        ui.addAttribute("message", message);
//
//        try {
//            req.getRequestDispatcher("WEB-INF/pages/register.jsp?message=" + message).forward(req, resp);
//        } catch (ServletException e) {
//            log.error(e.getStackTrace());
//        } catch (IOException e) {
//            log.error(e.getStackTrace());
//        }
return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGet(){

    }
}
