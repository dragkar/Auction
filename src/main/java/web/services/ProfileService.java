package web.services;

import db.DAO.UserDao;
import db.DAO.UserDataDao;
import db.DAO.UserPersonalDao;
import db.POJO.User;
import db.POJO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Класс обрабатывает запрос для отображения личной информации
 *
 * @author Grigoriy Zhuganov
 *@version 1.0
 */
@Service("profileService")
public class ProfileService {
 //@Autowired
    UserDataDao userDataDao = new UserDataDao();
    public UserData getUserInfo(String login){
        UserData userData = userDataDao.getByLogin(login);
   return userData;
    }
}
