package web.services;

import db.DAO.UserDao;
import db.DAO.UserDataDao;
import db.POJO.User;
import org.apache.log4j.Logger;

public class SignInServise {
    private static final Logger log = Logger.getLogger(SignInServise.class);
    private UserDataDao userDataDao = new UserDataDao();

    /**
     * Функция авторизации
     * @return  возвращает true, при удачной авторизации
     */
 public boolean Auth(String login, String password){
     return userDataDao.searchByLogin(login, passwordHashCode(password));
 }


    /**
     * Шифровка пароля
     * @param password оригинальный пароль
     * @return возвращает строку password.hashCode() + hashCode(50)
     */
    private String passwordHashCode(String password)
    {
        return String.valueOf((password.hashCode() + new Integer(50).hashCode()));
    }
}
