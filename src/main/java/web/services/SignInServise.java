package web.services;


import db.DAO.UserDataDao;
import db.DAO.UserDataDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class SignInServise {
  //  private static final Logger log = Logger.getLogger(SignInServise.class);
 //   @Autowired
    private UserDataDaoImpl userDataDao = new UserDataDao();

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
