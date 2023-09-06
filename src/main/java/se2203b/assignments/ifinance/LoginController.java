package se2203b.assignments.ifinance;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button cancel;

    @FXML
    private Button okay;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private UserAdapter userAdapter;

    Connection connection;

    @FXML
    private Label message;

    @FXML
    private IFinanceController controller = IFinanceController.instance;


    public void setModel(UserAdapter userAdapt) {
        userAdapter = userAdapt;
    }


    public void exit() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setPromptText("Enter your username");
        password.setPromptText("Enter your password");

    }

    @FXML
    public void executeLogIn() throws SQLException {

        String DB_URL = "jdbc:derby:UserDB;create=true";
        connection = DriverManager.getConnection(DB_URL);

        String userU = username.getText().replaceAll(" ", ""); // stores user inputted value for username
        String userP = password.getText().replaceAll(" ", ""); // stores user inputted value for password

        String[] Chosen;
        try {
            Chosen = userAdapter.userSelected(userU);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String user = Chosen[4];
        String pass = Chosen[5];
        if (!userU.equals(user)) {

            message.setText("Incorrect username");
        }
        if (userU.equals(user) && !userP.equals(pass)) {

            message.setText("Wrong password");

        }
        if (userU.equals("admin") && userP.equals(pass)) {
            controller.showAdmin(userU);
            Stage stage = (Stage) okay.getScene().getWindow();
            stage.close();

        } else if (userU.equals(user) && userP.equals(pass)) {
            controller.showUser(userU);
            Stage stage = (Stage) okay.getScene().getWindow();
            stage.close();

        }
    }

}
