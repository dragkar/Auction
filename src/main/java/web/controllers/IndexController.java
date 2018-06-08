package web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@SessionAttributes(names = "login")
@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            modelAndView.addObject("login", SecurityContextHolder.getContext().getAuthentication().getName());

        }
//
//        if (map.get("exit") != null && map.get("exit").equals("exit")) {
//            map.entrySet().forEach(System.out::println);
//            System.out.println(ui.containsAttribute("login"));
//
//            System.out.println(ui.containsAttribute("login"));
//        }
        return modelAndView;
    }

    //! ! ! ВАЖНО, запомнить. Тут инфа о том, как удалить атрибут из сессии через спринг
    @RequestMapping(value = "/indexEx", method = RequestMethod.GET)
    public String index(@RequestParam Map<String, String> map, WebRequest request) {

        if (map.get("exit") != null && map.get("exit").equals("exit")) {
            map.entrySet().forEach(System.out::println);
            request.removeAttribute("login", WebRequest.SCOPE_SESSION);
        }
        return "/index";
    }
}
