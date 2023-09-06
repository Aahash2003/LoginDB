package se2203b.assignments.ifinance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class IFinanceApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(IFinanceApplication.class.getResource("IFinance-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("iFINANCE");
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setScene(scene);
        stage.show();
    }
}
