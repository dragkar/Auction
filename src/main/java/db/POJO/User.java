package db.POJO;

import db.DAO.UserDataDao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlType(propOrder = {"id", "id_user_data","userData", "isAdmin"}, name = "Users Information")

@XmlRootElement(name = "Users")
public class User {

    private UserDataDao userDataDao = new UserDataDao();
    private int id;
    private int id_user_data;
    private UserData userData;
    private boolean isAdmin;

    public User() {
    }

    public User(int id_user_data) {
        if(id_user_data > 0) {
            this.id_user_data = id_user_data;
            this.userData = userDataDao.getEntityById(this.id_user_data);
        }
    }

    public User(int id, int id_user_data, boolean isAdmin) {
        this.id = id;
        this.id_user_data = id_user_data;
        this.isAdmin = isAdmin;
    }

    public User(int id, UserData userData, boolean isAdmin) {
        this.id = id;
        this.userData = userData;
        this.isAdmin = isAdmin;
        if (userData.getId() > 0)
            this.id_user_data = userData.getId();
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public int getId_user_data() {
        return id_user_data;
    }

    public void setId_user_data(int id_user_data) {
        this.id_user_data = id_user_data;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", id_user_data=" + id_user_data +
                ", userData=" + userData +
                ", isAdmin = " + isAdmin +
                '}';
    }
    @XmlElement
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    @XmlElement
    public Boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(boolean admin) {
        this.isAdmin = isAdmin;
    }

    @XmlRootElement
    public static class UsersWrapper {
        @XmlElement
        private List<User> users = new ArrayList<>();

        public List<User> getusers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public void add(User user){
            users.add(user);
        }
    }
}
