package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping(value = "/index", method  = RequestMethod.GET )
public void index(){
//
//        if (map.get("exit") != null && map.get("exit").equals("exit")) {
//            map.entrySet().forEach(System.out::println);
//            System.out.println(ui.containsAttribute("login"));
//
//            System.out.println(ui.containsAttribute("login"));
//        }
    }
//! ! ! ВАЖНО, запомнить. Тут инфа о том, как удалить атрибут из сессии через спринг
    @RequestMapping(value = "/indexEx", method  = RequestMethod.GET )
    public String index(@RequestParam Map<String, String> map, WebRequest request, SessionStatus status){

        if (map.get("exit") != null && map.get("exit").equals("exit")) {
            map.entrySet().forEach(System.out::println);
          //  System.out.println(ui.containsAttribute("login"));

            status.setComplete();
            request.removeAttribute("login", WebRequest.SCOPE_SESSION);
           // System.out.println(ui.containsAttribute("login"));
        }
    return "/index";
    }
}
