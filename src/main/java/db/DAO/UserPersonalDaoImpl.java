package db.DAO;

import db.POJO.UserPersonal;

import java.sql.SQLException;
import java.util.List;

public interface UserPersonalDaoImpl {
    List<UserPersonal> getAll();

    UserPersonal getEntityById(Integer id) throws SQLException;

    boolean updateAllColumns(UserPersonal entity);
    boolean delete(Integer id);
    Integer create(UserPersonal entity);
}
