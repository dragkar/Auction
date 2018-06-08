import db.DAO.*;
import db.POJO.Lots;
import db.POJO.User;
import db.POJO.UserData;
import db.POJO.UserPersonal;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        System.out.println(String.valueOf(("admin".hashCode() + new Integer(50).hashCode())));
        UserDAOImpl clientDAO = new UserDao();
        LotsDao lotsDao = new LotsDao();
        UserDataDao userDataDAO = new UserDataDao();
        UserPersonalDao userPersonalDao = new UserPersonalDao();
        System.out.println("=========select all user");

        try {
            for (User client : clientDAO.getAll()) {
                System.out.println(client.toString());
            }

       //     System.out.println("========= update isAdmin user");
         //   User clientUpdate = clientDAO.getEntityById(1);
        //    clientUpdate.setisAdmin(true);
        //    System.out.println(clientDAO.updateIsAdminColumns(clientUpdate));

            System.out.println("========= select by id user");
            User client = clientDAO.getEntityById(1);
            System.out.println(client.toString());

//            System.out.println("=========create user");
//            client.getUserData().setLogin("qwerty");
//            clientDAO.create(client);


            System.out.println("=========select all user data");
            for (UserData userData : userDataDAO.getAll()) {
                System.out.println(userData.toString());
              //  System.out.println(userDataDAO.searchByLogin(userData.getLogin()) + " zzzzzzzzzzzzzzzzzzz");
            }
            System.out.println("========= select by id userdata+++++++++++++++++++");
            UserData userData = userDataDAO.getByLogin("test");
            if(userData!= null)
            System.out.println(userData.toString());
            userData = userDataDAO.getLoginAndPass("test");
            System.out.println(userData.toString());

            System.out.println("=========select all user personal");
            for (UserPersonal userPersonal : userPersonalDao.getAll()) {
                System.out.println(userPersonal.toString());
              //  userPersonal.setFirst_name("gav-");
          //  userPersonalDao.create(userPersonal);

                System.out.println("=========select all lots");
                for (Lots lot : lotsDao.getAll()) {
                    System.out.println(lot.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
