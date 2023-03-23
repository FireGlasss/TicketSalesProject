package connection;

import java.sql.*;

public class Connections {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection connection;
    public static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Соединение с БД не установлено");
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void startConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        connection.createStatement();
        statement.executeUpdate(DataBaseCommands.CREATE_DATA_BASE);
        statement.executeUpdate(DataBaseCommands.CREATE_TABLE_USERS);
        statement.executeUpdate(DataBaseCommands.CREATE_TABLE_PLACE);
        statement.executeUpdate(DataBaseCommands.CREATE_TABLE_TICKET);

    }

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
    }
}
