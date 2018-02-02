package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.services.SignInServise;

import java.util.Map;

@Controller
@SessionAttributes(names = "login")

public class SignInController {
  //  private static final Logger log = Logger.getLogger(SignIn.class);
    private SignInServise signInServise = new SignInServise();

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String SignInPost(Model ui, @RequestParam Map<String, String> map) {


        map.entrySet().forEach(System.out::println);


        if (signInServise.Auth(map.get("login"), map.get("password") )) {
            ui.addAttribute("login", map.get("login"));
          //  modelAndView.setViewName("/index");

            return "/index";

        } else {
            String message = "Введена фигня, попробуй еще раз или свали !!! ";
            ui.addAttribute("message", message);
          // modelAndView.setViewName("/signin");
             //   modelAndView.addObject("message", message);
            return "/signin";
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public void SignInGet() {

    }
}
