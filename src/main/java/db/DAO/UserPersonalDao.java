package db.DAO;

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

public class UserPersonalDao implements UserPersonalDaoImpl {
    private static final Logger log = Logger.getLogger(UserPersonalDao.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerPostgeImpl.getInstance();

    @Override
    public List<UserPersonal> getAll() {
        List<UserPersonal> dataUsers = new ArrayList<>();
        Statement statement = null;
        try (Connection connection = connectionManager.getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT up.id, up.first_name, up.second_name," +
                            "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email   " +
                            "FROM  public.user_personal up"
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
                dataUsers.add(userPersonal);
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return dataUsers;
    }

    @Override
    public UserPersonal getEntityById(Integer id) throws SQLException {
        UserPersonal userPersonal = null;
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(
                "SELECT up.id AS user_personal_id, up.first_name, up.second_name," +
                        "up.last_name, up.birthday, up.sex, up. personal_account, up.id_monitored_lots, up.email   " +
                        "FROM  public.user_personal up " +
                        "WHERE up.id = " + id
        );

        while (resultSet.next()) {
             userPersonal = new UserPersonal(
                    resultSet.getInt("user_personal_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getObject("birthday").toString(),
                    resultSet.getString("sex"),
                    resultSet.getDouble("personal_account"),
                    resultSet.getString("id_monitored_lots"),
                     resultSet.getString("email")
            );
        }

        connection.close();

        return userPersonal;
    }

    @Override
    public boolean updateAllColumns(UserPersonal entity) {

        try (Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE  public.user_personal " +
                    " SET first_name = ?, last_name = ?, " +
                    "second_name = ?, birthday = ?, " +
                    "sex = ?, personal_account = ?, id_monitored_lots = ?, email = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, entity.getFirst_name());
            preparedStmt.setString(2, entity.getLast_name());
            preparedStmt.setString(3, entity.getSecond_name());
            preparedStmt.setDate(4, convertStringToDate(entity.getBirthday()));
            preparedStmt.setString(5, entity.getSex());
            preparedStmt.setDouble(6, entity.getPersonal_account());
            preparedStmt.setString(7, entity.getStringListIdMonitoringLots());
            preparedStmt.setString(8, entity.getEmail());
            preparedStmt.setInt(9, entity.getId());

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
            String query = "DELETE FROM public.user_personal " +
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
     * Создает в базе данных информацию о новом пользователе.
     *
     * @param entity - сохраняемый объект UserPersonal
     * @return индекс созданного объекта
     */
    @Override
    public Integer create(UserPersonal entity) {
        int result = 0;
        try (Connection connection = connectionManager.getConnection()) {
            String query = "INSERT INTO user_personal " +
                    "(first_name, last_name, second_name, birthday, sex, email) " +
                    "VALUES(?,?,?,?,?,?) RETURNING id";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, entity.getFirst_name());
            preparedStmt.setString(2, entity.getLast_name());
            preparedStmt.setString(3, entity.getSecond_name());
            preparedStmt.setDate(4, new Date(System.currentTimeMillis()));
            preparedStmt.setString(5, entity.getSex());
            preparedStmt.setString(6, entity.getEmail());

            ResultSet resultSet = preparedStmt.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }

    /**
     * Конвертирует дату из строки в формат Data
     *
     * @param strDate - принимаемая строка
     */
    private Date convertStringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date) format.parse(strDate);
        } catch (ParseException e) {
            log.error(e.getStackTrace());
        }
        return date;
    }
}
