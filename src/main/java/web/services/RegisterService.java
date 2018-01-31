package web.services;


import db.DAO.UserDao;
import db.DAO.UserDataDao;
import db.DAO.UserPersonalDao;
import db.POJO.UserData;
import db.POJO.UserPersonal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//@Component
public class RegisterService {
    private static final Logger log = Logger.getLogger(RegisterService.class);

    private UserDao userDao = new UserDao();
    private UserPersonalDao userPersonalDao = new UserPersonalDao();
    private UserDataDao userDataDao = new UserDataDao();

    private String login;
    private String password;

    private String firstName;
    private String lastName;
    private String secondName;
    private String birthday;
    private String sex;
    private String mail;

    public RegisterService(String login, String password, String firstName, String lastName, String secondName, String birthday, String sex, String mail) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.sex = sex;
        this.mail = mail;
    }

    /**
     * создае нового юзера
     * @return
     * @throws SQLException
     */
    public boolean createdNewUser()  {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date today = Calendar.getInstance().getTime();


        if (login.length() > 0 && firstName.length() > 0 && password.length() > 0 && mail.length() > 0) {
            boolean isCreat = userDao.create(
                    userDataDao.create(
                            new UserData(login, password, df.format(today),
                                    userPersonalDao.create(new UserPersonal(firstName, lastName, secondName, birthday, sex, 0, mail
                                            )
                                    )
                            )
                    )
            );
            if (isCreat) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
