package db.DAO;

import db.POJO.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAOImpl {

    List<User> getAll() throws SQLException;

    User getEntityById(Integer id) throws SQLException;
    boolean updateIsAdminColumns(User entity);
     boolean delete(Integer id);
     boolean create(User entity);
     boolean create(int idUserData);
}
