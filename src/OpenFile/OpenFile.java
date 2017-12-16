package OpenFile;

import MainEnterForm.MainEnterFormGUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class OpenFile {
    OpenFileController controllerOpenFile = new OpenFileController();

    public void openFileWindow(Window stg, String name) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainEnterFormGUI.class.getResource("/OpenFile/OpenFile.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(name);
            window.initOwner(stg);
            Scene scene = new Scene(pane, 800, 600);
            window.setScene(scene);

            controllerOpenFile = loader.getController();
            controllerOpenFile.setDialogStage(window);

            window.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
