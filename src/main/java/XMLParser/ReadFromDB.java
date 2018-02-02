package XMLParser;

import db.DAO.UserDAOImpl;
import db.DAO.UserDao;
import db.POJO.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.SQLException;

public class ReadFromDB {
  //  private static final Logger log = Logger.getLogger(ReadFromDB.class);
    protected static void UsersParserToXML()  {
        //XML Parser для всех пользователей
        UserDAOImpl userDAO = new UserDao();
        File file1 = new File("all_users.xml");
        JAXBContext context1;
        Marshaller marshaller1 = null;
        try {
            context1 = JAXBContext.newInstance(User.UsersWrapper.class);
        marshaller1 = context1.createMarshaller();
        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
         //   log.error(e.getStackTrace());
        }

        User.UsersWrapper users = new User.UsersWrapper();
        try {
            for (User user : userDAO.getAll()) {
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
         //   log.error(e.getStackTrace());
        }
        try {
            marshaller1.marshal(users, file1);
        } catch (JAXBException e) {
         //   log.error(e.getStackTrace());
        }
    }

    public static void main(String[] args) throws SQLException, JAXBException {
        //From UserPersonal and UserData to XML
        UsersParserToXML();
    }
}
