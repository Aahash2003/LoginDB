package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserAdapter {
    int UID;
    Connection connection;

    public UserAdapter(Connection conn, Boolean reset) throws SQLException {

        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {

                stmt.execute("DROP TABLE Users");
            } catch (SQLException ex) {

            } finally {

                stmt.execute("CREATE TABLE Users ("
                        + "UserID INT NOT NULL PRIMARY KEY, "
                        + "FullName VARCHAR(50), "
                        + "Address VARCHAR(100), "
                        + "Email VARCHAR(50), "
                        + "Username VARCHAR(50),"
                        + "Password VARCHAR(50))");
                create();
                print();
            }
        }
    }

    public void create() throws SQLException {
        this.insert(0, "System Administrator", "Western University", "admin@uwo.ca", "admin", "admin");
    }

    public ObservableList<String> buildComboBoxData() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        String sqlStatement = "SELECT Username FROM Users WHERE Username != 'admin'";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(sqlStatement);


        while (rs.next()) {
            System.out.println(rs.getString("Username"));
            list.add(rs.getString("Username"));

        }
        return list;


    }

    public void insert(int ID, String name, String address, String email, String username, String password) throws SQLException {
        String sqlStatement = "INSERT INTO Users (UserID, FullName, Address, Email, Username, Password) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1, ID);
        statement.setString(2, name);
        statement.setString(3, address);
        statement.setString(4, email);
        statement.setString(5, username);
        statement.setString(6, password);
        statement.executeUpdate();
    }

    public int getMax() throws SQLException {
        ObservableList<String> ML = FXCollections.observableArrayList();
        int num = 0;
        Statement sql = this.connection.createStatement();
        String matchNumber = "SELECT MAX(UserID) FROM Users";

        ResultSet rs = sql.executeQuery(matchNumber);
        if (rs.next()) {
            num = rs.getInt(1) + 1;
        }

        return num;
    }

    public String[] userSelected(String username) throws SQLException {
        String[] Chosen = new String[6];
        Statement stmt = this.connection.createStatement();
        String sqlStatement = "SELECT * FROM Users WHERE Username = '" + username + "'";
        ResultSet rs = stmt.executeQuery(sqlStatement);
        if (rs.next()) {
            Chosen[0] = rs.getString("UserID");
            UID = Integer.parseInt(Chosen[0]);
            Chosen[1] = rs.getString("FullName");
            Chosen[2] = rs.getString("Address");
            Chosen[3] = rs.getString("Email");
            Chosen[4] = rs.getString("Username");
            Chosen[5] = rs.getString("Password");


        }
        return Chosen;


    }

    public void modify(String fullN, String addy, String email) throws SQLException {
        String sql = "UPDATE Users SET FullName = ?, Address  = ?, Email = ? WHERE UserID = ?";
        PreparedStatement PST = connection.prepareStatement(sql);
        PST.setString(1, fullN);
        PST.setString(2, addy);
        PST.setString(3, email);
        PST.setInt(4, UID);
        PST.executeUpdate();


    }

    public void delete(int ID) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        PreparedStatement PST = connection.prepareStatement(sql);
        PST.setInt(1, UID);
        PST.executeUpdate();


    }

    public void print() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();


        String sql = "SELECT * FROM Users";

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("Username"));
            System.out.println(rs.getString("Password"));
        }
    }
}


