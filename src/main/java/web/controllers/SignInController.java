package web.controllers;

import db.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import web.services.SignInService;

import java.util.Map;

@Controller
//@SessionAttributes(names = "login")

public class SignInController {
//    @Autowired
//    private SignInService signInService;

//    @RequestMapping(value = "/signin", method = RequestMethod.POST)
//    public String SignInPost(Model ui, @RequestParam Map<String, String> map) {
//
//
//        map.entrySet().forEach(System.out::println);
//
//
//        if (signInService.Auth(map.get("login"), map.get("password") )) {
//            ui.addAttribute("login", map.get("login"));
//            return "/index";
//
//        } else {
//            String message = "Введена фигня, попробуй еще раз или свали !!! ";
//            ui.addAttribute("message", message);
//            return "/signin";
//        }
//    }
//
//    @RequestMapping(value = "/signin", method = RequestMethod.GET)
//    public void SignInGet() {
//
//    }
    @RequestMapping(value = "/signin")
    public ModelAndView SignIn() {
        ModelAndView modelAndView = new ModelAndView("signin");
//        User user = (User)SecurityContextHolder.getContext().getAuthentication();
//        if()
//        String message = "Введена фигня, попробуй еще раз или свали !!! ";
//            modelAndView.addObject("message", message);

        return modelAndView;
 }
}
