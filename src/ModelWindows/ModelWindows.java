package ModelWindows;

import MainEnterForm.MainEnterFormGUI;
import OpenFile.OpenFileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ModelWindows {


    CreateFileController controllerFile = new CreateFileController();

    public void newWindowCreateFile(Window stg) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainEnterFormGUI.class.getResource("/ModelWindows/ModelCreateFile.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initOwner(stg);
            Scene scene = new Scene(pane);
            window.setScene(scene);

            controllerFile = loader.getController();
            controllerFile.setDialogStage(window);

            window.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameFile() {
        return this.controllerFile.getName();
    }

    public String getTypeFile() {
        return this.controllerFile.getType();
    }

    public boolean getFlagFile() {
        return this.controllerFile.getFlag();
    }


    CreateFolderController controllerFolder = new CreateFolderController();

    public void newWindowCreateFolder(Window stg) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainEnterFormGUI.class.getResource("/ModelWindows/ModelCreateFolder.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.initOwner(stg);
            Scene scene = new Scene(pane);
            window.setScene(scene);

            controllerFolder = loader.getController();
            controllerFolder.setDialogStage(window);

            window.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameFolder() {
        return this.controllerFolder.getName();
    }

    public boolean getFlagFolder() {
        return this.controllerFolder.getFlag();
    }

}

