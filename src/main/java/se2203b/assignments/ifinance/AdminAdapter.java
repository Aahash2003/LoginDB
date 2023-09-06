package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AdminAdapter {

    Connection connection;
    public AdminAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // drop table if it is created before so we starting a new database
                stmt.execute("DROP TABLE Admins");
            } catch (SQLException ex) {
                // catch any SQL error, no need for handling
            } finally {
                // create users table with required columns
                stmt.execute("CREATE TABLE Admins ("
                        + "FullName VARCHAR(50), "
                        + "Address VARCHAR(100), "
                        + "Email VARCHAR(50), "
                        + "Username VARCHAR(50) PRIMARY KEY,"
                        + "Password VARCHAR(50))");
                createAdmin();
                printData();
            }
        }
    }

    public void createAdmin() throws SQLException {
        this.insertAdmin("admin","admin");
    }


    public void insertAdmin(String username, String password) throws SQLException {
        String sqlStatement = "INSERT INTO Users (FullName, Address, Email, Username, Password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlStatement);

        statement.setString(1, username);
        statement.setString(2, password);
        statement.executeUpdate();
    }



    public void printData() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();

        String sqlStatement = "SELECT * FROM Admins";

        rs = stmt.executeQuery(sqlStatement);
        while(rs.next()){
            System.out.println(rs.getString("Username"));
            System.out.println(rs.getString("Password"));
        }
    }


}