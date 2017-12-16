package ModelWindows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateFileController {
    private String name = null;
    private String type = null;
    private boolean flag = false;

    @FXML
    TextField textName = new TextField();
    @FXML
    TextField textType = new TextField();
    @FXML
    Label labelSuccess = new Label();
    @FXML
    Button btnOk = new Button();
    @FXML
    Button btnCancel = new Button();

    Stage window = new Stage();


    @FXML
    private void initialize() {
        setOnActionBtnCancel();
        setOnActionBtnOk();

    }

    public void setDialogStage(Stage window) {
        this.window = window;
    }

    public void setOnActionBtnOk() {
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textName.getText().isEmpty()) {
                    name = textName.getText();
                    type = textType.getText();
                    flag = true;
                    window.close();
                } else {
                    flag = false;
                    labelSuccess.setText("Input both text field!");
                }
            }
        });
    }

    public void setOnActionBtnCancel() {
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flag = false;
                window.close();
            }
        });
    }

    public boolean getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
