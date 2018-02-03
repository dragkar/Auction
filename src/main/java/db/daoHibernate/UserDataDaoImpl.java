package db.daoHibernate;

import db.POJO.UserData;

import java.sql.SQLException;
import java.util.List;

public interface UserDataDaoImpl {

    List<UserData> getAll() throws SQLException;

    UserData getEntityById(Integer id) throws SQLException;
    boolean updateAllColumns(UserData entity);
    boolean delete(Integer id);
    Integer create(UserData entity);
    Integer create(UserData entity, int id_personal);
    boolean searchByLogin(String login);
    boolean searchByLogin(String login, String password);
}
