package db.daoHibernate;

import db.POJO.Lots;

import java.sql.SQLException;
import java.util.List;

public interface LotsDaoImpl {
    List<Lots> getAll();

    Lots getEntityById(Integer id) throws SQLException;

    boolean updateAllColumns(Lots entity);
    boolean delete(Integer id);
    Integer create(Lots entity);
}
