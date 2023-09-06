package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateNewUserController implements Initializable {

    @FXML
    private TextField Address;

    @FXML
    private PasswordField ConfirmP;

    @FXML
    private TextField Email;

    @FXML
    private TextField FullName;

    @FXML
    private PasswordField Password;
    @FXML
    private Label message;
    @FXML
    private Button canc;
    @FXML
    private Button sav;
    ArrayList<String> SFN= new ArrayList<>();
    ArrayList<String> SAD= new ArrayList<>();
    ArrayList<String> SEM= new ArrayList<>();
    ArrayList<String> SUS= new ArrayList<>();
    ArrayList<String> SPS= new ArrayList<>();
    @FXML
    private TextField Username;
    private UserAdapter userAdapter;
    Connection connection;
    @FXML
    private IFinanceController controller= IFinanceController.instance;

    public void setModel(UserAdapter userAdapt) {
        userAdapter = userAdapt;
    }

    public void cancel() {
        Stage stage = (Stage) canc.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void TextInput() throws Exception {
        String DB_URL = "jdbc:derby:UserDB;create=true";
        connection = DriverManager.getConnection(DB_URL);
        String FN = FullName.getText().replaceAll(" ", "");
        String AD = Address.getText().replaceAll(" ", "");
        String EM = Email.getText().replaceAll(" ", "");
        String US = Username.getText().replaceAll(" ", "");
        String PS = Password.getText().replaceAll(" ", "");
        String CPS = ConfirmP.getText().replaceAll(" ", "");


        if(!(PS.equals(CPS))){
            message.setStyle("-fx-text-fill: red;");
            message.setText("The new passwords do not match");
        }
        if(PS.equals(CPS)){
            userAdapter.insert(userAdapter.getMax(),FN, AD, EM, US, PS);
            System.out.println("FirstName"+FN);
            System.out.println("US"+US);

            System.out.println();

            Stage stage = (Stage) sav.getScene().getWindow();

            stage.close();

        }
    }

}



