package db.DAO;

import db.POJO.Lots;
import db.POJO.UserPersonal;

import java.sql.SQLException;
import java.util.List;

public interface LotsDaoImpl {
    List<Lots> getAll() throws SQLException;

    Lots getEntityById(Integer id) throws SQLException;

    boolean updateAllColumns(Lots entity) throws SQLException;
    boolean delete(Integer id) throws SQLException;
    Integer create(Lots entity) throws SQLException;
}
