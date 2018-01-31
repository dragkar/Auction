package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class lotPageController {
    @RequestMapping(value = "/lotPage", method  = RequestMethod.GET )
    public void lotPageGet(Model ui){

    }

    @RequestMapping(value = "/lotPage", method  = RequestMethod.POST )
    public void lotPagePost(){

    }
}
