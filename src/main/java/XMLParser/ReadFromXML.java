package XMLParser;

import db.POJO.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadFromXML {
  //  private static final Logger log = Logger.getLogger(ReadFromXML.class);
    private static void fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(User.UsersWrapper.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            User.UsersWrapper usersWrapper = (User.UsersWrapper) un.unmarshal(new File(filePath));
            System.out.println("111111111111111111");
            for (User user : usersWrapper.getusers()) {
                System.out.println(user);
            }

        } catch (JAXBException e) {
        //    log.error(e.getStackTrace());
        }

    }

    public static void main(String[] args) {
       fromXmlToObject("all_users.xml");
//        for (double i = 0; i >2 ; i++) {
//            System.out.println(i);
//        }
    }

}
