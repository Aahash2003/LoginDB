package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ModifyUserController implements Initializable {

    @FXML
    private TextField Addy;

    @FXML
    private ComboBox<String> Combo;

    @FXML
    private TextField EM;

    @FXML
    private TextField FN;

    @FXML
    private TextField ID;

    @FXML
    private Button SVBTN;

    @FXML
    private Button cnc;

    private UserAdapter userAdapter;
    Connection connection;

    @FXML
    private IFinanceController controller = IFinanceController.instance;

    @FXML
    private CreateNewUserController CT;

    private ObservableList<String> data = FXCollections.observableArrayList();

    public void setModel(UserAdapter userAdapt) throws SQLException {
       String DB_URL = "jdbc:derby:iFinanceDB;create=true";
        this.connection = DriverManager.getConnection(DB_URL);
        this.userAdapter = userAdapt;
        build();

    }


    public void Cancel() {
        Stage stage = (Stage) cnc.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Combo.setItems(this.data);
        System.out.println(data);

    }
public void build() throws SQLException{
        data.addAll(userAdapter.buildComboBoxData());
}




        @FXML
        public void Modify() throws SQLException {
            String selected = Combo.getValue();
            Selected(selected);
        }

    @FXML
    public void Selected(String username) throws SQLException {

        String [] Chosen;
            try {
                Chosen = userAdapter.userSelected(username);

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
            int SID = Integer.parseInt(Chosen[0]);
            String full = Chosen[1];
            String add = Chosen[2];
            String Sem = Chosen[3];

            ID.setText(String.valueOf(SID));
            FN.setText(full);
            Addy.setText(add);
            EM.setText(Sem);



        }
   @FXML
    void save() throws SQLException{
        String fullN = FN.getText();
        String addy = Addy.getText();
        String email = EM.getText();
        userAdapter.modify(fullN, addy, email);
       Stage stage = (Stage) cnc.getScene().getWindow();
       stage.close();
   }
    }




