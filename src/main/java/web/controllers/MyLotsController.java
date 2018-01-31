package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyLotsController {

    @RequestMapping(value = "/mylots", method = RequestMethod.GET)
    public void mylots(){

    }
}
