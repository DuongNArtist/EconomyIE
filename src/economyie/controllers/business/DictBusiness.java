package economyie.controllers.business;

import economyie.models.DictModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Duong on 17/02/2016.
 */
public class DictBusiness {
    public static final String TABLE = "dict";
    private static Connection connection = null;

    public static ObservableList<DictModel> select(String keyword, int start, int end) {
        ObservableList<DictModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + keyword + "%", String.valueOf(start), String.valueOf(end)};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "select_" + TABLE, params).executeQuery();
            while (resultSet.next()) {
                DictModel model = new DictModel();
                model.setDictId(resultSet.getInt(TABLE + "_id"));
                model.setTagId(resultSet.getInt(TagBusiness.TABLE + "_id"));
                model.setDictValue(resultSet.getString(TABLE + "_value"));
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

    public static int insert(DictModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getTagId()), model.getDictValue()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "insert_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(DictModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getDictId()), String.valueOf(model.getTagId()), model.getDictValue()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "update_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(DictModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getDictId())};
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
