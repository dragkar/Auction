package db.DAO;

import db.POJO.User;
import db.POJO.UserData;
import db.POJO.UserPersonal;
import db.connection.ConnectionManager;
import db.connection.ConnectionManagerPostgeImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDAOImpl {
    private static final Logger log = Logger.getLogger(UserDao.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerPostgeImpl.getInstance();

    @Override
    public List<User> getAll()  {
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(
                "SELECT cl.id, cl.admin, " +
                        "ud.id AS user_data_id, ud.login, ud.password, ud.date_reg," +
                        " up.id AS user_personal_id, up.first_name, up.second_name," +
                        "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email " +
                        "FROM public.user cl " +
                        "JOIN public.user_data ud ON cl.id_user_data = ud.id" +
                        " JOIN public.user_personal up ON up.id = ud.id_personal"
        );



        while (resultSet.next()) {
            UserPersonal userPersonal = new UserPersonal(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getObject("birthday").toString(),
                    resultSet.getString("sex"),
                    resultSet.getDouble("personal_account"),
                    resultSet.getString("id_monitored_lots"),
                    resultSet.getString("email")
            );
            UserData userData = new UserData(
                    resultSet.getInt("user_data_id"),
                    userPersonal,
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getObject("date_reg").toString()
            );
            User user = new User(
                    resultSet.getInt("id"),
                    userData,
                    resultSet.getBoolean("admin")
            );
            users.add(user);
        }
        connection.close();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return users;
    }

    @Override
    public User getEntityById(Integer id) {
        User user = null;
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(
                "SELECT cl.id, cl.admin, " +
                        "ud.id AS user_data_id, ud.login, ud.password, ud.date_reg," +
                        " up.id AS user_personal_id, up.first_name, up.second_name," +
                        "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email  " +
                        "FROM public.user cl " +
                        "JOIN public.user_data ud ON cl.id_user_data = ud.id" +
                        " JOIN public.user_personal up ON up.id = ud.id_personal " +
                        "WHERE cl.id = " + id
        );

        while (resultSet.next()) {
            UserPersonal userPersonal = new UserPersonal(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getObject("birthday").toString(),
                    resultSet.getString("sex"),
                    resultSet.getDouble("personal_account"),
                    resultSet.getString("id_monitored_lots"),
                    resultSet.getString("email")
            );
            UserData userData = new UserData(
                    resultSet.getInt("user_data_id"),
                    userPersonal,
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getObject("date_reg").toString()
            );
            user = new User(
                    resultSet.getInt("id"),
                    userData,
                    resultSet.getBoolean("admin")
            );
        }
        connection.close();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return user;
    }

    @Override
    public boolean updateIsAdminColumns(User entity) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE  public.user " +
                    " SET admin = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setBoolean(1, entity.getisAdmin());
            preparedStmt.setInt(2, entity.getId());
            int result = preparedStmt.executeUpdate();
            if (result > 0) return true;
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        try (Connection connection = connectionManager.getConnection()) {
            String query = "DELETE FROM public.user " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            int result = preparedStmt.executeUpdate();
            if (result > 0) return true;
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return false;
    }

    /**
     * добавление нового пользователь в базу со всеми возможными данными
     *
     * @param entity входные данные нового пользователя(User, UserData, UserPersonal)
     * @return возвращает true, при удачном добавлении и false при неудачном
     */
    @Override
    public boolean create(User entity) {

        UserDataDao userDataDao = new UserDataDao();
        UserPersonalDao userPersonalDao = new UserPersonalDao();
        System.out.println(entity.getUserData().getLogin());
        int idNewUserData = 0;
        int idNewUserPersonal = 0;
        if (!userDataDao.searchByLogin(entity.getUserData().getLogin())) {
             idNewUserPersonal = userPersonalDao.create(entity.getUserData().getUserPersonal());
            if (idNewUserPersonal == 0) {

                return false;
            }
            entity.getUserData().setId_personal(idNewUserPersonal);
             idNewUserData = userDataDao.create(entity.getUserData());
            if (idNewUserData == 0) {
                userPersonalDao.delete(idNewUserPersonal);
                return false;
            }
            try (Connection connection = connectionManager.getConnection()) {
                String query = "INSERT INTO public.user " +
                        "(id_user_data) " +
                        "VALUES(?) RETURNING id";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, idNewUserData);
                ResultSet resultSet = preparedStmt.executeQuery();
                resultSet.next();
                int result = 0;
                result = resultSet.getInt("id");
                if (result > 0) {
                    System.out.println("new user created successfully ");
                    return true;
                } else {
                    userDataDao.delete(idNewUserData);
                    userPersonalDao.delete(idNewUserPersonal);
                }
            } catch (SQLException e) {
                log.error(e.getStackTrace());
                userDataDao.delete(idNewUserData);
                userPersonalDao.delete(idNewUserPersonal);
            }
        } else {
            System.out.println("new user not created");
            userDataDao.delete(idNewUserData);
            userPersonalDao.delete(idNewUserPersonal);
        }
        return false;
    }


    @Override
    public boolean create( int idUserData) {

            try (Connection connection = connectionManager.getConnection()) {
                String query = "INSERT INTO public.user " +
                        "(id_user_data) " +
                        "VALUES(?) RETURNING id";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, idUserData);
                ResultSet resultSet = preparedStmt.executeQuery();
                resultSet.next();
                int result = 0;
                result = resultSet.getInt("id");
                if (result > 0) {
                    System.out.println("new user created successfully ");
                    return true;
                }
            } catch (SQLException e) {
                log.error(e.getStackTrace());
            }
        return false;
    }
}