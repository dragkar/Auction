package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.services.AllLotsService;

@Controller
public class AllLotsController {

    private AllLotsService allLotsService;

@RequestMapping(value = "/alllots", method = RequestMethod.GET)
    public void allLotsGet(Model ui){
    allLotsService = new AllLotsService();
    ui.addAttribute("lots",allLotsService.getAllLots());
}

    @RequestMapping(value = "/alllots", method = RequestMethod.POST)
    public void allLotsPost(){

    }
}
