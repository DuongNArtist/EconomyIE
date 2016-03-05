package economyie.databases;

import economyie.models.DocModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Duong on 20/02/2016.
 */
public class DocBusiness {
    public static final String TABLE = "doc";
    private static Connection connection = null;

    public static ObservableList<DocModel> select(String keyword, int start, int end) {
        ObservableList<DocModel> models = FXCollections.observableArrayList();
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{"%" + keyword + "%", String.valueOf(start), String.valueOf(end)};
            ResultSet resultSet = DatabaseConnector.getCallableStatement(connection, "select_" + TABLE, params).executeQuery();
            while (resultSet.next()) {
                DocModel model = new DocModel();
                model.setDocId(resultSet.getInt(TABLE + "_id"));
                model.setDocName(resultSet.getString(TABLE + "_name"));
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

    public static int insert(DocModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{model.getDocName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "insert_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int update(DocModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getDocId()), model.getDocName()};
            CallableStatement statement = DatabaseConnector.getCallableStatement(connection, "update_" + TABLE, params);
            updated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(connection);
        }
        return updated;
    }

    public static int delete(DocModel model) {
        int updated = 0;
        try {
            connection = DatabaseConnector.getConnection();
            String[] params = new String[]{String.valueOf(model.getDocId())};
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
