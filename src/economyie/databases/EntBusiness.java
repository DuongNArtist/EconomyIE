package economyie.databases;

import economyie.models.EntModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Duong on 20/02/2016.
 */
public class EntBusiness {
    public static final String TABLE = "ent";
    private static Connection connection = null;

    public static ObservableList<EntModel> select(String keyword, int start, int end) {
        ObservableList<EntModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + keyword + "%", String.valueOf(start), String.valueOf(end)};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "select_" + TABLE, params).executeQuery();
            while (resultSet.next()) {
                economyie.models.EntModel model = new EntModel();
                model.setEntId(resultSet.getInt(TABLE + "_id"));
                model.setEntName(resultSet.getString(TABLE + "_name"));
                model.setEntOwner(resultSet.getString(TABLE + "_owner"));
                model.setEntAddress(resultSet.getString(TABLE + "_address"));
                model.setEntProduct(resultSet.getString(TABLE + "_product"));
                model.setEntProfit(resultSet.getString(TABLE + "_profit"));
                model.setEntExport(resultSet.getString(TABLE + "_export"));
                model.setEntImport(resultSet.getString(TABLE + "_import"));
                models.add(model);
            }
            DatabaseConnector.closeResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return models;
    }

    public static int insert(EntModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getEntName(), model.getEntOwner(), model.getEntAddress(), model.getEntProduct(), model.getEntProfit(), model.getEntExport(), model.getEntImport()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "insert_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(EntModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getEntId()), model.getEntName(), model.getEntOwner(), model.getEntAddress(), model.getEntProduct(), model.getEntProfit(), model.getEntExport(), model.getEntImport()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "update_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(EntModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getEntId())};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "delete_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }
}
