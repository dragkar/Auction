package web.controllers;

import db.DAO.UserDao;
import db.POJO.User;
import db.POJO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.services.ProfileService;

import java.util.Map;

@Controller
public class ProfileController {
   // @Autowired
    ProfileService profileService = new ProfileService();
    UserData userData;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public void profile() {

    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profileGet(@SessionAttribute String login, @RequestParam Map<String, String> map) {
        ModelAndView modelAndView = new ModelAndView("profile");

        userData = profileService.getUserInfo(login);
        System.out.println(userData.toString());
        modelAndView.addObject("login",userData.getLogin());
        modelAndView.addObject("firstName",userData.getUserPersonal().getFirst_name());
        modelAndView.addObject("secondName",userData.getUserPersonal().getSecond_name());
        modelAndView.addObject("lastName",userData.getUserPersonal().getLast_name());
        modelAndView.addObject("birthday",userData.getUserPersonal().getBirthday());
        modelAndView.addObject("email",userData.getUserPersonal().getEmail());

        if(map.containsKey("update") && map.get("update").equals("update")){
            modelAndView.addObject("type", "text");
        }else{  modelAndView.addObject("type", "hidden");}
       //  modelAndView.addObject("login",userData.getLogin());
//        modelAndView.addObject("firstName",userData.getUserPersonal().getFirst_name());
//        modelAndView.addObject("secondName",userData.getUserPersonal().getSecond_name());
//        modelAndView.addObject("lastName",userData.getUserPersonal().getLast_name());
//        modelAndView.addObject("birthday",userData.getUserPersonal().getBirthday());
//        modelAndView.addObject("email",userData.getUserPersonal().getEmail());

        return modelAndView;
    }

//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public ModelAndView profileUpdate(@SessionAttribute String login, Model ui) {
//        ModelAndView modelAndView = new ModelAndView("profile");
//        modelAndView.addObject("type", "text");
//
//        return modelAndView;
//    }

}
