package TemporaryWindow;

import MainEnterForm.EnterFormController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class TempWindowController {


    @FXML
    public Button buttonToFiles;
    @FXML
    public Button buttonComplete;

    //Контроллер главного окна
    public EnterFormController enterFormController = new EnterFormController();

    Stage stageTemporaryWindow = new Stage();

    public void Init(String login) {

        buttonToFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                stageTemporaryWindow = (Stage) buttonToFiles.getScene().getWindow();
                stageTemporaryWindow.close();

                //Открывает главное окно программы
                enterFormController.showEnterFormGUI(login);
            }
        });

        buttonComplete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageTemporaryWindow = (Stage) buttonComplete.getScene().getWindow();
                stageTemporaryWindow.close();

                enterFormController.showCompleteRegWindow(login);
            }
        });
    }
}