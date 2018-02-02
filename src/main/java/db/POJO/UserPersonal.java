package db.POJO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPersonal {

    private int id;
    private String first_name;
    private String last_name;
    private String second_name;
    private String birthday;
    private String sex;
    //счет
    private double personal_account;
    private String email;
    private List<String> listIdMonitoringLots = new ArrayList<>();

    public UserPersonal() {
    }

    @Override
    public String toString() {
        return "UserPersonal{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", personal_account=" + personal_account +
                ", listIdMonitoringLots=" + listIdMonitoringLots +
                '}';
    }

    public UserPersonal( String first_name, String last_name, String second_name, String birthday, String sex, double personal_account, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.second_name = second_name;
        this.birthday = birthday;
        this.sex = sex;
        this.personal_account = personal_account;
        this.listIdMonitoringLots = listIdMonitoringLots;
        this.email = email;
    }

    public UserPersonal( String first_name, String last_name, String second_name, String birthday, String sex, double personal_account, List<String> listIdMonitoringLots, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.second_name = second_name;
        this.birthday = birthday;
        this.sex = sex;
        this.personal_account = personal_account;
        this.listIdMonitoringLots = listIdMonitoringLots;
        this.email = email;
    }

    public UserPersonal(int id, String first_name, String last_name, String birthday, String sex, Double personal_account, String listIdMonitoringLots, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.sex = sex;
        this.personal_account = personal_account;
        if (listIdMonitoringLots != null &&listIdMonitoringLots.length() > 0)
        this.listIdMonitoringLots = Arrays.asList(listIdMonitoringLots.split(" "));
        this.email = email;
    }

    public String getSecond_name() {
        return second_name;
    }

    public double getPersonal_account() {
        return personal_account;
    }

    public List<String> getListIdMonitoringLots() {
        return listIdMonitoringLots;
    }
    @XmlElement
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }


    public int getId() {
        return id;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
@XmlElement
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    @XmlElement
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthday() {
        return birthday;
    }
    @XmlElement
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }
    @XmlElement
    public void setSex(String sex) {
        this.sex = sex;
    }
    @XmlElement
    public String getStringListIdMonitoringLots(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str:listIdMonitoringLots) {
            stringBuilder.append(str);
            stringBuilder.append(" ");
        }
    return stringBuilder.toString().trim();
    }
}
