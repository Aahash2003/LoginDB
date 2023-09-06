package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {

    @FXML
    private TextField DAD;

    @FXML
    private ComboBox<String> DC;

    @FXML
    private TextField DEM;

    @FXML
    private TextField DFN;

    @FXML
    private TextField DID;

    @FXML
    private Button cnc;

    @FXML
    private Button DEL;






        private UserAdapter userAdapter;
        Connection connection;

        @FXML
        private IFinanceController controller = IFinanceController.instance;


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
            this.DC.setItems(this.data);
            System.out.println(data);


        }
        public void build() throws SQLException{
            data.addAll(userAdapter.buildComboBoxData());
        }




        @FXML
        public void Modify() throws SQLException {
            String selected = DC.getValue();
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

            DID.setText(String.valueOf(SID));
            DFN.setText(full);
            DAD.setText(add);
            DEM.setText(Sem);



        }
        @FXML
        void DEL() throws SQLException{

            String id = DID.getText();
            userAdapter.delete(Integer.parseInt(id));
            Stage stage = (Stage) cnc.getScene().getWindow();
            stage.close();
        }
    }

