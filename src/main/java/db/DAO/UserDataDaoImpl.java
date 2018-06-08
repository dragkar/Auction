package db.DAO;

import db.POJO.UserData;

import java.sql.SQLException;
import java.util.List;

public interface UserDataDaoImpl {

    List<UserData> getAll();

    UserData getEntityById(Integer id) ;
    boolean updateAllColumns(UserData entity);
    boolean delete(Integer id);
    Integer create(UserData entity);
    Integer create(UserData entity, int id_personal);
    boolean searchByLogin(String login);
    boolean searchByLoginAndPassword(String login, String password);
    UserData getLoginAndPass(String username) throws SQLException;
UserData getByLogin(String login);
    int getIdByName(String username) throws SQLException;
}
