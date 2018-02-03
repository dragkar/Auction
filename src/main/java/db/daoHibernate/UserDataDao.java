package db.daoHibernate;

import db.POJO.UserData;
import db.POJO.UserPersonal;
import db.connection.ConnectionManager;
import db.connection.ConnectionManagerPostgeImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//@Component
public class UserDataDao implements UserDataDaoImpl {
    private static final Logger log = Logger.getLogger(UserDataDao.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerPostgeImpl.getInstance();

    /**
     * Возвращает содержимое таблицы userdata
     * login, password, UserPersonal
     * @return
     */
    @Override
    public List<UserData> getAll()  {
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        List<UserData> dataUsers = new ArrayList<>();
        try {
            statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(
                "SELECT ud.id AS user_data_id, ud.login, ud.password, ud.date_reg," +
                        " up.id AS user_personal_id, up.first_name, up.second_name," +
                        "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email   " +
                        "FROM public.user_data ud" +
                        " JOIN public.user_personal up ON up.id = ud.id_personal"
        );
        while (resultSet.next()) {
            UserPersonal userPersonal = new UserPersonal(
                    resultSet.getInt("user_personal_id"),
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
            dataUsers.add(userData);
        }
        connection.close();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return dataUsers;
    }

    @Override
    public UserData getEntityById(Integer id) {
        UserData userData = null;
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(
                "SELECT ud.id AS user_data_id, ud.login, ud.password, ud.date_reg," +
                        " up.id AS user_personal_id, up.first_name, up.second_name," +
                        "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email   " +
                        "FROM  public.user_data ud " +
                        " JOIN public.user_personal up ON up.id = ud.id_personal " +
                        "WHERE ud.id = " + id
        );

        while (resultSet.next()) {
            UserPersonal userPersonal = new UserPersonal(
                    resultSet.getInt("user_personal_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getObject("birthday").toString(),
                    resultSet.getString("sex"),
                    resultSet.getDouble("personal_account"),
                    resultSet.getString("id_monitored_lots"),
                    resultSet.getString("email")
            );
            userData = new UserData(
                    resultSet.getInt("user_data_id"),
                    userPersonal,
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getObject("date_reg").toString()
            );
        }

        connection.close();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return userData;
    }

    @Override
    public boolean updateAllColumns(UserData entity) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE  public.user_data " +
                    " SET id_personal = ?, login = ?, password = ?, date_reg = ?, blocking = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, entity.getId_personal());
            preparedStmt.setString(2, entity.getLogin());
            preparedStmt.setString(3, entity.getPassword());
            preparedStmt.setDate(4, convertStringToDate(entity.getDate_reg()));
            preparedStmt.setBoolean(5, entity.isBlocking());
            preparedStmt.setInt(6, entity.getId());

            int result = preparedStmt.executeUpdate();
            if (result > 0) return true;
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        } catch (ParseException e) {
            log.error(e.getStackTrace());
        }
        return false;
    }

    /**
     * Удаление элемента по индексу
     *
     * @param id -индекс удаляемого элемента
     * @return возвращает true, если операция выполнена успешно
     */
    @Override
    public boolean delete(Integer id) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "DELETE FROM public.user_data " +
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

    @Override
    public Integer create(UserData entity) {
        int result = 0;
        if(!searchByLogin(entity.getLogin())){
        try (Connection connection = connectionManager.getConnection()) {

            String query = "INSERT INTO user_data " +
                    "(id_personal, login, password, date_reg) " +
                    "VALUES(?,?,?,?) RETURNING id";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, entity.getId_personal());
            preparedStmt.setString(2, entity.getLogin());
            preparedStmt.setString(3, entity.getPassword());
            preparedStmt.setDate(4, convertStringToDate(entity.getDate_reg()));

            ResultSet resultSet = preparedStmt.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        } catch (ParseException e) {
            log.error(e.getStackTrace());
        }
            return result;
        }
        return result;
    }

    /**
     * Запись объекта в базу с двумя параметрами
     * @param entity - объект типа Userdata без id_personal
     * @param id_personal id объекта UserPersonal
     * @return
     */
    @Override
    public Integer create(UserData entity, int id_personal) {
        int result = 0;
        try (Connection connection = connectionManager.getConnection()) {

            String query = "INSERT INTO user_data " +
                    "(id_personal, login, password, date_reg) " +
                    "VALUES(?,?,?,?,?) RETURNING id";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id_personal);
            preparedStmt.setString(2, entity.getLogin());
            preparedStmt.setString(3, entity.getPassword());
            preparedStmt.setDate(4, convertStringToDate(entity.getDate_reg()));

            ResultSet resultSet = preparedStmt.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        } catch (ParseException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }

    @Override
    public boolean searchByLogin(String login) {

        try(   Connection connection = connectionManager.getConnection();
               Statement statement = connection.createStatement()) {
        ResultSet resultSet = statement.executeQuery(
                " SELECT id" +
                        " FROM public.user_data " +
                        " WHERE login = " + "'"+login+"'" );
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return false;
    }


    @Override
    public boolean searchByLogin(String login, String password) {

        try(   Connection connection = connectionManager.getConnection();
               Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    " SELECT id" +
                            " FROM public.user_data " +
                            " WHERE login = '"+login+"' AND password = '" + password + "'" );
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return false;
    }

    private Date convertStringToDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        java.util.Date date = format.parse(strDate);
        return new Date(date.getTime());
    }
}
