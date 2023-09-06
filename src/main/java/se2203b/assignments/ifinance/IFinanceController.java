package se2203b.assignments.ifinance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class IFinanceController implements Initializable {

    public MenuItem logout;
    @FXML
    private MenuItem close;

    @FXML
    private MenuItem loginBtn;

    @FXML
    private MenuBar mainMenu;

    @FXML
    public Menu user;
    @FXML
    private Menu about;

    @FXML
    private MenuItem changeP;
    @FXML
    private Menu chart;

    @FXML
    private Menu doubleEntry;

    @FXML
    private Menu financialR;

    @FXML
    private Menu manageAccounts;

    @FXML
    private Menu UserAccounts;

    private ModifyUserController mod= new ModifyUserController();

    public Connection conn;
    public UserAdapter users;

    public static IFinanceController instance;




    public void showAbout() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("About-view.fxml"));
        Parent About = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(About));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }public void showLogin() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Login-view.fxml"));
        Parent Login = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        loginController.setModel(this.users);
        Stage stage = new Stage();
        stage.setScene(new Scene(Login));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Login");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }





    public void showCreate() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Create-NewUser.fxml"));
        Parent Create = fxmlLoader.load();
        CreateNewUserController createNewUserController = fxmlLoader.getController();
        createNewUserController.setModel(this.users);
        Stage stage = new Stage();
        stage.setScene(new Scene(Create));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Create User Account");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showChange() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("ChangePassword-view.fxml"));
        Parent password = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(password));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Change Password");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void showModify() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Modify-User.fxml"));
        Parent modify = fxmlLoader.load();
        ModifyUserController modifyUserController = fxmlLoader.getController();
        modifyUserController.setModel(this.users);
        Stage stage = new Stage();
        stage.setScene(new Scene(modify));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Modify User Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showDelete() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Delete-User.fxml"));
        Parent Delete = fxmlLoader.load();
        DeleteUserController deleteUserController = fxmlLoader.getController();
        deleteUserController.setModel(this.users);
        Stage stage = new Stage();
        stage.setScene(new Scene(Delete));
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Delete User Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void setAdminMenuItem(Menu adminMenuItem){
        user = adminMenuItem;
    }


    public void showAdmin(String username){
        ImageView menuIcon = new ImageView(new Image("file:src/main/resources/se2203b/assignments/ifinance/icon.png"));
        user.setText(username);
        menuIcon.setFitHeight(20);
        menuIcon.setFitWidth(20);
        user.setGraphic(menuIcon);
        user.setVisible(true);
        UserAccounts.setDisable(false);




    }
    public void showUser(String username){
        ImageView menuIcon = new ImageView(new Image("file:src/main/resources/se2203b/assignments/ifinance/icon.png"));
        user.setText(username);
        menuIcon.setFitHeight(20);
        menuIcon.setFitWidth(20);
        user.setGraphic(menuIcon);

        user.setVisible(true);
        UserAccounts.setDisable(true);
        chart.setDisable(false);
        manageAccounts.setDisable(false);
        doubleEntry.setDisable(false);
        financialR.setDisable(false);




    }

    public void Logout(){
        user.setVisible(false);
        chart.setDisable(true);
        doubleEntry.setDisable(true);
        financialR.setDisable(true);
        manageAccounts.setDisable(true);
        UserAccounts.setDisable(true);


    }



    public void exit() {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            instance = this;
            String DB_URL = "jdbc:derby:UserDB;create=true";
            conn = DriverManager.getConnection(DB_URL);
            users = new UserAdapter(conn,true);

            ImageView menuIcon = new ImageView(new Image("file:src/main/resources/se2203b/assignments/ifinance/icon.png"));
            menuIcon.setFitHeight(20);
            menuIcon.setFitWidth(20);
            user.setGraphic(menuIcon);
            user.setVisible(false);

            chart.setDisable(true);
            doubleEntry.setDisable(true);
            financialR.setDisable(true);
            manageAccounts.setDisable(true);
            UserAccounts.setDisable(true);


        } catch (SQLException ex) {

        }

        try {
            UserAdapter adapter = new UserAdapter(conn, false);
        } catch (SQLException e) {
        }
    }

}