package db.daoHibernate;

import db.POJO.Lots;
import db.connection.ConnectionManager;
import db.connection.ConnectionManagerPostgeImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Component
public class LotsDao implements LotsDaoImpl {
    private static final Logger log = Logger.getLogger(LotsDao.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerPostgeImpl.getInstance();

    @Override
    public List<Lots> getAll() {
        List<Lots> lots = new ArrayList<>();
        Statement statement = null;
        try (Connection connection = connectionManager.getConnection()) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT lts.id, lts.id_user_owner, lts.name," +
                            "lts.comment, lts.start_price, lts.current_price, lts.current_user," +
                            " lts.added_time, lts.auction_time_in_ms, lts.start_time " +
                            "FROM  public.lots lts "
            );
            while (resultSet.next()) {
                Lots lot = new Lots(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_user_owner"),
                        resultSet.getString("name"),
                        resultSet.getString("comment"),
                        resultSet.getDouble("start_price"),
                        resultSet.getDouble("current_price"),
                        resultSet.getInt("current_user"),
                        resultSet.getBoolean("added_time"),
                        resultSet.getLong("auction_time_in_ms"),
                        resultSet.getLong("start_time")
                );
                lots.add(lot);
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return lots;

    }

    @Override
    public Lots getEntityById(Integer id){
        Lots lots = null;
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(
                "SELECT lts.id, lts.id_user_owner, lts.name," +
                        "lts.comment, lts.start_price, lts.current_price, lts.current_user," +
                        " lts.added_time, lts.auction_time_in_ms, lts.start_time " +
                        "FROM  public.lots lts " +
                        "WHERE lts.id = " + id
        );

        while (resultSet.next()) {
            lots = new Lots(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_user_owner"),
                    resultSet.getString("name"),
                    resultSet.getString("comment"),
                    resultSet.getDouble("start_price"),
                    resultSet.getDouble("current_price"),
                    resultSet.getInt("current_user"),
                    resultSet.getBoolean("added_time"),
                    resultSet.getLong("auction_time_in_ms"),
                    resultSet.getLong("start_time")
            );
        }

        connection.close();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return lots;
    }

    @Override
    public boolean updateAllColumns(Lots entity) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE  public.lots " +
                    " SET name = ?, comment = ?, start_price = ?, current_price = ?, current_user = ?," +
                    " added_time = ?, auction_time_in_ms = ?, start_time = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, entity.getName());
            preparedStmt.setString(2, entity.getComment());
            preparedStmt.setDouble(3, entity.getStartPrice());
            preparedStmt.setDouble(4, entity.getCurrentPrice());
            preparedStmt.setInt(5, entity.getCurrentUserId());
            preparedStmt.setBoolean(6, entity.isAddedTime());
            preparedStmt.setLong(7, entity.getAuctionTimeInMs());
            preparedStmt.setLong(8, entity.getStartTimeInMs());
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
            String query = "DELETE FROM public.lots " +
                    "WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            int result = preparedStmt.executeUpdate();
            if (result > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error delete LotsDao ! ! !");
        }
        return false;
    }

    @Override
    public Integer create(Lots entity) {
        int result = 0;
        try (Connection connection = connectionManager.getConnection()) {
            String query = "INSERT INTO lots " +
                    "(id_user_owner, name, comment, start_price, current_price, " +
                    "auction_time_in_ms, start_time) " +
                    "VALUES(?,?,?,?,?,?,?) RETURNING id";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, entity.getId_user_owner());
            preparedStmt.setString(2, entity.getName());
            preparedStmt.setString(3, entity.getComment());
            preparedStmt.setDouble(4, entity.getStartPrice());
            preparedStmt.setDouble(5, entity.getCurrentPrice());
            preparedStmt.setLong(6, entity.getAuctionTimeInMs());
            preparedStmt.setLong(7, entity.getStartTimeInMs());

            ResultSet resultSet = preparedStmt.executeQuery();
            resultSet.next();
            result = resultSet.getInt("id");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }
}
