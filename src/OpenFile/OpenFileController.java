package OpenFile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class OpenFileController {

    @FXML
    private TextArea mainTextArea;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnExit;

    Stage window = new Stage();

    @FXML
    private void initialize() {setOnActionBtnExit();
        setOnActionBtnSave();

    }

    public void setDialogStage(Stage window) {
        this.window = window;
    }

    public void setOnActionBtnExit() {
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
    }

    public void setOnActionBtnSave() {
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
    }
}
