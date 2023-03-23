package repository;

import connection.Connections;
import connection.Constants;
import entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public void create(User user)  {
        try {
        String statement = "INSERT INTO " + Constants.DATABASE_NAME + "." + Constants.USERS_TABLE + "(" +
                Constants.USERS_EMAIL + "," + Constants.USERS_PASSWORD + "," +
                Constants.USERS_balance + ")" + "VALUES (?,?,?);";
        PreparedStatement prSt = Connections.getDbConnection().prepareStatement(statement);
        prSt.setString(1, user.getEmail());
        prSt.setString(2, user.getPassword());
        prSt.setString(3, Double.toString(user.getBalance()));
        prSt.executeUpdate();
        user.setID(getID(user));
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }


    public User getUser(String email)  {
        try {
        String select = "SELECT email, password, balance, id from avia_tickets.users where email = ?;";
        PreparedStatement prSt = Connections.getDbConnection().prepareStatement(select);
        prSt.setString(1, email);
        ResultSet resultSet = prSt.executeQuery();
        resultSet.next();
        User user = new User(resultSet.getString("email"), resultSet.getString("password"),
                resultSet.getDouble("balance"));
        user.setID(resultSet.getInt("id"));
        return user;
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        try {
            String change = "UPDATE avia_tickets.users SET email = ?, password = ?, balance = ? " +
                    "where id = ?;";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(change);
            prSt.setString(1, user.getEmail());
            prSt.setString(2, user.getPassword());
            prSt.setDouble(3, user.getBalance());
            prSt.setInt(4, user.getID());
            prSt.executeUpdate();
         }  catch (SQLException | ClassNotFoundException e) {
        throw e;
    }

    }

    public void delete(String email)  {
        try {
        String remove = "DELETE FROM avia_tickets.users WHERE email = '" + email + "';";
        Statement st = Connections.getDbConnection().createStatement();
        st.executeUpdate(remove);
        }  catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public int getID(User user) throws SQLException, ClassNotFoundException {
        try {
        String ID = "SELECT id from avia_tickets.users where email = '" + user.getEmail() + "';";
        Statement st = Connections.getDbConnection().createStatement();
        ResultSet resultSet = st.executeQuery(ID);
        resultSet.next();
        return resultSet.getInt(1);
        }  catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }
}
