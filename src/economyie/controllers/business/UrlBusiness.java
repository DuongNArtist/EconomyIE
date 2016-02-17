package economyie.controllers.business;

import economyie.models.UrlModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DuongNArtist on 17/12/2015.
 */
public class UrlBusiness {
    public static final String TABLE = "url";
    private static Connection connection = null;

    public static ObservableList<UrlModel> select(String keyword, int start, int end) {
        ObservableList<UrlModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + keyword + "%", String.valueOf(start), String.valueOf(end)};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "select_" + TABLE, params).executeQuery();
            while (resultSet.next()) {
                UrlModel model = new UrlModel();
                model.setUrlId(resultSet.getInt(TABLE + "_id"));
                model.setUrlMain(resultSet.getString(TABLE + "_main"));
                model.setUrlFormat(resultSet.getString(TABLE + "_format"));
                model.setUrlTag(resultSet.getString(TABLE + "_tag"));
                model.setUrlStart(resultSet.getInt(TABLE + "_start"));
                model.setUrlEnd(resultSet.getInt(TABLE + "_end"));
                model.setUrlStep(resultSet.getInt(TABLE + "_step"));
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

    public static int insert(UrlModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getUrlMain(), model.getUrlFormat(), model.getUrlTag(), String.valueOf(model.getUrlStart()), String.valueOf(model.getUrlEnd()), String.valueOf(model.getUrlStep())};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "insert_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(UrlModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getUrlId()), model.getUrlMain(), model.getUrlFormat(), model.getUrlTag(), String.valueOf(model.getUrlStart()), String.valueOf(model.getUrlEnd()), String.valueOf(model.getUrlStep())};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "update_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(UrlModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getUrlId())};
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
