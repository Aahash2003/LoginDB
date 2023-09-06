
package se2203b.assignments.ifinance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AboutController {
    @FXML
    Button okBtn;

    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) okBtn.getScene().getWindow();
        // Close stage
        stage.close();
    }
}
