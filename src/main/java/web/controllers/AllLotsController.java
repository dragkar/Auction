package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.services.AllLotsService;

import java.sql.SQLException;

@Controller
public class AllLotsController {
@Autowired
    private AllLotsService allLotsService;

@RequestMapping(value = "/alllots", method = RequestMethod.GET)
    public void allLotsGet(Model ui) throws SQLException {
  //  allLotsService = new AllLotsService();
    ui.addAttribute("lots",allLotsService.getAllLots());
}

    @RequestMapping(value = "/alllots", method = RequestMethod.POST)
    public void allLotsPost(){

    }
}
