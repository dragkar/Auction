package db.POJO;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "id_personal", "userPersonal", "login", "password", "date_reg", "blocking"}, name = "Users data")

@XmlRootElement(name = "UserDatas")
public class UserData {

    private int id;
    private int id_personal;
    private UserPersonal userPersonal;
    private String login;
    private String password;
    private String date_reg;
    private boolean blocking;

    public UserData() {
    }

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public UserData(int id, int id_personal, String login, String password, String date_reg, boolean blocking) {
        this.id = id;
        this.id_personal = id_personal;
        this.login = login;
        this.password = passwordHashCode(password);
        this.date_reg = date_reg;
        this.blocking = blocking;
    }

    public UserData(String login, String password, String date_reg, int id_personal) {

        this.login = login;
        this.password = passwordHashCode(password);
        this.date_reg = date_reg;
        this.id_personal = id_personal;
    }

    public UserData(int id, UserPersonal userPersonal, String login, String password, String date_reg) {
        this.id = id;
        if (userPersonal.getId() != 0) {
            this.id_personal = userPersonal.getId();
        }
        this.userPersonal = userPersonal;
        this.login = login;
        this.password = passwordHashCode(password);
        this.date_reg = date_reg;
    }

    /**
     * Шифровка пароля
     * @param password оригинальный пароль
     * @return возвращает строку password.hashCode() + hashCode(50)
     */
    String passwordHashCode(String password)
    {
        return String.valueOf((password.hashCode() + new Integer(50).hashCode()));
     }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", id_personal=" + id_personal +
                ", userPersonal=" + userPersonal +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date_reg='" + date_reg + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public UserPersonal getUserPersonal() {
        return userPersonal;
    }

    public void setUserPersonal(UserPersonal userPersonal) {
        this.userPersonal = userPersonal;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(String date_reg) {
        this.date_reg = date_reg;
    }
}
