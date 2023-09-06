package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class ChangePasswordController {

    @FXML
    private Button cancel;
    @FXML
    private Label userE;

    @FXML
    private PasswordField confirm;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private Button save;
    Connection connection;
    @FXML
    private IFinanceController controller= IFinanceController.instance;


    @FXML
    void exit(ActionEvent event) {
        // Get current stage reference
        Stage stage = (Stage) cancel.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String userEnteredOldPassword = oldPassword.getText().replaceAll(" ", "");
        String userEnteredNewPassword = newPassword.getText().replaceAll(" ", "");
        String userEnteredConfirmPassword = confirm.getText().replaceAll(" ", "");

        String DB_URL = "jdbc:derby:UserDB;create=true";
        connection = DriverManager.getConnection(DB_URL);
        String sqlStatement = "SELECT * FROM Users";
        ArrayList<String> storedPassword= new ArrayList<>();
        System.out.println(storedPassword.listIterator());
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                storedPassword.add(rs.getString("Password"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        if(!userEnteredOldPassword.equals(storedPassword.get(0))){
            userE.setStyle("-fx-text-fill: red;");
            userE.setText("Wrong old password");
        }
        if(userEnteredOldPassword.equals(storedPassword.get(0)) && userEnteredNewPassword.isBlank()){
            userE.setStyle("-fx-text-fill: red;");
            userE.setText("Indicate new password");
        }
        if(userEnteredOldPassword.equals(storedPassword.get(0)) && !userEnteredConfirmPassword.equals(userEnteredNewPassword)){
            userE.setStyle("-fx-text-fill: red;");
            userE.setText("The new passwords do not match");
        }
        if(userEnteredOldPassword.equals(storedPassword.get(0)) && userEnteredConfirmPassword.equals(userEnteredNewPassword)){
            userE.setStyle("-fx-text-fill: red;");
            userE.setText("Everything correct");
            String sqlUpdateStatement = "UPDATE Users SET Password = ? WHERE Username='admin'";
            PreparedStatement statement2 = connection.prepareStatement(sqlUpdateStatement);
            statement2.setString(1, userEnteredNewPassword);
            statement2.executeUpdate();

            // Get current stage reference
            Stage stage = (Stage) save.getScene().getWindow();
            controller.Logout();
            // Close stage
            stage.close();
        }

    }

}
